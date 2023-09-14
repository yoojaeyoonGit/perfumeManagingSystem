package perfumeManage.perfumeManagingSystem.domain.product;

import lombok.Getter;
import lombok.Setter;
import perfumeManage.perfumeManagingSystem.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("perf")
@Setter
public class Perfume extends Product {
}
