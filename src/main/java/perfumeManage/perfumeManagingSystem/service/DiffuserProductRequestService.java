package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.Deadline;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.repository.DiffuserProductRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiffuserProductRequestService {
    private final DiffuserProductRequestRepository diffuserProductRequestRepository;

    @Transactional
    public void saveDiffuserProductRequest(Customer customer, DiffuserRequestDto diffuserRequestDto) {
        DiffuserProductRequest diffuserProductRequest = DiffuserProductRequest.diffuserRequestCreate(customer, diffuserRequestDto);
        diffuserProductRequestRepository.save(diffuserProductRequest);
    }

    public List<DiffuserProductRequest> findAllDiffuserProductRequest() {
        return diffuserProductRequestRepository.findAll();
    }
}
