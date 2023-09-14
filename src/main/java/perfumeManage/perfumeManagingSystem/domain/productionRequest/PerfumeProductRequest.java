package perfumeManage.perfumeManagingSystem.domain.productionRequest;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.domain.product.Diffuser;
import perfumeManage.perfumeManagingSystem.domain.product.Perfume;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class PerfumeProductRequest extends ProductRequest {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public static PerfumeProductRequest createPerfumeProductRequest (Customer customer, Perfume perfume, int amount, Deadline deadline) {
        PerfumeProductRequest perfumeProductRequest = new PerfumeProductRequest();
        perfumeProductRequest.setCustomer(customer);
        perfumeProductRequest.setAmount(amount);
        perfumeProductRequest.setDeadline(deadline);
        perfumeProductRequest.setPerfume(perfume);
        perfumeProductRequest.setStatus(ProductionStatus.REQUEST);
        return perfumeProductRequest;
    }
}
