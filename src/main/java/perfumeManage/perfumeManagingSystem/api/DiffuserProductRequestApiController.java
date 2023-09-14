package perfumeManage.perfumeManagingSystem.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import perfumeManage.perfumeManagingSystem.domain.Auth;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DiffuserProductRequestApiController {
    private final DiffuserProductRequestService diffuserProductRequestService;
    private final CustomerService customerService;


    @GetMapping("/api/v1/diffuserProducts/{id}")
    public Result AllProcessingDiffuser(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer.getAuth() == Auth.General) {
            List<DiffuserProductRequest> diffuserProductRequests = diffuserProductRequestService.GeneralUserDiffProduct(customer.getId());
            List<DiffuserProductRequestDto> diffuserProductRequestsDto = diffuserProductRequests.stream()
                    .map(d -> new DiffuserProductRequestDto(d))
                    .collect(Collectors.toList());
            return new Result(diffuserProductRequestsDto);

        } else {
            List<DiffuserProductRequest> diffuserProductRequests = diffuserProductRequestService.findAllDiffuserProductRequest();
            List<DiffuserProductRequestDto> diffuserProductRequestsDto = diffuserProductRequests.stream()
                    .map(d -> new DiffuserProductRequestDto(d))
                    .collect(Collectors.toList());
            return new Result(diffuserProductRequestsDto);
        }
    }


    @Data
    static class DiffuserProductRequestDto {
        private CustomerDto customer;
        private DiffuserDto diffuser;


//        public DiffuserProductRequestDto(Diffuser diffuserInfo, Customer customerInfo) {
//            customer = new CustomerDto(customerInfo.getId(), customerInfo.getName());
//            diffuser = new DiffuserDto(diffuserInfo.getName(), diffuserInfo.getRecipe());
//        }

        public DiffuserProductRequestDto(DiffuserProductRequest diffuserProductRequest) {
            customer = new CustomerDto(diffuserProductRequest.getId(), diffuserProductRequest.getCustomer().getName());
            diffuser = new DiffuserDto(diffuserProductRequest.getDiffuser().getName(), diffuserProductRequest.getDiffuser().getRecipe());
        }


    }


    @Data
    @AllArgsConstructor
    static class DiffuserDto {
        private String name;
        private String recipe;
    }

    @Data
    @AllArgsConstructor
    static class CustomerDto {
        private Long id;
        private String name;
    }



    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
