package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.Customer;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiffuserProductRequestRepository {
    private final EntityManager em;

    public void save(DiffuserProductRequest diffuserProductRequest) {
        em.persist(diffuserProductRequest);
    }

    public List<DiffuserProductRequest> findAll() {
        return em.createQuery("select p from DiffuserProductRequest p", DiffuserProductRequest.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
    }
}
