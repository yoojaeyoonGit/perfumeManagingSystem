package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String country;
    private String city;
    private String StreetAddress;
    private String detailed_address;
    private String zipcode;

    protected Address() {

    }

    public Address(String country, String city, String StreetAddress, String detailed_address, String zipcode) {
        this.country = country;
        this.city = city;
        this.StreetAddress = StreetAddress;
        this.detailed_address = detailed_address;
        this.zipcode = zipcode;
    }


}
