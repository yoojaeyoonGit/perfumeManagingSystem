package perfumeManage.perfumeManagingSystem.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import perfumeManage.perfumeManagingSystem.domain.Customer;
        import perfumeManage.perfumeManagingSystem.domain.PerfumeProductRequest;
        import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestDto;
        import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestStatusDetect;
        import perfumeManage.perfumeManagingSystem.service.CustomerService;
        import perfumeManage.perfumeManagingSystem.service.PerfumeProductRequestService;

        import java.util.List;

@Controller
@RequiredArgsConstructor
public class PerfumeProductRequestController {

    private final PerfumeProductRequestService perfumeProductRequestService;
    private final CustomerService customerService;
    @GetMapping("{id}/perfumeProduct")
    public String hello(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomer(id);
        List<PerfumeProductRequest> perfumeRequests = customer.getPerfumeRequests();
        for (PerfumeProductRequest perfumeProductRequest : perfumeRequests) {
            System.out.println(perfumeProductRequest.getName() + " This is perfume name " + " for " + customer.getName());
        }
        return "hello";
    }

    @PostMapping("{id}/perfumeProduct")
    public String PerfumeRequest(@PathVariable("id") Long id, @RequestBody PerfumeRequestDto perfumeRequestDto) {
        Customer customer = customerService.findCustomer(id);
        perfumeProductRequestService.savePerfumeProductRequest(customer, perfumeRequestDto);
        return "request/perfumeRequest";
    }



    @PostMapping("{id}/perfumeProduction")
    public String ChangePerfumeRequestStatus(@PathVariable("id") Long id, @RequestBody PerfumeRequestStatusDetect perfumeRequestStatusDetect) {
        PerfumeProductRequest perfumeProductRequest = perfumeProductRequestService.find(id);
        perfumeProductRequestService.ChangePerfumeProductRequestStatus(perfumeProductRequest, perfumeRequestStatusDetect);
        return "request/diffuserRequest";
    }
}
