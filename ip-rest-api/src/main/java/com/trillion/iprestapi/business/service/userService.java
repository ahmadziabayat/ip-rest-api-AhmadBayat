package com.trillion.iprestapi.business.service;

import com.trillion.iprestapi.data.entity.User;
import com.trillion.iprestapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class userService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String firstName, String lastName, String emailAddress){
        List<User> existenceUser = new ArrayList<>();
        this.userRepository.findAll().forEach(user ->{
            if (user.getEmail() == emailAddress){
                existenceUser.add(user);
            }
        });
        User user = new User();
        if(existenceUser.size() > 0){
            System.out.println("User already Exit");
        }else {

            user.setEmail(emailAddress);
            user.setLastName(lastName);
            user.setFirstName(firstName);
            userRepository.save(user);

        }
        return user;
    }
}
