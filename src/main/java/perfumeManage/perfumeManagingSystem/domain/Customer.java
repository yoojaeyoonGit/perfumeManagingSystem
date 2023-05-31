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

    @OneToMany(mappedBy = "customer")
    private List<PerfumeProductRequest> perfumeRequest = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<PerfumeProductRequest> DiffuserRequest = new ArrayList<>();
}
