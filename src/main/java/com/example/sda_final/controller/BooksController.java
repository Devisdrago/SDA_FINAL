package com.example.sda_final.controller;


import com.example.sda_final.entity.AuthorEntity;
import com.example.sda_final.entity.BookEntity;
import com.example.sda_final.entity.PublishingHouseEntity;
import com.example.sda_final.repository.BookRepository;
import com.example.sda_final.serivce.AuthorService;
import com.example.sda_final.serivce.BookService;
import com.example.sda_final.serivce.PublishingHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@Controller
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;

    private final BookRepository bookRepository;


    public BooksController(BookService bookService, AuthorService authorService, PublishingHouseService publishingHouseService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publishingHouseService = publishingHouseService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books/list")
    public String getBooksPage(Model model){
        List<BookEntity> bookEntityList = bookService.getAllBooks();
        model.addAttribute("bookRequest" , bookEntityList);
        return "bookListPage";}

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute BookEntity bookEntity){
        System.out.println("book request : " + bookEntity);
        BookEntity addBook = bookService.addBook(bookEntity.getName(),bookEntity.getGenre(), bookEntity.getAuthor(),bookEntity.getHouse());
        return addBook != null ? "addBookPage" : "redirect:error_page";
    }

    @GetMapping("/books/addBook")
    public String addBookAndHousePage(Model model){
        List<PublishingHouseEntity> publishingHouseEntityList = publishingHouseService.getAllPublishedHouses();
        List<AuthorEntity> authorEntityList = authorService.getAllAuthors();
        model.addAttribute("bookRequest" , new BookEntity());
        model.addAttribute("authorBookRequest" ,authorEntityList);
        model.addAttribute("houseBookRequest", publishingHouseEntityList);
        return "addBookPage";

    }

    @GetMapping("/books/list/delete/{id}")
    public String deleteBook(@PathVariable Long id , Model model){
        bookRepository.deleteById(id);
        return getBooksPage(model);
    }

    @PostMapping("/books/list/update/{id}")
    public String updateBook(@PathVariable Long id ,BookEntity bookEntity  , Model model){
        List<PublishingHouseEntity> publishingHouseEntityList = publishingHouseService.getAllPublishedHouses();
        List<AuthorEntity> authorEntityList = authorService.getAllAuthors();
        model.addAttribute("updateBookRequest" , new Book());
        model.addAttribute("updateBookAuthorsRequest" , authorEntityList );
        model.addAttribute("updateBookHousesRequest" , publishingHouseEntityList);
        bookService.updateBook(id,bookEntity.getName(),bookEntity.getGenre(),bookEntity.getAuthor(),bookEntity.getHouse());
        return getBooksPage(model);
    }

}
