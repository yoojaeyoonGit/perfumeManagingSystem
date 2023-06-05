package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.CompleteRequest;
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

    public List<ProcessingRequest> findAllProcessingProduct() {
        return em.createQuery("select p from ProcessingRequest p", ProcessingRequest.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
    }
}
