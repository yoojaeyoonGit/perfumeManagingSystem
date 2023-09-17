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
        return em.createQuery("select distinct o from Order o" +
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

    public List<Order> findAllOrder() {
        return em.createQuery("select o from Order o" +
                        " join fetch o.customer", Order.class)
                        //ToOne  관계는 fetch join 걸어주는게 좋음
                        // row 수를 증가 시키지 않기 떄문에

                .getResultList();
    }

    public void save(Order order) {
        em.persist(order);
    }
}
