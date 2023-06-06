package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;
import perfumeManage.perfumeManagingSystem.repository.ProcessingRequestRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProcessingRequestService {

    private final ProcessingRequestRepository processingRequestRepository;


    public ProcessingRequest findProcessingRequest(Long id) {
        return processingRequestRepository.findById(id);
    }

    public List<ProcessingRequest> findAllProcessingRequest() {
        return processingRequestRepository.findAllProcessingProduct();
    }

}
