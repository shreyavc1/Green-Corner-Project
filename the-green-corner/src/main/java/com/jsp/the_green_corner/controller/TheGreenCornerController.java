package com.jsp.the_green_corner.controller;

import com.jsp.the_green_corner.entity.*;
import com.jsp.the_green_corner.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class TheGreenCornerController {

    private final UserService userService;
    private final PlantService plantService;
    private final ReviewService reviewService;
    private final EquipmentsService equipmentsService;
    private final CartService cartService;
    private final WishListService wishListService;
    private final ContactUsService contactUsService;
    private String basePaymentUrl = "upi://pay?";



    @Autowired
    public TheGreenCornerController(UserService userService, PlantService plantService, ReviewService reviewService,
                                    EquipmentsService equipmentsService, CartService cartService, WishListService wishListService,
                                    ContactUsService contactUsService){

        this.userService=userService;
        this.plantService=plantService;
        this.reviewService=reviewService;
        this.equipmentsService=equipmentsService;
        this.cartService=cartService;
        this.wishListService=wishListService;
        this.contactUsService=contactUsService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> userSignUp(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signupUser(user));
    }

    @PostMapping("/user/signIn/{email}/{password}")
    public ResponseEntity<User> userSignIn( @PathVariable String email,@PathVariable String password){
        return ResponseEntity.status(HttpStatus.OK).body(userService.signInUser(email,password));
    }

    @PostMapping("/user/{userId}/deliveries")
    public ResponseEntity<Delivery> placeDelivery(@PathVariable Long userId, @Valid @RequestBody Delivery delivery){
        return ResponseEntity.status(HttpStatus.OK).body(userService.placeDelivery(userId,delivery));
    }

    @GetMapping("/user/fetchAll")
    public ResponseEntity<List<User>> fetchAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }


    @GetMapping("/user/id/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @GetMapping("/user/fetchByName/{name}")
    public ResponseEntity<User> fetchUserByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByName(name));
    }

    @GetMapping("/fetchAllByName/{name}")
    public ResponseEntity<List<User>> fetchAllUsersByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsersByName(name));
    }

    @PatchMapping("/user/id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user){

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(id,user));

    }

//                            PLANTS CONTROLLERS
    @PostMapping("/plant/save")
    public ResponseEntity<Plant> savePlant(@Valid @RequestBody Plant plant){
        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.savePlant(plant));
    }

    @GetMapping("/plant/findAll")
    public ResponseEntity<List<Plant>> fetchAllPlants(){
        return ResponseEntity.status(HttpStatus.OK).body(plantService.findAllPlants());
    }

    @GetMapping("/plant/findAllByName/{name}")
    public ResponseEntity<List<Plant>> fetchPlantsByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByName(name));
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<Plant> fetchPlantById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantById(id));
    }

    @PutMapping("/plant/{plantId}/review")
    public ResponseEntity<Plant> assignReviewToPlant( @PathVariable long plantId,@Valid @RequestBody Review review){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.addReviewToPlant(plantId,review));
    }

    @GetMapping("/plant/rating")
    public ResponseEntity<List<Plant>> fetchPlantsByRating(@RequestParam Double rating){

        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByRating(rating));
    }

    @GetMapping("/plant/fetchByPriceLowToHigh")
    public ResponseEntity<List<Plant>> fetchPlantsByLowToHighPrice(){

        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsFromLowToHighPrice());
    }

    @GetMapping("/plant/fetchByPriceHighToLow")
    public ResponseEntity<List<Plant>> fetchPlantsByHighToLowPrice(){

        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsFromHighToLowPrice());
    }

    @GetMapping("/plant/categories")
    public ResponseEntity<List<Plant>> fetchPlantsByCategory(@RequestParam String category){
        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByCategory(category));
    }

    @GetMapping("/plant/page")
    public ResponseEntity<Page<Plant>> fetchPlantsByPage(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "2") int size){
        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByPage(page,size));
    }

//                             EQUIPMENTS CONTROLLER
@PostMapping("/equipments/save")
public ResponseEntity<Equipments> saveEquipment(@Valid @RequestBody Equipments equipment){
    return ResponseEntity.status(HttpStatus.CREATED).body(equipmentsService.saveEquipment(equipment));
}

    @GetMapping("/equipments/{id}")
    public ResponseEntity<Equipments> fetchEquipmentById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentById(id));
    }

    @GetMapping("/equipments/fetchAllByName/{name}")
    public ResponseEntity<List<Equipments>> fetchAllEquipmentsByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByName(name));
    }

    @GetMapping("/equipments/fetchAll")
    public ResponseEntity<List<Equipments>> fetchAllEquipment(){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findAllEquipments());
    }

    @PutMapping("/equipments/{equipId}/review")
    public ResponseEntity<Equipments> assignReviewToEquipment( @PathVariable long equipId,@Valid @RequestBody Review review){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.addReviewToEquipment(equipId,review));
    }

    @GetMapping("/equipments/rating")
    public ResponseEntity<List<Equipments>> fetchEquipmentsByRating(@RequestParam Double rating){

        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByRating(rating));
    }

    @GetMapping("/equipments/fetchByPriceLowToHigh")
    public ResponseEntity<List<Equipments>> fetchEquipmentsByLowToHighPrice(){

        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsFromLowToHighPrice());
    }

    @GetMapping("/equipments/fetchByPriceHighToLow")
    public ResponseEntity<List<Equipments>> fetchEquipmentsByPriceHighToLowPrice(){

        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsFromHighToLowPrice());
    }

    @GetMapping("/equipments/categories")
    public ResponseEntity<List<Equipments>> fetchEquipmentsByCategory(@RequestParam String category){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByCategory(category));
    }

    @GetMapping("/equipments/page")
    public ResponseEntity<Page<Equipments>> fetchEquipmentsByPage(@RequestParam(defaultValue = "0")
                                            int page, @RequestParam(defaultValue = "2") int size){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByPage(page,size));
    }

//                              CART CONTROLLER
    @PostMapping("/cart/addToCart/plant/{plantId}/user/{userId}")
    public ResponseEntity<Cart> addPlantToCart(@PathVariable long plantId,@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addPlantToCart(plantId,userId));
    }

    @PostMapping("/cart/addToCart/equipment/{equipId}/user/{userId}")
    public ResponseEntity<Cart> addEquipmentToCart(@PathVariable long equipId, @PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addEquipmentToCart(equipId,userId));
    }

    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<Cart> fetchCart(@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.displayCart(userId));
    }

    @DeleteMapping("/cart/deleteFromCart/plant/{plantId}/user/{userId}")
    public ResponseEntity<Cart> deletePlantFromCart(@PathVariable long plantId,@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.deletePlantFromCart(plantId,userId));
    }

    @DeleteMapping("/cart/deleteFromCart/equipment/{equipId}/user/{userId}")
    public ResponseEntity<Cart> deleteEquipmentFromCart(@PathVariable long equipId,@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteEquipmentFromCart(equipId,userId));
    }

//                                    WishList CONTROLLER
    @PostMapping("/wishList/addToWishList/plant/{plantId}/user/{userId}")
    public ResponseEntity<WishList> addPlantToWishList(@PathVariable long plantId, @PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.addPlantToWishList(plantId,userId));
    }

    @PostMapping("/wishList/addToWishList/equipment/{equipId}/user/{userId}")
    public ResponseEntity<WishList> addEquipmentToWishList(@PathVariable long equipId, @PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.addEquipmentToWishList(equipId,userId));
    }

    @GetMapping("/wishList/user/{userId}")
    public ResponseEntity<WishList> fetchWishList(@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.displayWishList(userId));
    }

    @DeleteMapping("/wishList/deleteFromWishList/plant/{plantId}/user/{userId}")
    public ResponseEntity<WishList> deletePlantFromWishList(@PathVariable long plantId,@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.deletePlantFromWishList(plantId,userId));
    }

    @DeleteMapping("/wishList/deleteFromWishList/equipment/{equipId}/user/{userId}")
    public ResponseEntity<WishList> deleteEquipmentFromWishList(@PathVariable long equipId,@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.deleteEquipmentFromWishList(equipId,userId));
    }

//                         CONTACTUS CONTROLLER
    @PostMapping("/contactUs")
    public ResponseEntity<String> saveAndSendEmail(@Valid @RequestBody ContactUs contactUs){
        return ResponseEntity.status(HttpStatus.OK)
            .body(contactUsService.saveAndSendEmailToAdminAndAutoReplyPlusManualReply(contactUs));
    }

//                       PAYMENTPROCESS CONTROLLER
    @GetMapping("/payment/apiService")
    public ResponseEntity<String> generatePaymentUrl(@RequestParam String upiId, @RequestParam String name,
                                                     @RequestParam String amount ){

        String paymentUrl = basePaymentUrl +
                "pa=" + URLEncoder.encode(upiId, StandardCharsets.UTF_8) +
                "&pn=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
                "&am=" + amount +
                "&cu=INR";
        return ResponseEntity.status(HttpStatus.OK).body(paymentUrl);
    }

}
 