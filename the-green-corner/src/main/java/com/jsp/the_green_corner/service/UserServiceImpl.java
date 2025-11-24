package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.*;
import com.jsp.the_green_corner.exception.*;
import com.jsp.the_green_corner.repository.DeliveryRepository;
import com.jsp.the_green_corner.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final DeliveryRepository deliveryRepo;

    private final JavaMailSender javaMailSender;

    private ResourceLoader resourceLoader;

    @Value("${app.admin.mail}")
    private String adminMail;

    @Value("${app.email.SignUpWelcomeImg.url}")
    private String emailWelcomeImgUrl;

    @Autowired
    public UserServiceImpl(UserRepository userRepo,JavaMailSender javaMailSender,
                           ResourceLoader resourceLoader,DeliveryRepository deliveryRepo) {
        this.userRepo = userRepo;
        this.javaMailSender=javaMailSender;
        this.resourceLoader=resourceLoader;
        this.deliveryRepo=deliveryRepo;

    }


    @Transactional
    public User signupUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent() ) {

            throw new DuplicateUser("User with emailId " + user.getEmail() +
                    " already exists in database!Email must be unique!");
//            return null;
        }
        //all usernames are unique so that in plant entity we can uniquely identify user who adds
        // review bcz username in plant for review is
        if(userRepo.findByName(user.getName()).isPresent()){
            throw new DuplicateUser("User with name " + user.getName() + " already exists in database username " +
                    "must be unique!");
//            return null;
        }
        Cart cart = new Cart();
        user.setCart(cart);
        WishList wishList = new WishList();
        user.setWishList(wishList);
        User savedUser = userRepo.save(user);

        sendWelcomeEmail(savedUser);
        return savedUser;

    }

    private void sendWelcomeEmail(User user) {
        try {
            //used MimeMessage so that i could send image in email as email services cannot access static project image files so hosted the image on cloudinary
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(adminMail);
            helper.setTo(user.getEmail());
            helper.setSubject("Welcome to Green Corner!");

            String htmlContent = loadTemplate(user.getName());

            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new EmailException("Failed to send welcome email");
        }
    }

    private String loadTemplate(String username) {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/welcomeEmail.html");

            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            return content
                    .replace("{{username}}", username)
                    .replace("{{welcomeImgUrl}}", emailWelcomeImgUrl);

        } catch (IOException e) {
            throw new EmailException("Unable to load email template");
        }
    }


    public User signInUser(String email, String password) {

            User fetchedUser = findUserByEmailAndPassword(email, password);
            if (fetchedUser!=null) {
                if (email.equals(fetchedUser.getEmail()) && password.equals(fetchedUser.getPassword()) ) {
                    return fetchedUser;
                }
            }

        throw new UserNotFoundException("Invalid email/password");
    }

    @Transactional
    public Delivery placeDelivery(Long userId, Delivery delivery) {

        //here i am fetching setting totalAmount in delivery by calculating price of all the products(i.e plants and
        // equipments) present in cart of the user so that user won't be able to change the amount on delivery page even if he/she provides manipulated amt in delivery
        // json obj (rightnow from postman to test)
        // the backend will still save the valid and real amount by calculating the total now we only need to pass this total amount in payment process functionality
        // in upi so as to ensure that valid amt will be paid and not the one provided by user in json obj


        User user = findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User does not exist");
        }

        Cart cart = user.getCart();
        if (cart == null) {
            throw new CartException("Cart is empty so no orders to deliver.");
        }

        double total = 0;

        for (Plant p : cart.getPlants()) {
            total += p.getPrice();
        }

        for (Equipments e : cart.getEquipment()) {
            total += e.getPrice();
        }

        delivery.setTotalAmount(total);

        delivery.setUser(user);
        Delivery savedDelivery = deliveryRepo.save(delivery);
        user.getDeliveries().add(savedDelivery);

        return savedDelivery;
    }

//                      READING BUSINESS LOGIC IMPL
    public User findUserById(Long id) {

        try {
            Optional<User> optionalUser = userRepo.findById(id);
            if (optionalUser.isPresent()) {
                User fetchedUser = optionalUser.get();
                return fetchedUser;
            }
        }
        catch (DataAccessException ex) {
            throw new DatabaseException("Database error occurred while fetching user with " +
                    "provided id: " + id);
        }
        throw new UserNotFoundException("No user exists with provided id:" + id);
    }
    public User findUserByName(String name) {
        if (validateUserNameFormat(name)) {
            String normalizedName = name.trim().replaceAll("\\s+", " ");
            try {
                Optional<User> optionalfetchedUser = userRepo.findByName(normalizedName);

                if (optionalfetchedUser.isPresent()) {
                    User fetchedUser = optionalfetchedUser.get();
                    return fetchedUser;
                }
            }catch (DataAccessException ex) {
                throw new DatabaseException("Database error occurred while fetching user with " +
                        "provided name: " + name);
            }
        }
        throw new UserNotFoundException("No user exist with provided name: " + name);
    }

    public User findUserByEmail(String email) {
        if (validateUserEmailFormat(email)) {
            try {
                Optional<User> optionalUser = userRepo.findByEmail(email);
//            User user = userRepo.findByEmail(email);
                if (optionalUser.isPresent()) {
//            if (user != null) {
                    User fetchedUser = optionalUser.get();
                    return fetchedUser;

//                return user;
                }
            }catch (DataAccessException ex) {
                throw new DatabaseException("Database error occurred while fetching user with provided" +
                        " email: " + email);
            }
        }
        throw new UserNotFoundException("No user exist with provided email: " + email);
    }
    public User findUserByContact(Long contact) {
        if (validateUserContactFormat(contact)) {
            try {
                Optional<User> optionalfetchedUser = userRepo.findByContact(contact);

                if (optionalfetchedUser.isPresent()) {
                    User fetchedUser = optionalfetchedUser.get();
                    return fetchedUser;
                }
            }catch (DataAccessException ex) {
                throw new DatabaseException("Database error occurred while fetching user with " +
                        "provided contact: " + contact);
            }
        }
        throw new UserNotFoundException("No user exist with provided contact: " + contact);
    }
    public List<User> findAllUsers() {
        try {
            List<User> usersList = userRepo.findAll();
            if (!usersList.isEmpty()) {
                return usersList;
            }
        }catch (Exception ex) {
            throw new DatabaseException("Database error occurred while fetching users ");
        }
        throw new UserNotFoundException("No users exist in database");
    }
    public List<User> findAllUsersByName(String name) {
        try {
            List<User> usersList = userRepo.findAllByName(name);
            if (!usersList.isEmpty()) {
                return usersList;
            }
        }catch (DataAccessException ex) {
            throw new DatabaseException("Database error occurred while fetching users with " +
                    "provided name: " + name);
        }
        throw new UserNotFoundException("No users exist with provided name: " + name);
    }


//                      UPDATE BUSINESS LOGIC IMPL
    @Transactional
    public User updateUserById(Long id,User user) {
        User existingUser = findUserById(id);
        if (existingUser != null) {

            return userRepo.save(updateUserUtil(user,existingUser));
        }
        return null;
    }

    @Transactional
    public User updateUserByName(String name,User user) {
        User existingUser = findUserByName(name);
        if (existingUser != null) {

            return userRepo.save(updateUserUtil(user,existingUser));
        }
        return null;
    }

    @Transactional
    public User updateUserByEmail(String email,User user) {
        User existingUser = findUserByEmail(email);
        if (existingUser != null) {

            return userRepo.save(updateUserUtil(user,existingUser));
        }
        return null;
    }

    @Transactional
    public User updateUserByContact(Long contact,User user) {
        User existingUser = findUserByContact(contact);
        if (existingUser != null) {

            return userRepo.save(updateUserUtil(user,existingUser));
        }
        return null;
    }

    @Transactional
    public String deleteUserById(Long id){
        User existingUser = findUserById(id);
        if(existingUser !=null){
            int rowsDeleted = userRepo.deleteUserById(id);
            if (rowsDeleted > 0) {
                return "User with id " + id + " with name " + existingUser.getName() + " deleted " +
                        "successfully!";
            }
        }
        return "User with provided id " + id + " does not exist!";
    }
    @Transactional
    public String deleteUserByName(String name){

            User existingUser = findUserByName(name);
            if(existingUser !=null){
                int rowsDeleted = userRepo.deleteByName(name);
                if (rowsDeleted > 0) {
                    return "User with name " + name + " deleted successfully!";
                }
            }
        return "User with name " + name + " does not exist!";
    }

    @Transactional
    public String deleteUserByEmail(String email){

//        public User deleteUserByEmail(String email){
            User existingUser = findUserByEmail(email);
            if(existingUser !=null){
                int rowsDeleted = userRepo.deleteByEmail(email);
                if (rowsDeleted > 0) {
                    return "User with emailId " + email + " with name " + existingUser.getName() +
                            " deleted successfully!";
//                return existingUser;
                }
            }
//        return null;
        return "User with emailId " + email + " does not exist so noway of deleting!";
    }

    @Transactional
    public String deleteUserByContact(Long contact){

            User existingUser = findUserByContact(contact);
            if(existingUser !=null){
                int rowsDeleted = userRepo.deleteByContact(contact);
                if (rowsDeleted > 0) {
                    return "User with contact " + contact + " with name " + existingUser.getName() +
                            " deleted successfully!";
                }
            }
        return "User with contact " + contact + " is not deleted!";
    }



    //                UTILITY FUNCTIONS OF BUSINESS LOGIC
    private User findUserByEmailAndPassword(String email, String password) {

        if (validateUserCredentialsFormat(email, password)) {
            Optional<User> optional = userRepo.findByEmailAndPassword(email,password);
//            User user = userRepo.findByEmailAndPassword(email, password);
            if(optional.isPresent()){
//            if (user != null) {

                User fetchedUser = optional.get();
                return fetchedUser;
//                return user;
            }
        }
        throw new UserNotFoundException("User with provided email and password does not exist");
    }

    private User updateUserUtil(User user,User existingUser){
        if (user.getName() != null && validateUserNameFormat(user.getName())) {
            if (userRepo.findByName(user.getName()).isPresent() &&
                    !existingUser.getName().equals(user.getName())) {

                throw new DuplicateUser("Username already exists!");
            }
            existingUser.setName(user.getName());
        }

        if (user.getEmail()!=null && validateUserEmailFormat(user.getEmail())) {
            if (userRepo.findByEmail(user.getEmail()).isPresent() &&
                    !existingUser.getEmail().equals(user.getEmail())) {

                throw new DuplicateUser("Email already exists!");
            }
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword()!=null && validateUserPasswordFormat(user.getPassword())) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getContact() != null && validateUserContactFormat(user.getContact()) ){
            if (userRepo.findByContact(user.getContact()).isPresent() &&
                    !existingUser.getContact().equals(user.getContact())) {

                throw new DuplicateUser("Contact already exists!");
            }
            existingUser.setContact(user.getContact());

        }
        return existingUser;

    }

    private boolean validateUserCredentialsFormat(String email, String password) {

        return validateUserEmailFormat(email) && validateUserPasswordFormat(password);
    }

    private boolean validateUserEmailFormat(String email) {
        if ( email != null && !email.isEmpty() && email.matches("^[a-z0-9]+(?:[._-][a-z0-9]+)?@[a-z]+(?:\\.[a-z]{2,})+$")) {
            return true;
        }

        throw new InvalidDataFormatException("Invalid email format");
    }

    private boolean validateUserPasswordFormat(String password) {
        if ( password != null  && !password.isEmpty() && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*!]).{7,20}$") ) {
            return true;
        }
        throw new InvalidDataFormatException("Invalid password format");
    }

    private boolean validateUserNameFormat(String name) {

        if(  name!=null && !name.isEmpty() && name.trim().matches("^[A-Za-z0-9 ]+$")) {
            return  true;
        }
        throw new InvalidDataFormatException("Invalid name format");
    }

    private boolean validateUserContactFormat(Long contact) {
        if (contact == null) return false;
        if(String.valueOf(contact).matches("^[6-9]\\d{9}$")){
            return  true;
        }
        throw new InvalidDataFormatException("Invalid contact format");
    }
}