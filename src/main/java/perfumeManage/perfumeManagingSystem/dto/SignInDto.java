package perfumeManage.perfumeManagingSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import perfumeManage.perfumeManagingSystem.domain.Address;
import perfumeManage.perfumeManagingSystem.domain.Auth;
import perfumeManage.perfumeManagingSystem.domain.Gender;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    private String name;
    private int password;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private String country;
    private String city;
    private String streetAddress;
    private String zipcode;
    private String detailedAddress;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Auth auth;
}
