package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import com.hediyesilaozyurt.entities.enums.CarStatusType;
import com.hediyesilaozyurt.entities.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="car",schema = "gallery_management")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity {

    @Column(name = "plate")
    private String plate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "damage_price")
    private BigDecimal damagePrice;

    @Column(name = "car_status_type")
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;

    @ToString.Exclude
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GalleristCar> gallerists = new ArrayList<>();

}
