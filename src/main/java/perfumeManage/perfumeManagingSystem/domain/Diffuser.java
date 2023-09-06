package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Diffuser {
    @Id @GeneratedValue
    @Column(name = "diffuser_id")
    private Long id;

    private String name;

    private String recipe;

    private String image;
}
