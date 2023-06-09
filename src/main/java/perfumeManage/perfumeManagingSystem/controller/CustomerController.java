package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.dto.CustomerDto;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;
import perfumeManage.perfumeManagingSystem.service.PerfumeProductRequestService;
import perfumeManage.perfumeManagingSystem.service.ProcessingRequestService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    private final DiffuserProductRequestService diffuserProductRequestService;

    private final PerfumeProductRequestService perfumeProductRequestService;

    private final ProcessingRequestService processingRequestService;


    @GetMapping("/customer")
    public String customers (Model model) {
        List<Customer> customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        return "customer/customerList";
    }


    @GetMapping("{id}/main")
    public String main (@PathVariable("id") Long id) {
        List<Customer> customers = customerService.findAllCustomer();
        for (Customer customer : customers) {
            System.out.println("Introducing customers : " +customer.getName());
        }

        List <DiffuserProductRequest> diffuserProductRequests = diffuserProductRequestService.findAllDiffuserProductRequest();
        for (DiffuserProductRequest diffuserProductRequest : diffuserProductRequests) {
            System.out.println("Introducing Our diffusers : " + diffuserProductRequest.getName());
        }

        List <PerfumeProductRequest> perfumeProductRequests = perfumeProductRequestService.findAllPerfumeProductRequest();
        for (PerfumeProductRequest perfumeProductRequest : perfumeProductRequests) {
            System.out.println("Introducing Our perfumes : " + perfumeProductRequest.getName());
        }


        ProcessingRequest processingRequest =  processingRequestService.findProcessingRequest(id);
        List<DiffuserProductRequest> diffuserProductRequestsFromProcessing =  processingRequest.getDiffuserProductRequests();
        List<PerfumeProductRequest> perfumeProductRequestsFromProcessing =  processingRequest.getPerfumeProductRequests();


        for (DiffuserProductRequest diffuserProductRequest : diffuserProductRequestsFromProcessing) {
            System.out.println("This is Processing diffusers : " + diffuserProductRequest.getName());
        }

        for (PerfumeProductRequest perfumeProductRequest : perfumeProductRequestsFromProcessing) {
            System.out.println("This is Processing perfume : " + perfumeProductRequest.getName());
        }
        return "customer/customer";
    }

    @GetMapping("/customer/new")
    public String Join(Model model){
        model.addAttribute("customerDto", new CustomerDto());
        return "customer/createCustomerForm";
    }


    @PostMapping("/customer/new")
    public String signIn(CustomerDto customerDto) {
        customerDto.setAuth(Auth.General);
        customerService.saveCustomer(customerDto);
        return "redirect:/login";
    }
}
