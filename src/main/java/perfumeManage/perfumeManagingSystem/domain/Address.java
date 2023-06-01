package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String country;
    private String city;
    private String StreetAddress;
    private String detailedAddress;
    private String zipcode;

    protected Address() {

    }

    public Address(String country, String city, String StreetAddress, String detailedAddress, String zipcode) {
        this.country = country;
        this.city = city;
        this.StreetAddress = StreetAddress;
        this.detailedAddress = detailedAddress;
        this.zipcode = zipcode;
    }


}
