package perfumeManage.perfumeManagingSystem.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.domain.product.Diffuser;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.repository.OrderRepository;
import perfumeManage.perfumeManagingSystem.repository.query.DiffuserProductRequestQueryDto;
import perfumeManage.perfumeManagingSystem.repository.query.OrderFlatDto;
import perfumeManage.perfumeManagingSystem.repository.query.OrderQueryDto;
import perfumeManage.perfumeManagingSystem.repository.query.OrderQueryRepository;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserService;
import perfumeManage.perfumeManagingSystem.service.OrderService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;
    private final CustomerService customerService;

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final DiffuserService diffuserService;


    @GetMapping("api/v1/orders/{id}")
    public ResultMany findByJoinFetch(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer.getAuth() == Auth.General) {
            List<Order> orders = orderRepository.findByJoinFetchByAuth();
            return getOrderDtoList(orders);
        } else {
            List<Order> orders = orderRepository.findAllOrder();
            return getOrderDtoList(orders);
        }
    }

    @GetMapping("api/v2/orders/{id}") // batch_fetch_size 검색용
    public ResultMany findByBatchFetch(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer.getAuth() == Auth.General) {
            List<Order> orders = orderService.findByAuth(customer.getId());
            return getOrderDtoList(orders);
        } else {
            List<Order> orders = orderRepository.findAllOrder();
            return getOrderDtoList(orders);
        }
    }

    @GetMapping("api/v3/orders/{id}")  // N + 1 문제
    public ResultMany ordersV3(@PathVariable("id") Long id) { // orderDto를 참조하지 않은 이유는
        List<OrderQueryDto> orderQueryDtos = orderQueryRepository.findOrderQueryDtos(id);
        return new ResultMany<>(orderQueryDtos.size(), orderQueryDtos);
    }


    @GetMapping("api/v4/orders/{id}")
    public ResultMany ordersV4(@PathVariable("id") Long id) {
        List<OrderQueryDto> orderQueryDtos = orderQueryRepository.findAllByDto_optimization(id);
        return new ResultMany<>(orderQueryDtos.size(), orderQueryDtos);
    }

//    @GetMapping("api/v5/orders/{id}")
//    public ResultMany ordersV5(@PathVariable("id") Long id) {
//        List<OrderFlatDto> orderQueryDtos = orderQueryRepository.findAllBy_flat(id);
//
//        orderQueryDtos.stream()
//                .collect(Collectors.groupingBy( o -> new OrderQueryDto(o.getOrderId(), o.getCustomerName()),
//                                Collectors.mapping(o -> new DiffuserProductRequestQueryDto(o.getOrderId(),  o.getDiffuserProductRequestId(),  o.getDiffuserId(), o.getDiffuserName(), o.getAmount(), o.getDeadline(), o.getStatus()), Collectors.toList()
//                                )).entrySet().stream()
//                        .map(e -> new OrderQueryDto(e.getKey().getOrderId(),
//                                e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
//                                e.getKey().getAddress(), e.getValue()))
//                        .collect(Collectors.toList());
//        return new ResultMany<>(orderQueryDtos.size(), orderQueryDtos);
//    }

        @PostMapping("api/orders/{id}")
    public Result createOrder(@PathVariable("id") Long id,
                              @RequestBody @Valid List<OrderRequest> orderRequest) {
        Customer customer = customerService.findCustomerById(id);
        Order order = new Order();
        order.setCustomer(customer);
        orderService.createOrder(order);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderRequest request : orderRequest) {
            Diffuser diffuser = diffuserService.findById(request.getDiffuserId());
            Deadline deadline = new Deadline(request.getYear(), request.getMonth(), request.getDate());
            orderService.createDiffRequest(order, diffuser, deadline, request.getAmount());
            OrderDto orderDto = new OrderDto(order);
            orderDtoList.add(orderDto);
        }
        return new Result<>(orderDtoList);
    }

    @Data
    static class OrderDto {
        private Long id;
        private CustomerDto customer;
        private List<DiffuserProductRequestDto> diffuserProductRequest;
        public OrderDto (Order order) {
            id = order.getId();
            customer = new CustomerDto(order.getCustomer());
            diffuserProductRequest = order.getDiffuserProductRequests().stream()
                    .map(diff -> new DiffuserProductRequestDto(diff))
                    .collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class OrderRequest {
        private Long diffuserId;
        private int amount;
        private int year;
        private int month;
        private int date;
    }

//    @Data
//    @AllArgsConstructor
//    static class OrderResponse {
//        private LocalDate localDate;
//        private Long id;
//        private DiffuserDto diffuserDto;
//
//        public OrderResponse () {
//            localDate = LocalDate.now();
//            id =
//        }
//    }

    @Data
    static class CustomerDto {
        private Long id;
        private String name;
        public CustomerDto (Customer customer) {
            id = customer.getId();
            name = customer.getName();
        }
    }

    @Data
    static class DiffuserProductRequestDto {

        private Long id;

        private DiffuserDto diffuser;

        private int amount;

        private Deadline deadline;

        private ProductionStatus status;

        public DiffuserProductRequestDto (DiffuserProductRequest diffuserProductRequest) {
            id = diffuserProductRequest.getId();
            diffuser = new DiffuserDto(diffuserProductRequest.getDiffuser());
            amount = diffuserProductRequest.getAmount();
            deadline = diffuserProductRequest.getDeadline();
            status = diffuserProductRequest.getStatus();
        }
    }

    @Data
    @AllArgsConstructor
    static class DiffuserDto {
        private String name;
        private String recipe;

        public DiffuserDto (Diffuser diffuser) {
            name = diffuser.getName();
            recipe = diffuser.getRecipe();
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


    @Data
    @AllArgsConstructor
    static class ResultMany<T> {
        private int count;
        private T data;
    }


    private ResultMany getOrderDtoList(List<Order> orders) {
        List<OrderDto> orderDtos = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        return new ResultMany<>(orders.size(), orderDtos);
    }
}
