package com.hediyesilaozyurt.entities.authEntities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="refresh_token",schema = "gallery_management")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RefreshToken extends BaseEntity {

    @Column(name="refresh_token",unique = true,nullable = false,length=500)
    private String refreshToken;

    @Column(name="expiry_date",nullable = false)
    private LocalDateTime expiryDate;

    @Builder.Default
    @Column(name="is_revoked",nullable=false)
    private boolean revoked=false;

    @ManyToOne
    @JoinColumn(name = "user_id",unique = false, nullable = false)
    private User user;

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public boolean isValid() {
        return !isExpired()&&!revoked;
    }
}
