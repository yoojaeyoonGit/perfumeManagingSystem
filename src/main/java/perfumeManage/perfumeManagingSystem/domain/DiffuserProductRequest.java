package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class DiffuserProductRequest {
    @Id @GeneratedValue
    @Column(name = "diffuserProductRequest_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;

    private String recipe;

    private int amount;

    private LocalDate date;

    private String image;
}
