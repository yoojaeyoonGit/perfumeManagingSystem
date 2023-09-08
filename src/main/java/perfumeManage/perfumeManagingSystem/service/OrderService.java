package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import perfumeManage.perfumeManagingSystem.domain.Order;
import perfumeManage.perfumeManagingSystem.repository.OrderRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findByAuth(Long id) {
        return orderRepository.findByAuth(id);
    }
}
