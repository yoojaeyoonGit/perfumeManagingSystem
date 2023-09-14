package perfumeManage.perfumeManagingSystem.domain.product;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("diff")
@Setter
public class Diffuser extends Product {
}
