package perfumeManage.perfumeManagingSystem.repository.query;

import lombok.Data;
import perfumeManage.perfumeManagingSystem.domain.Customer;

import java.util.List;

@Data
public class OrderQueryDto {
    private Long orderId;

    private String customerName;

    private List<DiffuserProductRequestQueryDto> diffuserProductRequests;

    private List<PerfumeProductRequestQueryDto> perfumeProductRequests;

    public OrderQueryDto(Long orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

}