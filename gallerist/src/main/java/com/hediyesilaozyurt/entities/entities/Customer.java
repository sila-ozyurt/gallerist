package com.hediyesilaozyurt.entities.entities;

import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer",schema = "gallery_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "tckn")
    private String tckn;

    @Column(name = "birth_of_date")
    private LocalDate birthOfDate;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<Address> addresses=new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<Account> accounts=new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<SaledCar> sales = new ArrayList<>();

    //helper methods
    public void addAddress(Address address){
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setCustomer(null);
    }

    public void addAccount(Account account){
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account){
        accounts.remove(account);
        account.setCustomer(null);
    }
}
