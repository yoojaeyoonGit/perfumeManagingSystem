package perfumeManage.perfumeManagingSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import perfumeManage.perfumeManagingSystem.domain.Auth;
import perfumeManage.perfumeManagingSystem.domain.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String name;
    private String password;

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
