package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.repository.CompleteRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.DiffuserProductRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.ProcessingRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiffuserProductRequestService {
    private final DiffuserProductRequestRepository diffuserProductRequestRepository;
    private final ProcessingRequestRepository processingRequestRepository;
    private final CompleteRequestRepository completeRequestRepository;


    @Transactional
    public void saveDiffuserProductRequest(Customer customer, DiffuserRequestDto diffuserRequestDto) {
        DiffuserProductRequest diffuserProductRequest = new DiffuserProductRequest();

        LocalDate deadline = LocalDate.of(diffuserRequestDto.getYear(), diffuserRequestDto.getMonth(), diffuserRequestDto.getDate());
        diffuserProductRequest.setCustomer(customer);
        diffuserProductRequest.setName(diffuserRequestDto.getName());
        diffuserProductRequest.setRecipe(diffuserRequestDto.getRecipe());
        diffuserProductRequest.setAmount(diffuserRequestDto.getAmount());
        diffuserProductRequest.setDeadline(deadline);
        diffuserProductRequest.setImage(diffuserRequestDto.getImage());
        diffuserProductRequest.setStatus(ProductionStatus.REQUEST);
        diffuserProductRequestRepository.save(diffuserProductRequest);

        customer.addDiffuserProductRequest(diffuserProductRequest);
    }

    @Transactional
    public void changeDiffuserProductRequestStatusToProcessing(DiffuserProductRequest diffuserProductRequest) {
        // Diffuser 주문 변경 시

        // 상태 변경 시 상태 별 분류 리스트에 추가를 위한 테이블 조작
        Customer customer = diffuserProductRequest.getCustomer();
        ProcessingRequest processingRequest = customer.getProcessingRequest();

        if (processingRequest == null) {
            processingRequest = ProcessingRequest.createProcessingRequest(customer);
            diffuserProductRequest.setProcessingRequest(processingRequest);
            processingRequestRepository.save(processingRequest);
        }

        diffuserProductRequest.setProcessingRequest(processingRequest);
        processingRequest.addDiffuserProcessingRequest(diffuserProductRequest);
    }


    // 구조적인 문제 발생
    // 주문 상태를 변경하는 과정에서 각각 [요청, 진행 중, 완료]를 구분하여 보여주는 페이지를 만들기 위해 테이블을 각각 상태별로 따로 구축하였음
    // 이 부븐이 구조적으로 잘못 생각한 것 같음

    // processingRequest 를 받은 이유는 진행 중인 요청의 테이블에서 지우고 위해 받았다.
    @Transactional
    public void changeDiffuserProductRequestStatusToComplete(ProcessingRequest processingRequest, DiffuserProductRequest diffuserProductRequest) {


        Customer customer = diffuserProductRequest.getCustomer();
        CompleteRequest completeRequest = customer.getCompleteRequest();

        if (completeRequest == null) {
            completeRequest = CompleteRequest.createCompleteRequest(customer);
            diffuserProductRequest.setCompleteRequest(completeRequest);
            completeRequestRepository.save(completeRequest);
        }

        List<DiffuserProductRequest> processingDiffusers = processingRequest.getDiffuserProductRequests();
//        for(DiffuserProductRequest processingDiffuser : processingDiffusers) {
//            if (diffuserProductRequest == processingDiffuser) {
//                processingDiffuser.setProcessingRequest(null);
//                processingDiffusers.remove(processingDiffuser);
//            }
//        }

        for (int i = 0; i < processingDiffusers.size(); i++) {
            if (diffuserProductRequest == processingDiffusers.get(i)) {
                processingDiffusers.get(i).setProcessingRequest(null);
                processingDiffusers.remove(processingDiffusers.get(i));
            }
        }


        diffuserProductRequest.setCompleteRequest(completeRequest);
        completeRequest.addDiffuserCompleteRequest(diffuserProductRequest);
    }

    public DiffuserProductRequest find(Long id) {
        DiffuserProductRequest diffuserProductRequest = diffuserProductRequestRepository.find(id);
        return diffuserProductRequest;
    }

    public List<DiffuserProductRequest> findAllDiffuserProductRequest() {
        return diffuserProductRequestRepository.findAll();
    }
}
