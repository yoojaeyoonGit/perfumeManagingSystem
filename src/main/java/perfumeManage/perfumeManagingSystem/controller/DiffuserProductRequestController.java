package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.SessionConst;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
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

    @PutMapping("{id}/diffuserProductionProcessing")
    public String ChangeDiffuserRequestStatusToProcessing(@PathVariable("id") Long id, @RequestBody DiffuserRequestStatusDetect diffuserRequestStatusDetect) {
        DiffuserProductRequest diffuserProductRequest = diffuserProductRequestService.find(id);
        diffuserProductRequestService.changeDiffuserProductRequestStatusToProcessing(diffuserProductRequest, diffuserRequestStatusDetect);
        return "request/diffuserRequest";
    }

    @PutMapping("{id}/diffuserProductionComplete")
    public String ChangeDiffuserRequestStatusToComplete(@PathVariable("id") Long id, @RequestBody DiffuserRequestStatusDetect diffuserRequestStatusDetect) {
        try{

            DiffuserProductRequest diffuserProductRequest = diffuserProductRequestService.find(id);

            ProcessingRequest processingRequest = diffuserProductRequest.getProcessingRequest();
            diffuserProductRequestService.changeDiffuserProductRequestStatusToComplete (processingRequest, diffuserProductRequest, diffuserRequestStatusDetect);
            return "request/diffuserRequest";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/1/main";
        }
    }
}
