package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestDto;
import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.repository.DiffuserProductRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.PerfumeProductRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerfumeProductRequestService {

    private final PerfumeProductRequestRepository perfumeProductRequestRepository;

    @Transactional
    public void savePerfumeProductRequest(Customer customer, PerfumeRequestDto perfumeRequestDto) {

        PerfumeProductRequest perfumeProductRequest = new PerfumeProductRequest();
        LocalDate deadline = LocalDate.of(perfumeRequestDto.getYear(), perfumeRequestDto.getMonth(), perfumeRequestDto.getDate());
        perfumeProductRequest.setCustomer(customer);
        perfumeProductRequest.setName(perfumeRequestDto.getName());
        perfumeProductRequest.setRecipe(perfumeRequestDto.getRecipe());
        perfumeProductRequest.setAmount(perfumeRequestDto.getAmount());
        perfumeProductRequest.setDeadline(deadline);
        perfumeProductRequest.setImage(perfumeRequestDto.getImage());
        perfumeProductRequest.setStatus(ProductionStatus.REQUEST);

        customer.addPerfumeProductRequest(perfumeProductRequest);

        perfumeProductRequestRepository.save(perfumeProductRequest);
    }

        @Transactional
        public void ChangePerfumeProductRequestStatus(PerfumeProductRequest perfumeProductRequest, PerfumeRequestStatusDetect
        perfumeRequestStatusDetect) {
            System.out.println("this is Perfume name : " + perfumeProductRequest.getName());
            System.out.println("this is status : " + perfumeRequestStatusDetect.getStatus());
            perfumeProductRequest.setStatus(perfumeRequestStatusDetect.getStatus());
        }

        public PerfumeProductRequest find(Long id) {
            PerfumeProductRequest perfumeProductRequest = perfumeProductRequestRepository.find(id);
            return perfumeProductRequest;
        }

        public List<PerfumeProductRequest> findAllDiffuserProductRequest() {
            return perfumeProductRequestRepository.findAll();
        }
}
