package perfumeManage.perfumeManagingSystem.repository.query;

import lombok.Data;
import perfumeManage.perfumeManagingSystem.domain.Deadline;
import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;

@Data
public class OrderFlatDto {


    private Long orderId;

    private String customerName;
    private Long diffuserProductRequestId;

    private Long diffuserId;
    private String diffuserName;

    private int amount;

    private Deadline deadline;

    private ProductionStatus status;

    public OrderFlatDto(Long orderId, String customerName, Long diffuserProductRequestId, Long diffuserId, String diffuserName, int amount, Deadline deadline, ProductionStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.diffuserProductRequestId = diffuserProductRequestId;
        this.diffuserId = diffuserId;
        this.diffuserName = diffuserName;
        this.amount = amount;
        this.deadline = deadline;
        this.status = status;
    }

}
