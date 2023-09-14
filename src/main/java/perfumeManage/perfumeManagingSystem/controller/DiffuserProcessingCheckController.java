package perfumeManage.perfumeManagingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.service.CompleteRequestService;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserProductRequestService;
import perfumeManage.perfumeManagingSystem.service.ProcessingRequestService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiffuserProcessingCheckController {
    private final CustomerService customerService;
    private final CompleteRequestService completeRequestService;
    private final DiffuserProductRequestService diffuserProductRequestService;
    private final ProcessingRequestService processingRequestService;

    @GetMapping("{id}/checkAll/diffuser")
    public String checkAllProcessingDiffuser(@PathVariable("id") Long customerId, Model model) {
        Customer customer = customerService.findCustomerById(customerId);
        model.addAttribute(customer);
        // General 권한 멤버의 모든 주문 [ request, processing, complete ]

        // General 권한의 멤버의 진행 중인 주문
        if (customer.getAuth() == Auth.General) {
            // 주문한 상품
            List<DiffuserProductRequest> diffuserProductRequests = customer.getDiffuserRequests();
            model.addAttribute("diffuserProductRequests", diffuserProductRequests);

            // 진행 증인 주문
            List<DiffuserProductRequest> diffuserProductProcessingRequests = customer.getProcessingRequest().getDiffuserProductRequests();
            model.addAttribute("diffuserProductProcessingRequests", diffuserProductProcessingRequests);

            // 완료 된 주문
            List<DiffuserProductRequest> diffuserProductCompleteRequests = customer.getCompleteRequest().getDiffuserProductRequests();
            model.addAttribute("diffuserProductCompleteRequests", diffuserProductCompleteRequests);

            return "requestList/diffuserList";
        } else {

            // 매니저 권한으로 모든 주문 찾기
            List<DiffuserProductRequest> diffuserProductRequests = diffuserProductRequestService.findAllDiffuserProductRequest();
            model.addAttribute("diffuserProductRequests", diffuserProductRequests);

            // 매니저 권한으로 진행중인 주문 찾기
            List<ProcessingRequest> diffuserProductProcessingRequestReady = processingRequestService.findAllProcessingRequest();
            List<DiffuserProductRequest> diffuserProductProcessingRequests = new ArrayList<>();

            for (ProcessingRequest processingDiffuserInObject : diffuserProductProcessingRequestReady) {
                List<DiffuserProductRequest> processingDiffuser = processingDiffuserInObject.getDiffuserProductRequests();
                diffuserProductProcessingRequests.addAll(processingDiffuser);

                // 위 addAll() 코드가 아래 코드 줄인 거임 ㄷ
//              for (DiffuserProductRequest diffuserProductProcessingRequest : processingDiffuser) {
//                  diffuserProductProcessingRequests.add(diffuserProductProcessingRequest);
//              }
            }
            model.addAttribute("diffuserProductProcessingRequests", diffuserProductProcessingRequests);


            // 매니저 권한으로 완성된 주문 찾기
            List<CompleteRequest> diffuserProductCompleteRequestsReady = completeRequestService.findAllCompleteRequests();
            List<DiffuserProductRequest> diffuserProductCompleteRequests = new ArrayList<>();

            // completeRequest 안에 진행 중, 완료 리스트를 만들어 놓았음
            for (CompleteRequest completeDiffuserInObject : diffuserProductCompleteRequestsReady) {
                List<DiffuserProductRequest> completeDiffuser = completeDiffuserInObject.getDiffuserProductRequests();
                for (DiffuserProductRequest diffuserCompleteRequest : completeDiffuser) {
                    diffuserProductCompleteRequests.add(diffuserCompleteRequest);
                }
            }

            model.addAttribute("diffuserProductCompleteRequests", diffuserProductCompleteRequests);
            return "requestList/diffuserList";
        }
    }
}
