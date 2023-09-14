package perfumeManage.perfumeManagingSystem.domain.productionRequest;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.*;
import perfumeManage.perfumeManagingSystem.domain.product.Diffuser;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class ProductRequest {
    @Id
    @GeneratedValue
    @Column(name = "diffuserProductRequest_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processingRequest_id")
    private ProcessingRequest processingRequest;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "completeRequest_id")
    private CompleteRequest completeRequest;

    private int amount;

    private Deadline deadline;

    @Enumerated(EnumType.STRING)
    private ProductionStatus status;

}
