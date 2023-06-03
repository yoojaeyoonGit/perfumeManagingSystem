package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public String checkAuthority (@PathVariable ("id") Long id) {
        Customer customer = customerService.findCustomer(id);
        System.out.println(customer.getAuth());

        return "authority/authorityPage";
    }

    @PutMapping("{id}/auth")
    public String changeAuthority(@PathVariable ("id") Long id,  @RequestBody AuthorityTest authorityTest) {
        Customer customer = customerService.findCustomer(id);
        givingManagerAuthorityService.giveManagerAuthority(customer, authorityTest);
        return "authority/authorityPage";
    }
}
