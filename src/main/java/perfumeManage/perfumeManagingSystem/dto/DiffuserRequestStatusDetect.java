package perfumeManage.perfumeManagingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import perfumeManage.perfumeManagingSystem.domain.ProductionStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiffuserRequestStatusDetect {
    @Enumerated(EnumType.STRING)
    private ProductionStatus status;
}
