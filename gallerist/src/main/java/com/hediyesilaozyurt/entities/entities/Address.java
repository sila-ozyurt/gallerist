package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import com.hediyesilaozyurt.entities.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address",schema = "gallery_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @Column(name="country", length = 30, nullable = false)
    private String country;

    @Column(name="city", length = 50, nullable = false)
    private String city;

    @Column(name="district", length = 50, nullable = false)
    private String district;

    @Column(name="neighborhood", length = 100, nullable = false)
    private String neighborhood;

    @Column(name="street", length = 100, nullable = false)
    private String street;

    @Column(name = "postal_code", length = 10, nullable = false)
    private String postalCode;

    @Column(name = "building_no", length = 20, nullable = false)
    private String buildingNo;

    @Column(name = "apartment_no", length = 20, nullable = false)
    private String apartmentNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", length = 20, nullable = false)
    private AddressType addressType;

    @Column(name = "full_address", length = 500)
    private String fullAddress;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;


    @PrePersist
    @PreUpdate
    public void generateFullAddress(){
        StringBuilder sb=new StringBuilder();

        if (street != null) sb.append(street).append(" ");
        if (buildingNo != null) sb.append("No:").append(buildingNo).append(" ");
        if (apartmentNo != null) sb.append("Apartment:").append(apartmentNo).append(" ");
        if (neighborhood != null) sb.append(neighborhood).append(" ");
        if (district != null) sb.append(district).append(" ");
        if (city != null) sb.append(city).append(" ");
        if (postalCode != null) sb.append(postalCode).append(" ");
        if (country != null) sb.append(country);

        this.fullAddress=sb.toString().trim();
    }

}
