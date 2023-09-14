package perfumeManage.perfumeManagingSystem.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.Address;
import perfumeManage.perfumeManagingSystem.domain.Auth;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.Gender;
import perfumeManage.perfumeManagingSystem.service.CustomerService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CustomerApiController {
    private final CustomerService customerService;

    @GetMapping("/api/v1/customers")
    public Result member() {
        List<Customer> findCustomer = customerService.findAllCustomer();
        List<CustomerDto> customer = findCustomer.stream()
                .map(m -> new CustomerDto(m.getId(), m.getName()))
                .collect(Collectors.toList());
        return new Result<>(customer);
    }

    @Data
    @AllArgsConstructor
    static class CustomerDto {
        private Long id;
        private String name;
    }




    @GetMapping("/api/customer/{id}")
    public Result findMember(@PathVariable("id") Long id) {
        Customer findCustomer = customerService.findCustomerById(id);
        CustomerDto customerDto = new CustomerDto(id, findCustomer.getName());
        return new Result<>(customerDto);
    }

    @PostMapping("/api/customer")
    public Result createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer();
        customer.setName(createCustomerRequest.getName());
        customer.setPassword(createCustomerRequest.getPassword());
        customer.setGender(createCustomerRequest.getGender());
        customer.setAge(createCustomerRequest.getAge());
        customer.setAddress(new Address(createCustomerRequest.getCountry(), createCustomerRequest.getCity(), createCustomerRequest.getStreetAddress(), createCustomerRequest.getDetailedAddress(), createCustomerRequest.getZipcode()));
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        customer.setSignUpDate(LocalDate.now());
        customer.setAuth(Auth.General);
        customerService.saveCustomer(customer);

        CreateCustomerResponse createCustomerResponse = new CreateCustomerResponse(customer.getId(), customer.getName());
        return new Result<>(createCustomerResponse);
    }

    @PutMapping("/api/customer/{id}")
    public Result updateCustomerApi(
            @PathVariable("id") long id,
            @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {

        customerService.updateCustomer(id, updateCustomerRequest.getName());
        Customer customer = customerService.findCustomerById(id);
        UpdateCustomerResponse updateCustomerResponse = new UpdateCustomerResponse(id, customer.getName());
        return new Result<>(updateCustomerResponse);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UpdateCustomerRequest {
        Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateCustomerResponse {
        Long id;
        private String name;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CreateCustomerRequest {
        private String name;
        private int age;
        private String phoneNumber;
        private String country;
        private String city;
        private String detailedAddress;
        private String streetAddress;
        private String zipcode;
        private String password;
        private Gender gender;
    }


    @Data
    @AllArgsConstructor
    static class findMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class CreateCustomerResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
