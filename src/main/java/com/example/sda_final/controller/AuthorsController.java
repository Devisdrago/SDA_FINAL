package com.example.sda_final.controller;

import com.example.sda_final.entity.AuthorEntity;
import com.example.sda_final.repository.AuthorsRepository;
import com.example.sda_final.serivce.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorsController {

    private final AuthorService authorService;
    private final AuthorsRepository authorsRepository;

    public AuthorsController(AuthorService authorService, AuthorsRepository authorsRepository) {
        this.authorService = authorService;
        this.authorsRepository = authorsRepository;
    }


    // for adding feature in add author page
    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute AuthorEntity authorEntity){
        System.out.println(" author request : " + authorEntity);
        AuthorEntity addAuthor = authorService.addAuthor(authorEntity.getName() ,authorEntity.getAge(),authorEntity.getNationality());
        return addAuthor != null ? "addAuthorPage" : "redirect:error_page";
    }

    // view table in authors list page
    @RequestMapping("/authors/list")
    public String getAllAuthors(Model model){
        List<AuthorEntity> authorEntityList = authorService.getAllAuthors();
        model.addAttribute("authorRequest" , authorEntityList);
        return authorEntityList !=null ? "authorsListPage" : "redirect:error_page";
    }

    // view add author page
    @GetMapping("/authors/addAuthor")
    public String addAuthorPage(Model model){
        model.addAttribute("authorRequest" ,new AuthorEntity());
        return "addAuthorPage";
    }

    @GetMapping("/authors/list/delete/{id}")
    public String deleteAuthor(@PathVariable Long id , Model model){
        authorsRepository.deleteById(id);
        return getAllAuthors(model);
    }

    @PostMapping("/authors/list/update/{id}")
    public String updateAuthor(@PathVariable Long id, AuthorEntity authorEntity  , Model model){
        model.addAttribute("updateAuthorRequest" , new AuthorEntity());
        authorService.updateAuthor(id,authorEntity.getName(),authorEntity.getAge(),authorEntity.getNationality());
        return getAllAuthors(model);
    }
}
