package com.example.sda_final.serivce;

import com.example.sda_final.entity.AuthorEntity;
import com.example.sda_final.repository.AuthorsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AuthorService {

    private final AuthorsRepository authorsRepository;

    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public AuthorEntity addAuthor(String name, int age, String nationality) {

        AuthorEntity author = new AuthorEntity();
        author.setName(name);
        author.setAge(age);
        author.setNationality(nationality);
        return authorsRepository.save(author);

    }

    public List<AuthorEntity> getAllAuthors(){

        return new ArrayList<>(authorsRepository.findAll());
    }

    public void updateAuthor(Long id , String name , int age , String nationality){
        if(authorsRepository.findById(id).isPresent()){
            authorsRepository.findById(id).get().setName(name);
            authorsRepository.findById(id).get().setAge(age);
            authorsRepository.findById(id).get().setNationality(nationality);
        }
    }


}

