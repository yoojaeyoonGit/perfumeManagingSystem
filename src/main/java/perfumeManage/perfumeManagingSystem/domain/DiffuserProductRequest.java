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

    private String name;

    private String recipe;

    private int amount;

    private LocalDate deadline;
    private String image;

    public static DiffuserProductRequest diffuserRequestCreate(Customer customer, DiffuserRequestDto diffuserRequestDto) {
        DiffuserProductRequest diffuserProductRequest = new DiffuserProductRequest();

        LocalDate deadline = LocalDate.of(diffuserRequestDto.getYear(), diffuserRequestDto.getMonth(), diffuserRequestDto.getDate());
        diffuserProductRequest.setCustomer(customer);
        diffuserProductRequest.setName(diffuserRequestDto.getName());
        diffuserProductRequest.setRecipe(diffuserRequestDto.getRecipe());
        diffuserProductRequest.setAmount(diffuserRequestDto.getAmount());
        diffuserProductRequest.setDeadline(deadline);
        diffuserProductRequest.setImage(diffuserRequestDto.getImage());

//        for(DiffuserProductRequest diffuserProductRequest1 : )

        return diffuserProductRequest;
    }
}
