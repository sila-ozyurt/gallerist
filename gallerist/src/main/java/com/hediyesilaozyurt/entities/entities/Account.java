package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import com.hediyesilaozyurt.entities.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="account",schema = "gallery_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "account_no",nullable = false,unique = true)
    private String accountNo;

    @Column(name="iban",nullable = false,unique = true)
    private String iban;

    @Column(name = "balance",nullable = false,precision = 19,scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name="currency_type",nullable = false)
    private CurrencyType currencyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;


}
