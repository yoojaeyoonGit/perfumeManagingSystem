package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.SessionConst;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;
import perfumeManage.perfumeManagingSystem.dto.CustomerDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class DiffuserProductRequestController {

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

    @PostMapping("{id}/diffuserProductRequest")
    public String diffuserRequest(@PathVariable("id") Long id, DiffuserRequestDto diffuserRequestDto) {
        Customer customer = customerService.findCustomer(id);
        diffuserProductRequestService.saveDiffuserProductRequest(customer, diffuserRequestDto);
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
