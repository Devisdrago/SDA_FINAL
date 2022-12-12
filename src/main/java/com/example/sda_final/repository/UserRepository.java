package com.example.sda_final.repository;

import com.example.sda_final.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsernameAndPassword(String username , String password);

    Optional<UserEntity> findFirstByUsername (String username);

    Optional<UserEntity> deleteUserEntityByUsername ( String username );


}