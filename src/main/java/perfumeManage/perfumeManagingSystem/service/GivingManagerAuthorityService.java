package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Auth;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.AuthorityTest;

@Service
@Transactional
@RequiredArgsConstructor
public class GivingManagerAuthorityService {
    private final CustomerService customerService;
    public void giveManagerAuthority (Customer customer, AuthorityTest authorityTest) {
//        Customer customer = customerService.findCustomer(authorityTest.getCustomerId());
        System.out.println("this is type check ");
        System.out.println(customer.getAuth() instanceof Enum);
        if (customer.getAuth() == Auth.General)
            System.out.println(authorityTest.getAnswer());
            if (authorityTest.getAnswer().equals("I Want Manager Authority")) {
                customer.setAuth(Auth.Manager);
                System.out.println("권한 체크");
            }
            else
                System.out.println("You are already Manager");
    }
}
