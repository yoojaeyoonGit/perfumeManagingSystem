package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ProcessingRequest {
    @Id @GeneratedValue
    @Column(name = "ProcessingRequest_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "processingRequest")
    private List<DiffuserProductRequest> diffuserProductRequests = new ArrayList<>();

    @OneToMany(mappedBy = "processingRequest")
    private List<PerfumeProductRequest> perfumeProductRequests = new ArrayList<>();



    private int count;

    //생성 메소드
    public static ProcessingRequest createProcessingRequest(Customer customer) {
        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setCustomer(customer);
        customer.setProcessingRequest(processingRequest);
        return processingRequest;
    }


    // 연관 관계 메소드
    public void addDiffuserProcessingRequest(DiffuserProductRequest diffuserProductRequest) {
        diffuserProductRequests.add(diffuserProductRequest);
    }

    public void addPerfumeProcessingRequest(PerfumeProductRequest perfumeProductRequest) {
        perfumeProductRequests.add(perfumeProductRequest);
    }
}
