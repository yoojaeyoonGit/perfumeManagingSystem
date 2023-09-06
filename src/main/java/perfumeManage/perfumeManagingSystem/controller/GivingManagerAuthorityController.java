package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.AuthorityTest;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.GivingManagerAuthorityService;

@Controller
@RequiredArgsConstructor
public class GivingManagerAuthorityController {

    private final CustomerService customerService;
    private final GivingManagerAuthorityService givingManagerAuthorityService;

    @GetMapping("{id}/auth")
    public String checkAuthority (@PathVariable ("id") Long id, Model model) {
        Customer customer = customerService.findCustomer(id);
        model.addAttribute("customer", customer);
        model.addAttribute("AuthorityTest", new AuthorityTest());

        return "authority/authorizationPage";
    }

    @PostMapping("{id}/auth")
    public String changeAuthority(@PathVariable ("id") Long id, AuthorityTest authorityTest) {
        Customer customer = customerService.findCustomer(id);
        boolean authorityTestCheck = givingManagerAuthorityService.giveManagerAuthority(customer, authorityTest);
//      Manager
        if (authorityTestCheck == true) {
            return "redirect:/";
        } else {
            return "redirect:/{id}/auth";
        }
    }
}
