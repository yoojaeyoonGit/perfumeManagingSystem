package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.dto.DiffuserRequestDto;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class DiffuserProductRequest {
    @Id @GeneratedValue
    @Column(name = "diffuserProductRequest_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processingRequest_id")
    private ProcessingRequest processingRequest;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "completeRequest_id")
    private CompleteRequest completeRequest;


    private String name;

    private String recipe;

    private int amount;

    private Deadline deadline;
    private String image;

    @Enumerated(EnumType.STRING)
    private ProductionStatus status;


//    public static DiffuserProductRequest diffuserRequestCreate(Customer customer, DiffuserRequestDto diffuserRequestDto) {
//        for(DiffuserProductRequest diffuserProductRequest1 : )
//        return diffuserProductRequest;
//    }

    // 생성 메서드
    public static DiffuserProductRequest createDiffuserProductRequest(String name, Customer customer, String recipe, int amount, Deadline deadline, String image) {
        DiffuserProductRequest diffuserProductRequest = new DiffuserProductRequest();
        diffuserProductRequest.setCustomer(customer);
        diffuserProductRequest.setName(name);
        diffuserProductRequest.setRecipe(recipe);
        diffuserProductRequest.setAmount(amount);
        diffuserProductRequest.setDeadline(deadline);
        diffuserProductRequest.setImage(image);
        diffuserProductRequest.setStatus(ProductionStatus.REQUEST);
        return diffuserProductRequest;
    }
}
