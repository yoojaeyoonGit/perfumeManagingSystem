package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Address;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.dto.CustomerDto;
import perfumeManage.perfumeManagingSystem.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Long saveCustomer(CustomerDto customerDto) {
        Address address = new Address(customerDto.getCountry(), customerDto.getCity(), customerDto.getStreetAddress() , customerDto.getDetailedAddress(), customerDto.getZipcode());

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setGender(customerDto.getGender());
        customer.setAuth(customerDto.getAuth());
        customer.setAge(customerDto.getAge());
        customer.setPassword(customerDto.getPassword());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setSignUpDate(LocalDate.now());
        customer.setAddress(address);
        customer.setAuth(customerDto.getAuth());

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
