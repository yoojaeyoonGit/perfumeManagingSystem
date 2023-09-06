package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.Diffuser;
import perfumeManage.perfumeManagingSystem.domain.Perfume;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerfumeRepository {
    private final EntityManager em;

    public void save(Perfume perfume) {
        em.persist(perfume);
    }

    public void findById(Long id) {
        em.find(Perfume.class, id);
    }

    public List<Perfume> findAllCompleteRequest () {
        return em.createQuery("select p from Perfume p", Perfume.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
    }
}