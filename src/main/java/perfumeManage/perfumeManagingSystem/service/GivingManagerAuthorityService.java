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
    public boolean giveManagerAuthority (Customer customer, AuthorityTest authorityTest) {
        if (customer.getAuth() == Auth.General) {
            if (authorityTest.getAnswer().equals("Manager")) {
                customer.setAuth(Auth.Manager);
                return true;
            }
            else {
                return false;
            }
        } else {
            return true;
        }
    }
}
