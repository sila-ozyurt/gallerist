package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gallerist",schema = "gallery_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gallerist extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "gallerist",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<GalleristCar> cars=new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "gallerist")
    private List<SaledCar> sales=new ArrayList<>();


}
