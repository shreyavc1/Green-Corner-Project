//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.Cart;
//import com.jsp.the_green_corner.entity.Plant;
//import com.jsp.the_green_corner.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//
//    private final CartService cartService;
//
//    @Autowired
//    public CartController(CartService cartService){
//        this.cartService=cartService;
//    }
//
//    @PostMapping("/addToCart/plant/{plantId}/user/{userId}")
//    public ResponseEntity<Cart> addPlantToCart(@PathVariable long plantId,@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(cartService.addPlantToCart(plantId,userId));
//    }
//
//    @PostMapping("/addToCart/equipment/{equipId}/user/{userId}")
//    public ResponseEntity<Cart> addEquipmentToCart(@PathVariable long equipId, @PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(cartService.addEquipmentToCart(equipId,userId));
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Cart> fetchCart(@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(cartService.displayCart(userId));
//    }
//
//    @DeleteMapping("/deleteFromCart/plant/{plantId}/user/{userId}")
//    public ResponseEntity<Cart> deletePlantFromCart(@PathVariable long plantId,@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(cartService.deletePlantFromCart(plantId,userId));
//    }
//
//    @DeleteMapping("/deleteFromCart/equipment/{equipId}/user/{userId}")
//    public ResponseEntity<Cart> deleteEquipmentFromCart(@PathVariable long equipId,@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteEquipmentFromCart(equipId,userId));
//    }
//
//}
