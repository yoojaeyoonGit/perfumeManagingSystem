package perfumeManage.perfumeManagingSystem.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import perfumeManage.perfumeManagingSystem.domain.*;
        import perfumeManage.perfumeManagingSystem.domain.productionRequest.PerfumeProductRequest;
        import perfumeManage.perfumeManagingSystem.service.*;

        import javax.servlet.http.HttpServletRequest;
        import java.util.ArrayList;
        import java.util.List;

@Controller
@RequiredArgsConstructor
public class perfumeProcessingCheckController {
    private final CustomerService customerService;
    private final CompleteRequestService completeRequestService;
    private final PerfumeProductRequestService perfumeProductRequestService;
    private final ProcessingRequestService processingRequestService;




    @GetMapping("{id}/checkAll/perfume")
    public String checkAllProcessingPerfume(@PathVariable("id") Long customerId, Model model, HttpServletRequest httpServletRequest) {
        try {
            Customer customer = customerService.findCustomerById(customerId);
            model.addAttribute(customer);

            // General 권한의 멤버의 진행 중인 주문
            if (customer.getAuth() == Auth.General) {
                // 요청된 모든 주문
                List<PerfumeProductRequest> perfumeProductRequests = customer.getPerfumeRequests();
                model.addAttribute("perfumeProductRequests", perfumeProductRequests);

                // 진행 증인 주문
                List<PerfumeProductRequest> perfumeProductProcessingRequests = customer.getProcessingRequest().getPerfumeProductRequests();
                model.addAttribute("perfumeProductProcessingRequests", perfumeProductProcessingRequests);


                // 완료 된 주문
                List<PerfumeProductRequest> perfumeProductCompleteRequests = customer.getCompleteRequest().getPerfumeProductRequests();
                model.addAttribute("perfumeProductCompleteRequests", perfumeProductCompleteRequests);

                return "requestList/perfumeList";
            } else {

                // 매니저 권한으로 모든 주문 찾기
                List<PerfumeProductRequest> perfumeProductRequests = perfumeProductRequestService.findAllPerfumeProductRequest();
                model.addAttribute("perfumeProductRequests", perfumeProductRequests);

    //            Long getProcessingRequestId = customer.getProcessingRequest().getId();
    //            ProcessingRequest  processingRequestReady = processingRequestService.findProcessingRequest(getProcessingRequestId);
    //            List<PerfumeProductRequest>  perfumeProductProcessingRequests = processingRequestReady.getPerfumeProductRequests();
    //            model.addAttribute("perfumeProductProcessingRequests", perfumeProductProcessingRequests);



                // 매니저 권한으로 진행중인 주문 찾기
                List<ProcessingRequest> perfumeProductProcessingRequestReady = processingRequestService.findAllProcessingRequest();
                List<PerfumeProductRequest> perfumeProductProcessingRequests = new ArrayList<>();

                for (ProcessingRequest processingPerfume1 : perfumeProductProcessingRequestReady) {
                    List<PerfumeProductRequest> processingPerfume2 = processingPerfume1.getPerfumeProductRequests();
                    for (PerfumeProductRequest processingPerfume3 : processingPerfume2) {
                        perfumeProductProcessingRequests.add(processingPerfume3);
                    }
                }
                model.addAttribute("perfumeProductProcessingRequests", perfumeProductProcessingRequests);
                // 얘를 왜 리스트로? 어차피 ProcessingRequest는 하나인데 생각해보니 진행중인 주문 객체는 하나니까 하나만 찾아서 리스트 model.addAtt.. 해주면됨 위에 그 해답이 있음


                // 매니저 권한으로 완성된 주문 찾기
                List<CompleteRequest> perfumeProductCompleteRequestsReady = completeRequestService.findAllCompleteRequests();
                List<PerfumeProductRequest> perfumeProductCompleteRequests = new ArrayList<>();

                for (CompleteRequest completePerfume : perfumeProductCompleteRequestsReady) {
                    List<PerfumeProductRequest> completedPerfume = completePerfume.getPerfumeProductRequests();
                    for (PerfumeProductRequest completePerfumeRequest : completedPerfume) {
                        perfumeProductCompleteRequests.add(completePerfumeRequest);
                    }
                }

                model.addAttribute("perfumeProductCompleteRequests", perfumeProductCompleteRequests);
                return "requestList/perfumeList";
            }
        } catch (Exception e) {

        }
        return null;
    }
}
