package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.service.CompleteRequestService;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;
import perfumeManage.perfumeManagingSystem.service.ProcessingRequestService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiffuserProcessingCheckController {
    private final CustomerService customerService;
    private final CompleteRequestService completeRequestService;
    private final DiffuserProductRequestService diffuserProductRequestService;
    private final ProcessingRequestService processingRequestService;

    @GetMapping("{id}/checkAll/diffuser")
    public String checkAllProcessingDiffuser(@PathVariable("id") Long customerId) {
        Customer customer = customerService.findCustomer(customerId);

        // General 권한 멤버의 모든 주문 [ request, processing, complete ]

        // General 권한의 멤버의 진행 중인 주문
        if (customer.getAuth() == Auth.General) {
            // 주문한 상품
            List<DiffuserProductRequest> diffuserProductRequests = customer.getDiffuserRequests();
            for(DiffuserProductRequest diffuserProductRequest : diffuserProductRequests) {
                System.out.println(diffuserProductRequest.getName());
            }
            // 진행 증인 상품
            List<DiffuserProductRequest> diffuserProductRequestsProcessing = customer.getProcessingRequest().getDiffuserProductRequests();
            for(DiffuserProductRequest diffuserProductRequestProcessing : diffuserProductRequestsProcessing) {
                System.out.println("customer name = " + customer + " and your processing diffuser is " + diffuserProductRequestProcessing.getName());
            }

            // 완료 상품
            List<DiffuserProductRequest> diffuserProductRequestsComplete = customer.getCompleteRequest().getDiffuserProductRequests();
            for(DiffuserProductRequest diffuserProductRequestComplete : diffuserProductRequestsComplete) {
                System.out.println("customer name = " + customer + " and your complete diffuser is " + diffuserProductRequestComplete.getName());
            }
            return "authorizationPage";
        } else {

            // 매니저 권한으로 모든 주문 찾기
            List<DiffuserProductRequest> diffuserProductRequests = diffuserProductRequestService.findAllDiffuserProductRequest();
            for(DiffuserProductRequest diffuserProductRequest : diffuserProductRequests) {
                System.out.println("this is Manager authority find All diffuser request " + diffuserProductRequest.getName());
            }

            // 매니저 권한으로 진행중인 주문 찾기
            List<ProcessingRequest> diffuserProductProcessingRequests = processingRequestService.findAllProcessingRequest();
            for (ProcessingRequest processingRequest : diffuserProductProcessingRequests) {
                List<DiffuserProductRequest> diffuserProductProcessingRequestsNow =  processingRequest.getDiffuserProductRequests();
                for (DiffuserProductRequest diffuserProductRequest :diffuserProductProcessingRequestsNow) {
                    System.out.println("this is Manager authority find All processing diffuser request " + diffuserProductRequest.getName());
                }
            }

            // 매니저 권한으로 완성된 주문 찾기
            List<CompleteRequest> diffuserProductCompleteRequests = completeRequestService.findAllCompleteRequests();
            for (CompleteRequest completeRequest : diffuserProductCompleteRequests) {
                List<DiffuserProductRequest> diffuserProductCompletedRequests = completeRequest.getDiffuserProductRequests();
                for (DiffuserProductRequest diffuserProductRequest : diffuserProductCompletedRequests) {
                    System.out.println("this is Manager authority find All Complete diffuser request " + diffuserProductRequest.getName());
                }
            }
            return "authorizationPage";
        }
    }
}
