package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "saled_car",schema = "gallery_management",
uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"gallerist_id","car_id","customer_id"},
                name = "unique_gallerist_car_customer_for_saled_cars"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Transaction entity
public class SaledCar extends BaseEntity {

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="gallerist_id",nullable = false)
    private Gallerist gallerist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id",nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private BigDecimal price;
}
