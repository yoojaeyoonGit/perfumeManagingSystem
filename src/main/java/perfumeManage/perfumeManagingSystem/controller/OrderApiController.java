package perfumeManage.perfumeManagingSystem.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.service.CustomerService;
import perfumeManage.perfumeManagingSystem.service.DiffuserService;
import perfumeManage.perfumeManagingSystem.service.OrderService;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;
    private final CustomerService customerService;

    private final DiffuserService diffuserService;

    @GetMapping("api/orders/{id}")
    public ResultMany findByAuth(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer.getAuth() == Auth.General) {
            List<Order> orders = orderService.findByAuth(customer.getId());
            return getOrderDtoList(orders);
        } else {
            List<Order> orders = orderService.findAll();
            return getOrderDtoList(orders);
        }
    }


    @PostMapping("api/orders/{id}")
    public Result createOrder(@PathVariable("id") Long id,
                              @RequestBody OrderRequest orderRequest) {
        Customer customer = customerService.findCustomerById(id);
        Diffuser diffuser = diffuserService.findById(orderRequest.getDiffuserId());

        Deadline deadline = new Deadline(orderRequest.getYear(), orderRequest.getMonth(), orderRequest.getDate());
        Order order = orderService.createOrder(customer, diffuser, deadline, orderRequest.getAmount());

        OrderDto orderDto = new OrderDto(order);

        return new Result(orderDto);
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
        return new ResultMany(orders.size(), orderDtos);
    }
}
