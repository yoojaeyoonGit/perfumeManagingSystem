package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;

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

    @ManyToOne
    @JoinColumn(name = "processingRequest_id")
    private ProcessingRequest processingRequest;

    private String name;

    private String recipe;

    private int amount;

    private LocalDate deadline;
    private String image;

    @Enumerated(EnumType.STRING)
    private ProductionStatus status;


//    public static DiffuserProductRequest diffuserRequestCreate(Customer customer, DiffuserRequestDto diffuserRequestDto) {
//        for(DiffuserProductRequest diffuserProductRequest1 : )
//        return diffuserProductRequest;
//    }
}
