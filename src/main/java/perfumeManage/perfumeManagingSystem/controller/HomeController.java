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
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final CustomerService customerService;
    @GetMapping("/")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        try{
            // 회원 가입된 회원이 없다면 회원가입 페이지로 있다면 로그인 페이지로
            List <Customer> signedUpCustomerList = customerService.findAllCustomer();

            if (signedUpCustomerList.isEmpty()) {
                return "redirect:/customer/new";
            } else {

                HttpSession session = httpServletRequest.getSession(false);
//                System.out.println();
//                if (session == null) {
//                    return "redirect:/login";
//                }
//
//                if (session.getAttribute(SessionConst.LOGIN_CUSTOMER) == null) {
//                    return "redirect:/login";
//                } else {
                    Customer loggedInCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
                    Long customerId = loggedInCustomer.getId();
                    Customer customer = customerService.findCustomer(customerId);
//
                    model.addAttribute( "customer", customer);
                    System.out.println("얘는 계정 주야 " + customer);

                    return "home";
//                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            return "home";
        }
    }
}