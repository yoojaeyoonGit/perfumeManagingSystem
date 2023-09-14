package perfumeManage.perfumeManagingSystem.domain.productionRequest;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.domain.product.Diffuser;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class DiffuserProductRequest extends ProductRequest {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "diffuser_id")
    private Diffuser diffuser;

    // 생성 메서드
    public static DiffuserProductRequest createDiffuserProductRequest(Customer customer, Diffuser diffuser, int amount, Deadline deadline) {
        DiffuserProductRequest diffuserProductRequest = new DiffuserProductRequest();
        diffuserProductRequest.setCustomer(customer);
        diffuserProductRequest.setAmount(amount);
        diffuserProductRequest.setDeadline(deadline);
        diffuserProductRequest.setDiffuser(diffuser);
        diffuserProductRequest.setStatus(ProductionStatus.REQUEST);
        return diffuserProductRequest;
    }
}
