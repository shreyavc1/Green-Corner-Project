//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.User;
//import com.jsp.the_green_corner.service.UserService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    private UserService userService;
//
//    @Autowired
//    public UserController(UserService userService){
//        this.userService=userService;
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<User> userSignUp(@Valid @RequestBody User user){
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signupUser(user));
//    }
//
//  @PostMapping("/signIn/{email}/{password}")
//  public ResponseEntity<User> userSignIn( @PathVariable String email, String password){
//    @PostMapping("/signIn")
//    public ResponseEntity<User> userSignIn( @RequestBody User user){
//
//        return ResponseEntity.status(HttpStatus.OK).body(userService.signInUser(user.getEmail(),user.getPassword()));
//     }
//
//
//
//
////                               READING MAPPINGS
//    @GetMapping("/fetchAll")
//    public ResponseEntity<List<User>> fetchAllUsers(){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
//    }
//
//    @GetMapping("/fetchAll/{name}")
//    public ResponseEntity<List<User>> fetchAllUsersByName(@PathVariable String name){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsersByName(name));
//    }
//
//    @GetMapping("/id/{id}")
//    public ResponseEntity<User> fetchUserById(@PathVariable Long id){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
//
//    }
//    @GetMapping("/fetchByName/{name}")
//    public ResponseEntity<User> fetchUserByName(@PathVariable String name){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByName(name));
//    }
//
//    @GetMapping("/email/{email}")
//    public ResponseEntity<User> fetchUserByEmail(@PathVariable String email){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
//
//    }
//    @GetMapping("/contact/{contact}")
//    public ResponseEntity<User> fetchUserByContact(@PathVariable Long contact){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByContact(contact));
//
//    }
//
////                                UPDATE MAPPINGS
//    @PatchMapping("/id/{id}")
//    public ResponseEntity<User> updateUserById(@PathVariable Long id,@RequestBody User user){
//
//       return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(id,user));
//
//    }
//    @PatchMapping("/name/{name}")
//    public ResponseEntity<User> updateUserByName(@PathVariable String name,@RequestBody User user){
//
//        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserByName(name,user));
//
//    }
//    @PatchMapping("/email/{email}")
//    public ResponseEntity<User> updateUserByEmail(@PathVariable String email,@RequestBody User user){
//
//        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserByEmail(email,user));
//    }
//    @PatchMapping("/contact/{contact}")
//    public ResponseEntity<User> updateUserByContact(@PathVariable Long contact,@RequestBody User user){
//
//        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserByContact(contact,user));
//    }
//
//
////                              DELETE MAPPINGS
//    @DeleteMapping("/id/{id}")
//    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(id));
//    }
//    @DeleteMapping("/name/{name}")
//    public ResponseEntity<String> deleteUserByName(@PathVariable String name) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserByName(name));
//    }
//
//    @DeleteMapping("/email/{email}")
//    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserByEmail(email));
//    }
//    @DeleteMapping("/contact/{contact}")
//    public ResponseEntity<String> deleteUserByContact(@PathVariable Long contact) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserByContact(contact));
//    }
//}
