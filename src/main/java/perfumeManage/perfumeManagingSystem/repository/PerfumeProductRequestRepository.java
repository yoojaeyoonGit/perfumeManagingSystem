package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;

import javax.persistence.EntityManager;


        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Repository;
        import perfumeManage.perfumeManagingSystem.domain.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.PerfumeProductRequest;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PerfumeProductRequestRepository {
    private final EntityManager em;

    public void save(PerfumeProductRequest perfumeProductRequest) {
        em.persist(perfumeProductRequest);
    }
}
