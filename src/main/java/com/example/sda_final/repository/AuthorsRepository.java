package com.example.sda_final.repository;

import com.example.sda_final.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface AuthorsRepository extends JpaRepository<AuthorEntity,Long> {

    Optional<AuthorEntity> deleteAuthorEntityByName(String name);

}
