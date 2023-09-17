package perfumeManage.perfumeManagingSystem.repository.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    // 핵심 로직이 아닌 특정 화면에 맞는 것을 찾을 때 queryRepository 사용
    // 회면 API용 ?
    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos(Long id) { // orderDto를 참조하지 않은 이유는 repository가 controller를 참조하는 것이기 떄문
        List<OrderQueryDto> result = findOrders(id);

        result.forEach(o -> {
            List<DiffuserProductRequestQueryDto> diffuserProductRequests = findDiffuserProductRequests(o.getOrderId());
            o.setDiffuserProductRequests(diffuserProductRequests);
        });

        return result;
    }

    private List<DiffuserProductRequestQueryDto> findDiffuserProductRequests(Long orderId) {
        return em.createQuery(
                "select new perfumeManage.perfumeManagingSystem.repository.query.DiffuserProductRequestQueryDto(diffProduct.order.id, diffProduct.id, d.id, d.name, diffProduct.amount, diffProduct.deadline, diffProduct.status)" +
                        " from DiffuserProductRequest diffProduct" +
                        " join diffProduct.diffuser d" +
                        " where diffProduct.order.id = :orderId", DiffuserProductRequestQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders(Long customerId) {
        return em.createQuery(
                        // jpql을 짜도 컬렉션을 바로 넣을 수 없다
                        " select new perfumeManage.perfumeManagingSystem.repository.query.OrderQueryDto(o.id, c.name) " +
                                " from Order o" +
                                " join o.customer c" +
                                " where o.customer.id = :customerId", OrderQueryDto.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }


    public List<OrderQueryDto> findAllByDto_optimization(Long customerId) {
        // 쿼리 1
        List<OrderQueryDto> result = findOrders(customerId);

        List<Long> orderIds = result.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());

        // 쿼리 2
        Map<Long, List<DiffuserProductRequestQueryDto>> diffuserProductRquestMap = findDiffuserRequestMap(orderIds);

        result.forEach(o -> o.setDiffuserProductRequests(diffuserProductRquestMap.get(o.getOrderId())));

        // 위 코드는 쿼리를 한번 날리고 메모리에서 매칭해서 값을 세팅해준다.
        // 쿼리는 총 두번
        return result;
    }

    private Map<Long, List<DiffuserProductRequestQueryDto>> findDiffuserRequestMap(List<Long> orderIds) {
        List<DiffuserProductRequestQueryDto> diffuserProductRequests = em.createQuery("select new perfumeManage.perfumeManagingSystem.repository.query.DiffuserProductRequestQueryDto(diffProduct.order.id, diffProduct.id, d.id, d.name, diffProduct.amount, diffProduct.deadline, diffProduct.status)" +
                        " from DiffuserProductRequest diffProduct" +
                        " join diffProduct.diffuser d" +
                        " where diffProduct.order.id in :orderIds", DiffuserProductRequestQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        Map<Long, List<DiffuserProductRequestQueryDto>> diffuserProductRquestMap = diffuserProductRequests.stream()
                .collect(Collectors.groupingBy(diffuserProductRequestQueryDto -> diffuserProductRequestQueryDto.getOrderId()));
        return diffuserProductRquestMap;
    }

    public List<OrderFlatDto> findAllBy_flat(Long customerId) {
        return em.createQuery("select new perfumeManage.perfumeManagingSystem.repository.query.OrderFlatDto(o.id, o.customer.name, diffRequest.id, diff.id, diff.name, diffRequest.amount, diffRequest.deadline, diffRequest.status) " +
                        " from Order o" +
                        " join o.customer c" +
                        " join o.diffuserProductRequests diffRequest " +
                        " join diffRequest.diffuser diff " +
                        " where o.customer.id = :customerId", OrderFlatDto.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
