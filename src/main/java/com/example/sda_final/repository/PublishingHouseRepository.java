package com.example.sda_final.repository;

import com.example.sda_final.entity.PublishingHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouseEntity,Long> {

    Optional<PublishingHouseEntity> findByName(String name);



}
