package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.CompleteRequest;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CompleteRequestRepository {
    private final EntityManager em;

    public void save(CompleteRequest completeRequest) {
        em.persist(completeRequest);
    }
}
