package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.ProcessingRequest;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProcessingRequestRepository {
    private final EntityManager em;

    public void save(ProcessingRequest processingRequest) {
        em.persist(processingRequest);
    }
}
