package perfumeManage.perfumeManagingSystem.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.Customer;

import javax.persistence.EntityManager;

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
}
