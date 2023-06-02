package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
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
    public void ChangeDiffuserProductRequestStatus(DiffuserProductRequest diffuserProductRequest, DiffuserRequestStatusDetect diffuserRequestStatusDetect) {
        System.out.println("this is diffuser name : " + diffuserProductRequest.getName());
        System.out.println("this is status : " + diffuserRequestStatusDetect.getStatus());

        // Diffuser 주문 변경 시

        // 주문 상태 변경
        diffuserProductRequest.setStatus(diffuserRequestStatusDetect.getStatus());

        // 상태 변경 시 상태 별 분류 리스트에 추가를 위한 테이블 조작
        Customer customer = diffuserProductRequest.getCustomer();
        ProcessingRequest processingRequest = customer.getProcessingRequest();

        if (processingRequest == null) {
            processingRequest = ProcessingRequest.createProcessingRequest(customer);
            diffuserProductRequest.setProcessingRequest(processingRequest);
            processingRequestRepository.save(processingRequest);
        }

        processingRequest.addDiffuserProcessingRequest(diffuserProductRequest);
    }

    public DiffuserProductRequest find(Long id) {
        DiffuserProductRequest diffuserProductRequest = diffuserProductRequestRepository.find(id);
        return diffuserProductRequest;
    }

    public List<DiffuserProductRequest> findAllDiffuserProductRequest() {
        return diffuserProductRequestRepository.findAll();
    }
}
