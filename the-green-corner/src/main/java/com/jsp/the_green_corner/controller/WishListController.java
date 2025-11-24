//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.Cart;
//import com.jsp.the_green_corner.entity.WishList;
//import com.jsp.the_green_corner.service.CartService;
//import com.jsp.the_green_corner.service.WishListService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/wishList")
//public class WishListController {
//
//    private final WishListService wishListService;
//
//    @Autowired
//    public WishListController(WishListService wishListService){
//        this.wishListService=wishListService;
//    }
//
//    @PostMapping("/addToWishList/plant/{plantId}/user/{userId}")
//    public ResponseEntity<WishList> addPlantToWishList(@PathVariable long plantId, @PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(wishListService.addPlantToWishList(plantId,userId));
//    }
//
//    @PostMapping("/addToWishList/equipment/{equipId}/user/{userId}")
//    public ResponseEntity<WishList> addEquipmentToWishList(@PathVariable long equipId, @PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(wishListService.addEquipmentToWishList(equipId,userId));
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<WishList> fetchWishList(@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(wishListService.displayWishList(userId));
//    }
//
//    @DeleteMapping("/deleteFromWishList/plant/{plantId}/user/{userId}")
//    public ResponseEntity<WishList> deletePlantFromWishList(@PathVariable long plantId,@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(wishListService.deletePlantFromWishList(plantId,userId));
//    }
//
//    @DeleteMapping("/deleteFromWishList/equipment/{equipId}/user/{userId}")
//    public ResponseEntity<WishList> deleteEquipmentFromWishList(@PathVariable long equipId,@PathVariable long userId){
//        return ResponseEntity.status(HttpStatus.OK).body(wishListService.deleteEquipmentFromWishList(equipId,userId));
//    }
//}
