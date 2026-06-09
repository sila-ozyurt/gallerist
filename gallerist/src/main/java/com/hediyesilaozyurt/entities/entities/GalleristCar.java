package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="gallerist_car",schema = "gallery_management",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"gallerist_id","car_id"},
                    name="unique_gallerist_car"
            )
        }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCar extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gallerist_id",nullable = false)
    private Gallerist gallerist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id",nullable = false)
    private Car car;

    @Column(name = "price",nullable = false,precision = 19,scale = 2)
    private BigDecimal price;
}
