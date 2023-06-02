package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import perfumeManage.perfumeManagingSystem.domain.PerfumeProductRequest;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerfumeProductRequestRepository {
    private final EntityManager em;

    public void save(PerfumeProductRequest perfumeProductRequest) {
        em.persist(perfumeProductRequest);
    }

    public PerfumeProductRequest find(Long perfumeProductRequestId) {
        return em.find(PerfumeProductRequest.class, perfumeProductRequestId);
    }


    public List<PerfumeProductRequest> findAll() {
        return em.createQuery("select p from PerfumeProductRequest p", PerfumeProductRequest.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
    }
}
