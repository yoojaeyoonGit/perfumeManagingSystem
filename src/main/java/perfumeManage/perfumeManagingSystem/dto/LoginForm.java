package perfumeManage.perfumeManagingSystem.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    @NotNull
    private Long id;

    @NotEmpty
    private String password;
}
