package perfumeManage.perfumeManagingSystem.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import perfumeManage.perfumeManagingSystem.domain.*;
        import perfumeManage.perfumeManagingSystem.service.*;

        import java.util.List;

@Controller
@RequiredArgsConstructor
public class perfumeProcessingCheckController {
    private final CustomerService customerService;
    private final CompleteRequestService completeRequestService;
    private final PerfumeProductRequestService perfumeProductRequestService;
    private final ProcessingRequestService processingRequestService;

    @GetMapping("{id}/checkAll/perfume")
    public String checkAllProcessingPerfume(@PathVariable("id") Long customerId) {
        Customer customer = customerService.findCustomer(customerId);

        // General 권한 멤버의 모든 주문 [ request, processing, complete ]

        // General 권한의 멤버의 진행 중인 주문
        if (customer.getAuth() == Auth.General) {
            // 주문한 상품
            List<PerfumeProductRequest> perfumeProductRequests = customer.getPerfumeRequests();
            for(PerfumeProductRequest perfumeProductRequest : perfumeProductRequests) {
                System.out.println(perfumeProductRequest.getName());
            }
            // 진행 증인 상품
            List<PerfumeProductRequest> perfumeProductRequestsProcessing = customer.getProcessingRequest().getPerfumeProductRequests();
            for(PerfumeProductRequest perfumeProductRequestProcessing : perfumeProductRequestsProcessing) {
                System.out.println("customer name = " + customer + " and your processing perfume is " + perfumeProductRequestProcessing.getName());
            }

            // 완료 상품
            List<PerfumeProductRequest> completeProductRequestsComplete = customer.getCompleteRequest().getPerfumeProductRequests();
            for(PerfumeProductRequest perfumeProductRequestComplete : completeProductRequestsComplete) {
                System.out.println("customer name = " + customer + " and your complete perfume is " + perfumeProductRequestComplete.getName());
            }
            return "authority/authorityPage";
        } else {

            // 매니저 권한으로 모든 주문 찾기
            List<PerfumeProductRequest> perfumeProductRequests = perfumeProductRequestService.findAllPerfumeProductRequest();
            for(PerfumeProductRequest perfumeProductRequest : perfumeProductRequests) {
                System.out.println("this is Manager authority find All perfume request " + perfumeProductRequest.getName());
            }

            // 매니저 권한으로 진행중인 주문 찾기
            List<ProcessingRequest> perfumeProductProcessingRequests = processingRequestService.findAllProcessingRequest();
            for (ProcessingRequest processingRequest : perfumeProductProcessingRequests) {
                List<PerfumeProductRequest> perfumeProductProcessingRequestsNow =  processingRequest.getPerfumeProductRequests();
                for (PerfumeProductRequest perfumeProductRequest : perfumeProductProcessingRequestsNow) {
                    System.out.println("this is Manager authority find All processing perfume request " + perfumeProductRequest.getName());
                }
            }

            // 매니저 권한으로 완성된 주문 찾기
            List<CompleteRequest> perfumeProductCompleteRequests = completeRequestService.findAllCompleteRequests();
            for (CompleteRequest completeRequest : perfumeProductCompleteRequests) {
                List<PerfumeProductRequest> perfumeProductCompletedRequests = completeRequest.getPerfumeProductRequests();
                for (PerfumeProductRequest perfumeProductRequest : perfumeProductCompletedRequests) {
                    System.out.println("this is Manager authority find All Complete perfume request " + perfumeProductRequest.getName());
                }
            }
            return "authority/authorityPage";
        }
    }
}
