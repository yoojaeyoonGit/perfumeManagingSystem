package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Address;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.SignInDto;
import perfumeManage.perfumeManagingSystem.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Long saveCustomer(SignInDto signInDto) {
        Address address = new Address(signInDto.getCountry(), signInDto.getCity(), signInDto.getStreetAddress() , signInDto.getDetailedAddress(), signInDto.getZipcode());

        Customer customer = new Customer();
        customer.setName(signInDto.getName());
        customer.setAge(signInDto.getAge());
        customer.setAddress(address);
        customer.setAuth(signInDto.getAuth());

        customerRepository.save(customer);
        return customer.getId();
    }


}
