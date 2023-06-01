package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.Deadline;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.PerfumeProductRequest;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;
import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestDto;
import perfumeManage.perfumeManagingSystem.repository.DiffuserProductRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.PerfumeProductRequestRepository;

import java.time.LocalDate;

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

        customer.addPerfumeProductRequest(perfumeProductRequest);

        perfumeProductRequestRepository.save(perfumeProductRequest);
    }
}
