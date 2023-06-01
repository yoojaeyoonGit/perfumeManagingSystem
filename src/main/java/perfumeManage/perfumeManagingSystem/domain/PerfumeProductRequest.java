package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class PerfumeProductRequest {
    @Id @GeneratedValue
    @Column(name = "perfumeProductRequest")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;

    private String recipe;

    private int amount;

    private LocalDate deadline;

    private String image;
}
