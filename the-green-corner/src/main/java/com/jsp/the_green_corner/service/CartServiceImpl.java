package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Cart;
import com.jsp.the_green_corner.entity.Equipments;
import com.jsp.the_green_corner.entity.Plant;
import com.jsp.the_green_corner.entity.User;
import com.jsp.the_green_corner.exception.*;
import com.jsp.the_green_corner.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final PlantService plantService;
    private final UserService userService;
    private final CartRepository cartRepo;
    private final EquipmentsService equipService;

    @Autowired
    public CartServiceImpl(PlantService plantService, UserService userService, EquipmentsService equipService,CartRepository cartRepo) {
        this.plantService = plantService;
        this.userService = userService;
        this.equipService = equipService;
        this.cartRepo=cartRepo;
    }

    public Cart displayCart(Long userId) {

        try {
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User with provided id " + userId + " does not exist so" +
                        " no cart functionality!");
//            return null;
            }

            Cart cart = user.getCart();
            if (cart == null) {
                //display html page that displays cart is empty but here I am returning empty cart which is null
                return cart;
            }
            return cart;
        }catch (DataAccessException ex){
            throw  new DatabaseException("Database exception occurred while fetching data from your cart! ");
        }
    }



    @Transactional
    public Cart addPlantToCart(Long plantId, Long userId) {
        try {
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User with provided id " + userId + " does not exist so" +
                        " no cart functionality!");
            }
            Plant plant = plantService.findPlantById(plantId);
            if (plant == null) {
                throw new PlantNotFoundException("Plant with provided id " + plantId + " does not exist so" +
                        " cannot add plant with id " + plantId + " to cart!");
            }

            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                user.setCart(cart);
            }

            cart.getPlants().add(plant);
//            Cart savedCart = cartRepo.save(cart);
            return cartRepo.save(cart); // can directly return as their is cascade.all on cart in user entity
//            So no need of this so commenting out..keeping just for learning/understanding purpose for me
//            here, but I have removed in other add and delete  methods in this service:
//            user.setCart(savedCart);
//            return savedCart;
        }catch (DataAccessException ex){
            throw  new DatabaseException("Database exception occurred while adding  plant to your cart! ");

        }
    }

    @Transactional
    public Cart addEquipmentToCart(Long equipId, Long userId) {
        try {
            User user = userService.findUserById(userId);
            if (user == null) {
            throw new UserNotFoundException("User with provided id " + userId + " does not "+
            " exist so cannot add equipment with id " + equipId + "to cart");
//                return null;

            }
            Equipments equip = equipService.findEquipmentById(equipId);
            if (equip == null) {
            throw new EquipmentNotFoundException("Equipment with provided id " + equipId + " does not"+
            " exist so cannot add equipment with id " + equipId + "to cart");
//                return null;
            }

            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                user.setCart(cart);
            }
            cart.getEquipment().add(equip);
            cartRepo.save(cart);
            return cart;

        }catch (DataAccessException ex){
            throw  new DatabaseException("Database exception occurred while adding  equipment to your cart! ");

        }
    }

    @Transactional
    public Cart deletePlantFromCart( Long plantId,  Long userId){
        try {
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User with provided id " + userId + " does not" +
                        " exist so cannot delete plant with id " + plantId + "from cart");
            }


            Cart cart = user.getCart();

            List<Plant> cartPlantsList = cart.getPlants();
            Iterator<Plant> pItr = cartPlantsList.iterator();

            boolean plantfound = false;
            Plant plant;
            while (pItr.hasNext()) {
                plant = pItr.next();
                if (plant.getId().equals(plantId)) {
                    plantfound = true;
                    pItr.remove();
                    break;
                }
            }
            if (!plantfound) {
                throw new PlantNotFoundException("Plant with provided Id" + plantId + " does not exist in cart");
//                return cart;
            }
            cartRepo.save(cart);
            return cart;

        }catch (DataAccessException ex){
            throw new DatabaseException("Database exception occurred while deleting  plant from your cart!");
        }
    }

    @Transactional
    public Cart deleteEquipmentFromCart( Long equipId,  Long userId){

        try {
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User with provided id " + userId + " does not"+
                        " exist so cannot delete equipment with id " + equipId + "from cart");
//                return null;

            }
            Cart cart = user.getCart();

            List<Equipments> cartEquipsList = cart.getEquipment();
            Iterator<Equipments> pItr = cartEquipsList.iterator();

            boolean equipfound = false;
            Equipments equip;
            while (pItr.hasNext()) {
                equip = pItr.next();
                if (equip.getId().equals(equipId)) {
                    equipfound = true;
                    pItr.remove();
                    break;
                }
            }
            if (!equipfound) {
                throw new EquipmentNotFoundException("Equipment with provided Id" + equipId + " does not exist in cart");

            }
            cartRepo.save(cart);
            return cart;

        }catch (DataAccessException ex){
            throw new DatabaseException("Database exception occurred while deleting equipment from your cart!");
        }
    }

}