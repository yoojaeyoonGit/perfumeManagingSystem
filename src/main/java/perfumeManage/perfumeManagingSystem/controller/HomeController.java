package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
//@Controller
//@Slf4j
//public class HomeController {
////    private final CustomerService customerService;
//    @RequestMapping("/" )
//    public String homePage(Model model) {
////        long man = 1;
//
////        Customer customer = customerService.findCustomer(man);
////        model.addAttribute(customer);
//        return "Home";
//    }
//
//}


@Controller
@Slf4j
public class HomeController {
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "hello";
    }
}