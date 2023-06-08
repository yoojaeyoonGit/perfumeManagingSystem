package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import perfumeManage.perfumeManagingSystem.SessionConst;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final CustomerService customerService;
    @GetMapping("/")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        try{
            System.out.println("시발아1111");
            HttpSession session = httpServletRequest.getSession(false);
            System.out.println("하 ... "+ session.getAttribute(SessionConst.LOGIN_CUSTOMER));
            System.out.println();

            if (session.getAttribute(SessionConst.LOGIN_CUSTOMER) == null) {
                return "redirect:/customer/new";

            } else {
                Customer loggedInCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
                Long customerId = loggedInCustomer.getId();
//            long customerId = 1;
                Customer customer = customerService.findCustomer(customerId);
//
                model.addAttribute(customer);
//            log.info("this is session " + customer.getName());

                return "home";
            }
        } catch (Exception e) {
            e.getStackTrace();
            System.out.printf("시발아");
            return "home";
        }
    }
}