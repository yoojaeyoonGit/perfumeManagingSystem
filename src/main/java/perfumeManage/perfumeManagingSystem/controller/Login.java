package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import perfumeManage.perfumeManagingSystem.SessionConst;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.LoginForm;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Login {
    private final LoginService loginService;
    private final CustomerService customerService;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest httpServletRequest) {

        List<Customer> customerList =  customerService.findAllCustomer();
        if (customerList.isEmpty()) {
            return "redirect:customer/new";
        }

        HttpSession session = httpServletRequest.getSession(false);

        if (session == null) {
            return "customer/login";
        }

        Customer loggedInCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);

        if (loggedInCustomer == null) {
            return "customer/login";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        try {
            if (bindingResult.hasErrors()) {
                return "customer/login";
            }

            Customer loggedInCustomer = loginService.login(loginForm.getId(), loginForm.getPassword());

            if (loggedInCustomer == null) {
                bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
                return "redirect:/login";
            }

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(SessionConst.LOGIN_CUSTOMER, loggedInCustomer);
            System.out.println(loggedInCustomer.getName());
            return "redirect:/";
        } catch (Exception e) {
            e.getStackTrace();
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
            HttpSession session = request.getSession(false);

            if(session != null) {
                session.invalidate();
            }
            return "main/home2";
    }
}
