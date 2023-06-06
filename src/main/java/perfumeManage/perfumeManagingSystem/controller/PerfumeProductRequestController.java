package perfumeManage.perfumeManagingSystem.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import perfumeManage.perfumeManagingSystem.domain.Customer;
        import perfumeManage.perfumeManagingSystem.domain.PerfumeProductRequest;
        import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
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
        return "red";
    }

    @PostMapping("{id}/perfumeProduct")
    public String PerfumeRequest(@PathVariable("id") Long id, @RequestBody PerfumeRequestDto perfumeRequestDto) {
        Customer customer = customerService.findCustomer(id);
        perfumeProductRequestService.savePerfumeProductRequest(customer, perfumeRequestDto);
        return "request/perfumeRequest";
    }



    @PutMapping ("{id}/perfumeProductionProcessing")
    public String ChangePerfumeRequestStatusToProcessing(@PathVariable("id") Long id, @RequestBody PerfumeRequestStatusDetect perfumeRequestStatusDetect) {
        PerfumeProductRequest perfumeProductRequest = perfumeProductRequestService.find(id);
        perfumeProductRequestService.ChangePerfumeProductRequestStatusToProcessing(perfumeProductRequest, perfumeRequestStatusDetect);
        return "request/diffuserRequest";
    }

    @PutMapping("{id}/perfumeProductionComplete")
    public String ChangPerfumeRequestStatusToComplete(@PathVariable("id") Long id, @RequestBody PerfumeRequestStatusDetect perfumeRequestStatusDetect) {
        try {

            PerfumeProductRequest perfumeProductRequest = perfumeProductRequestService.find(id);

            ProcessingRequest processingRequest = perfumeProductRequest.getProcessingRequest();
            System.out.println(processingRequest + " 없지?");
            perfumeProductRequestService.changePerfumeProductRequestStatusToComplete (processingRequest, perfumeProductRequest, perfumeRequestStatusDetect);
            return "request/diffuserRequest";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("hello error :" + e);
            return "red";
        }
    }
}
