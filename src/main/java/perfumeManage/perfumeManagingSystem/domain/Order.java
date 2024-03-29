package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.PerfumeProductRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<DiffuserProductRequest> diffuserProductRequests = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<PerfumeProductRequest> perfumeProductRequests = new ArrayList<>();

    public void addDiffuserProductRequest(DiffuserProductRequest diffuserProductRequest) {
        diffuserProductRequests.add(diffuserProductRequest);
        diffuserProductRequest.setOrder(this);
    }
}
