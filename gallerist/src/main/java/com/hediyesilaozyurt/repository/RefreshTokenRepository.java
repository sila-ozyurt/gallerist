package com.hediyesilaozyurt.repository;

import com.hediyesilaozyurt.entities.authEntities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    @Query(value = "select * from gallery_management.refresh_token where refresh_token= :token",nativeQuery = true)
    Optional<RefreshToken> findByRefreshToken(@Param(value = "token") String token);

    @Query(value = "select * from refresh_token where user_id= :userId and is_revoked=false ",nativeQuery = true)
    List<RefreshToken> findAllByUserIdAndRevokedFalse(@Param(value = "userId") Long userId);

}
