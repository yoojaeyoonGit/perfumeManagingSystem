package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToOne(mappedBy = "customer")
    private ProcessingRequest processingRequest;

    @OneToOne(mappedBy = "customer")
    private CompleteRequest completeRequest;

    @OneToMany(mappedBy = "customer")
    private List<PerfumeProductRequest> perfumeRequests = new ArrayList<>();

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
