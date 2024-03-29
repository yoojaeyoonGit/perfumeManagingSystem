package perfumeManage.perfumeManagingSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.DiffuserProductRequest;
import perfumeManage.perfumeManagingSystem.domain.productionRequest.PerfumeProductRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@RequiredArgsConstructor
@Entity
@Getter @Setter
public class Customer {
    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;

    @Embedded
    private Address address;

    private String phoneNumber;

    private LocalDate signUpDate;
    @Enumerated(EnumType.STRING)
    private Auth auth;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "customer", fetch = LAZY)
    private ProcessingRequest processingRequest;

    @OneToOne(mappedBy = "customer", fetch = LAZY)
    private CompleteRequest completeRequest;

    @OneToMany(mappedBy = "customer")
    private List<PerfumeProductRequest> perfumeRequests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<DiffuserProductRequest> diffuserRequests = new ArrayList<>();

    //연관관계메소드
    public void addDiffuserProductRequest(DiffuserProductRequest diffuserProductRequest) {
        diffuserRequests.add(diffuserProductRequest);
    }

    public void addPerfumeProductRequest(PerfumeProductRequest perfumeProductRequest) {
        perfumeRequests.add(perfumeProductRequest);
    }
}
