package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import perfumeManage.perfumeManagingSystem.dto.SignInDto;
import perfumeManage.perfumeManagingSystem.service.CustomerService;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/main")
    public String main (Model model) {
//        model.addAttribute("data","hello");
        return "customer/customer";
    }
    @PostMapping("/customer")
    public String signIn(@RequestBody SignInDto signInDto) {
        customerService.saveCustomer(signInDto);
        System.out.println(signInDto.getName());
        System.out.println(signInDto.getAge());
        System.out.println(signInDto.getDetailedAddress());
        return "customer/madeCustomer";
    }
}
