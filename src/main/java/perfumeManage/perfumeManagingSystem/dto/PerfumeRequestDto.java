package perfumeManage.perfumeManagingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeRequestDto {
    private String name;
    private String recipe;
    private int amount;
    private int year;
    private int month;
    private int date;
    private String image;
}
