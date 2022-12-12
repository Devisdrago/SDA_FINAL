package com.example.sda_final.serivce;

import com.example.sda_final.entity.BookEntity;
import com.example.sda_final.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity addBook(String name , String genre , String authorEntityList , String publishedHouseEntityList){

        if(name == null){
            return null;
        } else if (bookRepository.findByName(name).isPresent()) {
            System.out.println("The book already exist");
            return null ;
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(name);
        bookEntity.setGenre(genre);
        bookEntity.setAuthor(authorEntityList);
        bookEntity.setHouse(publishedHouseEntityList);
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> getAllBooks(){
        return new ArrayList<>(bookRepository.findAll());
    }

    public void updateBook(Long id , String name , String genre , String authors , String houses){

        if(bookRepository.findById(id).isPresent()){
            bookRepository.findById(id).get().setName(name);
            bookRepository.findById(id).get().setGenre(genre);
            bookRepository.findById(id).get().setAuthor(authors);
            bookRepository.findById(id).get().setHouse(houses);
        }

    }


}
