package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.PerfumeProductRequest;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProcessingRequestRepository {
    private final EntityManager em;

    public void save(ProcessingRequest processingRequest) {
        em.persist(processingRequest);
    }

    public ProcessingRequest findById(Long id) {
        return em.find(ProcessingRequest.class, id);
    }
}
