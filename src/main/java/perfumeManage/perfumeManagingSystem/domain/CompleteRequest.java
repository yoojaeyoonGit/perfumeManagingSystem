package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class CompleteRequest {
        @Id @GeneratedValue
        @Column(name = "completeRequest_id")
        private Long id;

        @OneToOne(fetch = LAZY)
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @OneToMany(mappedBy = "completeRequest")
        private List<DiffuserProductRequest> diffuserProductRequests = new ArrayList<>();

        @OneToMany(mappedBy = "completeRequest")
        private List<PerfumeProductRequest> perfumeProductRequests = new ArrayList<>();

        private int count;

        //생성 메소드
        public static CompleteRequest createCompleteRequest(Customer customer) {
            CompleteRequest completeRequest = new CompleteRequest();
            completeRequest.setCustomer(customer);
            customer.setCompleteRequest(completeRequest);
            return completeRequest;
        }


        //연관관계 메소드
        public void addDiffuserCompleteRequest(DiffuserProductRequest diffuserProductRequest) {
            diffuserProductRequests.add(diffuserProductRequest);
        }

        public void addPerfumeCompleteRequest(PerfumeProductRequest perfumeProductRequest) {
            perfumeProductRequests.add(perfumeProductRequest);
        }
    }
