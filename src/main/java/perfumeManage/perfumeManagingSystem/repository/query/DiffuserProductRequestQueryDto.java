package perfumeManage.perfumeManagingSystem.repository.query;

import lombok.Data;
import perfumeManage.perfumeManagingSystem.domain.Deadline;
import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class DiffuserProductRequestQueryDto {

    private Long orderId;

    private Long diffuserProductRequestId;

    private Long diffuserId;
    private String diffuserName;

    private int amount;

    private Deadline deadline;

    private ProductionStatus status;

    public DiffuserProductRequestQueryDto(Long orderId, Long diffuserProductRequestId, Long diffuserId, String diffuserName, int amount, Deadline deadline, ProductionStatus status) {
        this.orderId = orderId;
        this.diffuserProductRequestId = diffuserProductRequestId;
        this.diffuserName = diffuserName;
        this.diffuserId = diffuserId;
        this.amount = amount;
        this.deadline = deadline;
        this.status = status;
    }
}


