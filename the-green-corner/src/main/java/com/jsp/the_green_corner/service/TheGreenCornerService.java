package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TheGreenCornerService {

//                USER BL
    User signupUser(User user);
    User signInUser(String email, String password);
    Delivery placeDelivery(Long userId, Delivery delivery);
    List<User> findAllUsers();
    User findUserById(Long id);
    User findUserByName( String name);
    List<User> findAllUsersByName(String name);
    User updateUserById(Long id,User user);

//                 PLANT BL

    Plant savePlant(Plant plant);
    List<Plant> findAllPlants();
    List<Plant> findPlantByName(String name);
    Plant findPlantById(Long id);
    List<Plant> findPlantsByRating( Double rating);
    List<Plant> findPlantsFromLowToHighPrice();
    List<Plant> findPlantsFromHighToLowPrice();
    List<Plant> findPlantsByCategory( String category);
    Page<Plant> findPlantsByPage(int page, int size);

//                 EQUIPMENT BL

    Equipments saveEquipment(Equipments equipment);
    Equipments findEquipmentById(Long id);
    List<Equipments> findAllEquipments();
    List<Equipments> findEquipmentsByName(String name);
    List<Equipments> findEquipmentsByRating(Double rating);
    List<Equipments> findEquipmentsFromLowToHighPrice();
    List<Equipments> findEquipmentsFromHighToLowPrice();
    List<Equipments> findEquipmentsByCategory( String category);
    Page<Equipments> findEquipmentsByPage(int page, int size);

    //                 CART BL

    Cart addPlantToCart(Long plantId, Long userId);
    Cart addEquipmentToCart( Long equipId,  Long userId);
    Cart displayCart(Long userId);
    Cart deletePlantFromCart( Long plantId,  Long userId);
    Cart deleteEquipmentFromCart( Long equipId,  Long userId);

//                      WISHlIST BL
    WishList addPlantToWishList(Long plantId, Long userId);
    WishList addEquipmentToWishList(Long equipId, Long userId);
    WishList displayWishList(Long userId);
    WishList deletePlantFromWishList(Long plantId, Long userId);
    WishList deleteEquipmentFromWishList( Long equipId,  Long userId);

//                      CONTACT US BL
    String saveAndSendEmailToAdminAndAutoReplyPlusManualReply(ContactUs contactUs);

}
