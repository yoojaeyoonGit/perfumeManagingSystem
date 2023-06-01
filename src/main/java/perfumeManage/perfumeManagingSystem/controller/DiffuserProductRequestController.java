package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;

import java.util.List;

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
}
