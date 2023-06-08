package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.LoginForm;
import perfumeManage.perfumeManagingSystem.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CustomerRepository customerRepository;

    public Customer login(Long customerId, String password) {
        Customer customer = customerRepository.findById(customerId);
            if (!customer.getPassword().equals(password)) {
                customer = null;
            }
            return customer;
    }
}
