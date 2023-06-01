package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Address;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.SignInDto;
import perfumeManage.perfumeManagingSystem.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

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
        customer.setGender(signInDto.getGender());
        customer.setAuth(signInDto.getAuth());
        customer.setAge(signInDto.getAge());
        customer.setPassword(signInDto.getPassword());
        customer.setPhoneNumber(signInDto.getPhoneNumber());
        customer.setSignUpDate(LocalDate.now());
        customer.setAddress(address);
        customer.setAuth(signInDto.getAuth());

        customerRepository.save(customer);
        return customer.getId();
    }

    public Customer findCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }


}
