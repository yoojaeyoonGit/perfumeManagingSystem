package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.SessionConst;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.dto.CustomerDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;
import perfumeManage.perfumeManagingSystem.service.DiffuserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class DiffuserProductRequestController {
    private final DiffuserService diffuserService;
    private final DiffuserProductRequestService diffuserProductRequestService;
    private final CustomerService customerService;
    @GetMapping("/{id}/diffuserProductRequest")
    public String diffuserProductRequest(@PathVariable ("id")Long id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "redirect:/members/new";
        }

        Customer loginmCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
        model.addAttribute("loginCustomer", loginmCustomer);
        model.addAttribute("diffuserRequest", new DiffuserRequestDto());
        return "request/diffuserRequest";
    }

    @PostMapping("{customerId}/{diffuserId}/diffuserProductRequest")
    public String diffuserRequest(@PathVariable("customerId") Long customerId,@PathVariable("diffuserId") Long diffuserId, DiffuserRequestDto diffuserRequestDto) {
        Customer customer = customerService.findCustomerById(customerId);

        Diffuser diffuser = diffuserService.findById(diffuserId);
        DiffuserProductRequest diffuserProductRequest = new DiffuserProductRequest();

        Deadline deadline = new Deadline(diffuserRequestDto.getYear(), diffuserRequestDto.getMonth(), diffuserRequestDto.getDate());
        diffuserProductRequest.setCustomer(customer);
        diffuserProductRequest.setAmount(diffuserRequestDto.getAmount());
        diffuserProductRequest.setDeadline(deadline);
        diffuserProductRequest.setStatus(ProductionStatus.REQUEST);
        diffuserProductRequest.setDiffuser(diffuser);
        diffuserProductRequest.getCustomer().addDiffuserProductRequest(diffuserProductRequest);

        diffuserProductRequestService.saveDiffuserProductRequest(diffuserProductRequest);
        return "redirect:/";
    }

    @PostMapping("{customerId}/{id}/diffuserProductionProcessing")
    public String ChangeDiffuserRequestStatusToProcessing(@PathVariable("id") Long id,  @PathVariable("customerId") Long customerId) {
        DiffuserProductRequest diffuserProductRequest = diffuserProductRequestService.find(id);
        diffuserProductRequest.setStatus(ProductionStatus.PROCESSING);
        diffuserProductRequestService.changeDiffuserProductRequestStatusToProcessing(diffuserProductRequest);
        return "redirect:/{customerId}/checkAll/diffuser";
    }

    @PostMapping("{customerId}/{id}/diffuserProductionComplete")
    public String ChangeDiffuserRequestStatusToComplete(@PathVariable("id") Long id, @PathVariable("customerId") Long customerId) {
        try{

            DiffuserProductRequest diffuserProductRequest = diffuserProductRequestService.find(id);
            diffuserProductRequest.setStatus(ProductionStatus.COMPLETE);

            ProcessingRequest processingRequest = diffuserProductRequest.getProcessingRequest();
            diffuserProductRequestService.changeDiffuserProductRequestStatusToComplete (processingRequest, diffuserProductRequest);
            return "redirect:/{customerId}/checkAll/diffuser";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/1/main";
        }
    }
}
