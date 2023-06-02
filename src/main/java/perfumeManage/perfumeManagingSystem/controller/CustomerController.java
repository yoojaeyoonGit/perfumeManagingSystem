package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.CustomerDto;
import perfumeManage.perfumeManagingSystem.service.CustomerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/main")
    public String main (Model model) {

        List<Customer> customers = customerService.findAllCustomer();
        return "customer/customer";
    }
    @PostMapping("/customer")
    public String signIn(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return "customer/madeCustomer";
    }
}
