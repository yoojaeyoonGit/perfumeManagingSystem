package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;

@Controller
@RequiredArgsConstructor
public class DiffuserProductRequestController {

    private final DiffuserProductRequestService diffuserProductRequestService;
    private final CustomerService customerService;
    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping("{id}/diffuserProduct")
    public String diffuserRequest(@PathVariable("id") Long id, @RequestBody DiffuserRequestDto diffuserRequestDto) {
        Customer customer = customerService.findCustomer(id);
        diffuserProductRequestService.saveDiffuserProductRequest(customer, diffuserRequestDto);
        return "request/diffuserRequest";
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
