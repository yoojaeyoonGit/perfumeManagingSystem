package perfumeManage.perfumeManagingSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.repository.OrderRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final DiffuserProductRequestService diffuserProductRequestService;

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Customer customer, Diffuser diffuser, Deadline deadline, int amount) {
        Order order = new Order();
        order.setCustomer(customer);

        DiffuserProductRequest diffuserProductRequest = new DiffuserProductRequest();

        diffuserProductRequest.setOrder(order);
        diffuserProductRequest.setDiffuser(diffuser);
        diffuserProductRequest.setAmount(amount);
        diffuserProductRequest.setOrder(order);
        diffuserProductRequest.setDeadline(deadline);
        diffuserProductRequest.setStatus(ProductionStatus.REQUEST);
        order.addDiffuserProductRequest(diffuserProductRequest);


        diffuserProductRequestService.saveDiffuserProductRequest(diffuserProductRequest);
        orderRepository.save(order);

        return order;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByAuth(Long id) {
        return orderRepository.findByAuth(id);
    }
}
