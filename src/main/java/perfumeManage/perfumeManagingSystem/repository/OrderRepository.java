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

    public List<Order> findByJoinFetchByAuth() {
        return em.createQuery("select o from Order o" +
                        " join fetch o.customer c" +
                        " join fetch o.diffuserProductRequests dr" +
                        " join fetch dr.diffuser diff", Order.class)
                .getResultList();
    }

    public List<Order> findByAuth(Long id) {
        return em.createQuery(" select o from Order o  " +
                "where  o.customer.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public void save(Order order){
        em.persist(order);
    }
}
