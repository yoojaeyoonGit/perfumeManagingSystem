package perfumeManage.perfumeManagingSystem.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
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
    public String checkAllProcessingPerfume(@PathVariable("id") Long customerId, Model model) {
        Customer customer = customerService.findCustomer(customerId);


        // General 권한의 멤버의 진행 중인 주문
        if (customer.getAuth() == Auth.General) {
            // 요청된 모든 주문
            List<PerfumeProductRequest> perfumeProductRequests = customer.getPerfumeRequests();
            model.addAttribute("perfumeProductRequests", perfumeProductRequests);

//            for(PerfumeProductRequest perfumeProductRequest : perfumeProductRequests) {
//                System.out.println(perfumeProductRequest.getName());
//            }

            // 진행 증인 주문
            List<PerfumeProductRequest> perfumeProductProcessingRequests = customer.getProcessingRequest().getPerfumeProductRequests();
            model.addAttribute("perfumeProductProcessingRequests", perfumeProductProcessingRequests);

//            for(PerfumeProductRequest perfumeProductRequestProcessing : perfumeProductRequestsProcessing) {
//                System.out.println("customer name = " + customer + " and your processing perfume is " + perfumeProductRequestProcessing.getName());
//            }

            // 완료 된 주문
            List<PerfumeProductRequest> perfumeProductCompleteRequests = customer.getCompleteRequest().getPerfumeProductRequests();
            model.addAttribute("perfumeProductCompleteRequests", perfumeProductCompleteRequests);

//            for(PerfumeProductRequest perfumeProductRequestComplete : completeProductRequestsComplete) {
//                System.out.println("customer name = " + customer + " and your complete perfume is " + perfumeProductRequestComplete.getName());
//            }
            return "requestList/perfumeList";
        } else {

            // 매니저 권한으로 모든 주문 찾기
            List<PerfumeProductRequest> perfumeProductRequests = perfumeProductRequestService.findAllPerfumeProductRequest();
            model.addAttribute("perfumeProductRequests", perfumeProductRequests);

//          for(PerfumeProductRequest perfumeProductRequest : perfumeProductRequests) {
//              System.out.println("this is Manager authority find All perfume request " + perfumeProductRequest.getName());
//          }

            // 여기가 해답
            Long getProcessingRequestId = customer.getProcessingRequest().getId();
            ProcessingRequest  processingRequestReady = processingRequestService.findProcessingRequest(getProcessingRequestId);
            List<PerfumeProductRequest>  perfumeProductProcessingRequests = processingRequestReady.getPerfumeProductRequests();
            model.addAttribute("perfumeProductProcessingRequests", perfumeProductProcessingRequests);


            // 매니저 권한으로 진행중인 주문 찾기
            List<ProcessingRequest> perfumeProductProcessingRequestReady = processingRequestService.findAllProcessingRequest();
            // 얘를 왜 리스트로? 어차피 ProcessingRequest는 하나인데 생각해보니 진행중인 주문 객체는 하나니까 하나만 찾아서 리스트 model.addAtt.. 해주면됨 위에 그 해답이 있음




    //        for (ProcessingRequest processingRequest : perfumeProductProcessingRequestReady) {
    //            List<PerfumeProductRequest> perfumeProductProcessingRequests =  processingRequest.getPerfumeProductRequests();
    //            model.addAttribute("perfumeProductProcessingRequests", perfumeProductProcessingRequests);
    //        }
    //
//
//            for (PerfumeProductRequest perfumeProductRequest : perfumeProductProcessingRequestsNow) {
//                System.out.println("this is Manager authority find All processing perfume request " + perfumeProductRequest.getName());
//            }





            // 매니저 권한으로 완성된 주문 찾기
            Long getCompleteRequestId = customer.getCompleteRequest().getId();
            CompleteRequest completeRequestReady = completeRequestService.findCompleteRequest(getCompleteRequestId);
            List<PerfumeProductRequest>  perfumeProductCompleteRequests = completeRequestReady.getPerfumeProductRequests();
            model.addAttribute("perfumeProductCompleteRequests", perfumeProductCompleteRequests);


//        List<CompleteRequest> perfumeProductCompleteRequestsReady = completeRequestService.findAllCompleteRequests();
//        model.addAttribute("perfumeProductCompleteRequestsReady", perfumeProductCompleteRequestsReady);
//        for (CompleteRequest completeRequest : perfumeProductCompleteRequestsReady) {
//            List<PerfumeProductRequest> perfumeProductCompletedRequests = completeRequest.getPerfumeProductRequests();
//            model.addAttribute("perfumeProductCompletedRequests", perfumeProductCompletedRequests);
//        }



//            for (PerfumeProductRequest perfumeProductRequest : perfumeProductCompletedRequests) {
//                System.out.println("this is Manager authority find All Complete perfume request " + perfumeProductRequest.getName());
//            }
//        }
        return "requestList/perfumeList";
    }
    }
}









        // General 권한 멤버의 모든 주문 [ request, processing, complete ]

        // General 권한의 멤버의 진행 중인 주문
//        if (customer.getAuth() == Auth.General) {
//            // 주문한 상품
//            List<PerfumeProductRequest> perfumeProductRequests = customer.getPerfumeRequests();
//            for(PerfumeProductRequest perfumeProductRequest : perfumeProductRequests) {
//                System.out.println(perfumeProductRequest.getName());
//            }
//            // 진행 증인 상품
//            List<PerfumeProductRequest> perfumeProductRequestsProcessing = customer.getProcessingRequest().getPerfumeProductRequests();
//            for(PerfumeProductRequest perfumeProductRequestProcessing : perfumeProductRequestsProcessing) {
//                System.out.println("customer name = " + customer + " and your processing perfume is " + perfumeProductRequestProcessing.getName());
//            }
//
//            // 완료 상품
//            List<PerfumeProductRequest> completeProductRequestsComplete = customer.getCompleteRequest().getPerfumeProductRequests();
//            for(PerfumeProductRequest perfumeProductRequestComplete : completeProductRequestsComplete) {
//                System.out.println("customer name = " + customer + " and your complete perfume is " + perfumeProductRequestComplete.getName());
//            }
//            return "authorizationPage";
