package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.CompleteRequest;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompleteRequestRepository {
    private final EntityManager em;

    public void save(CompleteRequest completeRequest) {
        em.persist(completeRequest);
    }

    public List<CompleteRequest> findAllCompleteRequest () {
        return em.createQuery("select d from CompleteRequest d", CompleteRequest.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
    }


    public CompleteRequest findById(Long id) {
        return em.find(CompleteRequest.class, id);
    }
}
