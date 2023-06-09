package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestDto;
import perfumeManage.perfumeManagingSystem.dto.PerfumeRequestStatusDetect;
import perfumeManage.perfumeManagingSystem.repository.CompleteRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.PerfumeProductRequestRepository;
import perfumeManage.perfumeManagingSystem.repository.ProcessingRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerfumeProductRequestService {

    private final PerfumeProductRequestRepository perfumeProductRequestRepository;

    private final ProcessingRequestRepository processingRequestRepository;

    private final CompleteRequestRepository completeRequestRepository;
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
    public void ChangePerfumeProductRequestStatusToProcessing(PerfumeProductRequest perfumeProductRequest, PerfumeRequestStatusDetect perfumeRequestStatusDetect) {
            System.out.println("this is Perfume name : " + perfumeProductRequest.getName());
            System.out.println("this is status : " + perfumeRequestStatusDetect.getStatus());

            perfumeProductRequest.setStatus(perfumeRequestStatusDetect.getStatus());

            Customer customer = perfumeProductRequest.getCustomer();
            ProcessingRequest processingRequest = customer.getProcessingRequest();

            if (processingRequest == null) {
                processingRequest = ProcessingRequest.createProcessingRequest(customer);
                perfumeProductRequest.setProcessingRequest(processingRequest);
                processingRequestRepository.save(processingRequest);
            }

            perfumeProductRequest.setProcessingRequest(processingRequest);
            processingRequest.addPerfumeProcessingRequest(perfumeProductRequest);
        }

    @Transactional
    public void changePerfumeProductRequestStatusToComplete(ProcessingRequest processingRequest, PerfumeProductRequest perfumeProductRequest, PerfumeRequestStatusDetect perfumeRequestStatusDetect ) {
            perfumeProductRequest.setStatus(perfumeRequestStatusDetect.getStatus());

            Customer customer = perfumeProductRequest.getCustomer();
            CompleteRequest completeRequest = customer.getCompleteRequest();

            if (completeRequest == null) {
                completeRequest = CompleteRequest.createCompleteRequest(customer);
                perfumeProductRequest.setCompleteRequest(completeRequest);
                completeRequestRepository.save(completeRequest);
            }

            List<PerfumeProductRequest> processingPerfumes = processingRequest.getPerfumeProductRequests();
    //        for(DiffuserProductRequest processingDiffuser : processingDiffusers) {
    //            if (diffuserProductRequest == processingDiffuser) {
    //                processingDiffuser.setProcessingRequest(null);
    //                processingDiffusers.remove(processingDiffuser);
    //            }
    //        }

            for (int i = 0; i < processingPerfumes.size(); i++) {
                if (perfumeProductRequest == processingPerfumes.get(i)) {
                    processingPerfumes.get(i).setProcessingRequest(null);
                    processingPerfumes.remove(processingPerfumes.get(i));
                }
            }

            perfumeProductRequest.setCompleteRequest(completeRequest);
            completeRequest.addPerfumeCompleteRequest(perfumeProductRequest);
    }



        public PerfumeProductRequest find(Long id) {
            PerfumeProductRequest perfumeProductRequest = perfumeProductRequestRepository.find(id);
            return perfumeProductRequest;
        }

        public List<PerfumeProductRequest> findAllPerfumeProductRequest() {
            return perfumeProductRequestRepository.findAll();
        }
}
