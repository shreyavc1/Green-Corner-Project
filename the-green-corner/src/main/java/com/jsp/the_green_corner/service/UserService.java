package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Delivery;
import com.jsp.the_green_corner.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //saving i.e creating new user business logic
    User signupUser(User user);

    User signInUser(String email, String password);

    Delivery placeDelivery(Long userId, Delivery delivery);

    //READING BL
    User findUserById(Long id);
    User findUserByName( String name);
    User findUserByEmail(String email);
    User findUserByContact(Long contact);

    List<User> findAllUsers();
    List<User> findAllUsersByName(String name);

    //UPDATE BL
    User updateUserById(Long id,User user);
    User updateUserByName(String name,User user);
    User updateUserByEmail(String email,User user);
    User updateUserByContact(Long contact,User user);


//    DELETE BL
    String deleteUserById(Long id);
    String deleteUserByName(String name);
    String deleteUserByEmail(String email);
    String deleteUserByContact(Long contact);


}
