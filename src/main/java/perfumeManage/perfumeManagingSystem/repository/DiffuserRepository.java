package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.CompleteRequest;
import perfumeManage.perfumeManagingSystem.domain.Diffuser;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiffuserRepository {
    private final EntityManager em;

    public void save(Diffuser diffuser) {
        em.persist(diffuser);
    }

    public void findById(Long id) {
        em.find(Diffuser.class, id);
    }

    public List<Diffuser> findAllCompleteRequest () {
        return em.createQuery("select d from Diffuser d", Diffuser.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
    }
}
