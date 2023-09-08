package perfumeManage.perfumeManagingSystem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import perfumeManage.perfumeManagingSystem.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByAuth(Long id) {
        return em.createQuery(" select o from Order o  " +
                "where  o.customer.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList();
    }
    public void save(Order order){
        em.persist(order);
    }
}
