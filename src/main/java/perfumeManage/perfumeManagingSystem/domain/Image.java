package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Image {
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diffuserProductRequest_id")
    private DiffuserProductRequest diffuserProductRequest;

    @ManyToOne
    @JoinColumn(name = "perfumeProductRequest_id")
    private PerfumeProductRequest perfumeProductRequest;
}
