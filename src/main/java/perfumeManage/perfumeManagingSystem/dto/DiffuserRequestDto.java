package perfumeManage.perfumeManagingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import perfumeManage.perfumeManagingSystem.domain.Deadline;

import javax.persistence.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiffuserRequestDto {
    private String name;
    private String recipe;
    private int amount;
    private int year;
    private int month;
    private int date;
    private String image;
}
