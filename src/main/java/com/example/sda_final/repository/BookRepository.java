package com.example.sda_final.repository;

import com.example.sda_final.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    Optional<BookEntity> findByName(String name);

    Optional<BookEntity> deleteBookEntityByName(String name);

}
