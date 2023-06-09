package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.Customer;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final EntityManager em;
    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class) // 첫 번째 JPQL 사용, 두 번째 반환타입
                .getResultList();
        //SQL은 테이블에 대상으로 쿼리하는데 JPQL은 엔티티 객체를 대상으로 쿼리한다.
    }


    public List<Customer> findByName(String name){
        return em.createQuery("select m from Customer m where m.name = :name", Customer.class)
                .setParameter("name",name)
                .getResultList();
    }
}
