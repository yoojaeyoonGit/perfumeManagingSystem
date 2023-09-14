package perfumeManage.perfumeManagingSystem.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
public class Product {
    @GeneratedValue
    @Id @Column(name = "product_id")
    private Long id;

    private String name;

    private String recipe;

    private String image;
}
