package com.example.sda_final.serivce;
import com.example.sda_final.entity.UserEntity;
import com.example.sda_final.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(String username, String password, String email, String firstName, String lastName, String dateOfBirth) {


        if (username == null || password == null) {
            return null;
        } else if (userRepository.findFirstByUsername(username).isPresent()) {
            System.out.print("Duplicate username");
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setDateOfBirth(dateOfBirth);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        return userRepository.save(userEntity);
    }
    public UserEntity authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }


}
