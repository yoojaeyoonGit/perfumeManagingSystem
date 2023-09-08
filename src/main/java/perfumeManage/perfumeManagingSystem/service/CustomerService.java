package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Address;
import perfumeManage.perfumeManagingSystem.domain.CompleteRequest;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
import perfumeManage.perfumeManagingSystem.dto.CustomerDto;
import perfumeManage.perfumeManagingSystem.repository.CompleteRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.CustomerRepository;
import perfumeManage.perfumeManagingSystem.repository.ProcessingRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CompleteRequestRepository completeRequestRepository;
    private final ProcessingRequestRepository processingRequestRepository;

    @Transactional
    public Long saveCustomer(Customer customer) {

        validateDuplicateCustomer(customer);
        customerRepository.save(customer);

        Customer customerForCompleteAndProcessingRequest = customerRepository.findById(customer.getId());
        CompleteRequest completeRequest = customerForCompleteAndProcessingRequest.getCompleteRequest();
        ProcessingRequest processingRequest = customerForCompleteAndProcessingRequest.getProcessingRequest();


        if (completeRequest == null) {
            completeRequest = CompleteRequest.createCompleteRequest(customer);
            completeRequestRepository.save(completeRequest);
        }

        if (processingRequest == null) {
            processingRequest = ProcessingRequest.createProcessingRequest(customer);
            processingRequestRepository.save(processingRequest);
        }

        return customer.getId();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    // 동일 이름 예외 처리
    public void validateDuplicateCustomer(Customer customer) {
        List<Customer> customers = customerRepository.findByName(customer.getName());
        if (!customers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void updateCustomer(Long id, String name) {
        Customer customer = customerRepository.findById(id);
        customer.setName(name);
    }

}
