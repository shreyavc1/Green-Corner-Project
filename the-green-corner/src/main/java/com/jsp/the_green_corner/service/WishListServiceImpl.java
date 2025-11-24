package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.*;
import com.jsp.the_green_corner.exception.*;
import com.jsp.the_green_corner.repository.CartRepository;
import com.jsp.the_green_corner.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService{

        private final PlantService plantService;
        private final UserService userService;
        private final WishListRepository wishListRepo;
        private final EquipmentsService equipService;

        @Autowired
        public WishListServiceImpl(PlantService plantService, UserService userService, EquipmentsService equipService,WishListRepository wishListRepo) {
            this.plantService = plantService;
            this.userService = userService;
            this.equipService = equipService;
            this.wishListRepo=wishListRepo;
        }

        public WishList displayWishList(Long userId) {
            try {
                User user = userService.findUserById(userId);
                if (user == null) {
                    throw new UserNotFoundException("User with provided id " + userId + " does not exist so" +
                            " no wishlist functionality!");
//                return null;
                }

                WishList wishList = user.getWishList();
                if (wishList == null) {
                    //display html page that displays wishlist is empty
                    throw new WishListException("Wishlist is empty!");
//                return null;
                }
                return wishList;
            }catch (DataAccessException ex){
                throw new DatabaseException("Database exception occurred while fetching data from your wishlist");
            }
        }

        @Transactional
        public WishList addPlantToWishList(Long plantId, Long userId) {
            try {


                User user = userService.findUserById(userId);
                if (user == null) {
                    throw new PlantNotFoundException("User with provided id " + userId + " does not exist so" +
                            " cannot add this plant to wishlist!");
//                return null;
                }
                Plant plant = plantService.findPlantById(plantId);
                if (plant == null) {
                    throw new PlantNotFoundException("Plant with provided id " + plantId + " does not exist so" +
                            " cannot add this plant to wishList!");
//                return null;
                }

                WishList wishList = user.getWishList();

                wishList.getPlants().add(plant);
                WishList savedWishList = wishListRepo.save(wishList);
                user.setWishList(savedWishList);
                return savedWishList;
            }catch(DataAccessException ex){
                throw  new DatabaseException("Database exception occurred while adding  plant to your wishlist! ");

            }
        }

        @Transactional
        public WishList addEquipmentToWishList(Long equipId, Long userId) {
            try {
                User user = userService.findUserById(userId);
                if (user == null) {
                    throw new UserNotFoundException("User with provided id " + userId + " does not"+
                    " exist so cannot add equipment with id " + equipId + "to wishList");
//                    return null;
                }
                Equipments equip = equipService.findEquipmentById(equipId);
                if (equip == null) {
                    throw new EquipmentNotFoundException("Equipment with provided id " + equipId + " does not"+
                    " exist so cannot add equipment with id " + equipId + "to wishList");
//                    return null;
                }

                WishList wishList = user.getWishList();
                wishList.getEquipment().add(equip);
                WishList savedWishList = wishListRepo.save(wishList);
                user.setWishList(savedWishList);
                return savedWishList;
            }catch(DataAccessException ex){
                throw  new DatabaseException("Database exception occurred while adding  equipment to your wishlist! ");
            }
        }

        @Transactional
    public WishList deletePlantFromWishList( Long plantId,  Long userId){
        try {
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User with provided id " + userId + " does not" +
                        " exist so cannot delete plant with id " + plantId + "from wishList");
//            return null;
            }
            WishList wishList = user.getWishList();

            List<Plant> wishListPlantsList = wishList.getPlants();
            Iterator<Plant> pItr = wishListPlantsList.iterator();

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
                throw new PlantNotFoundException("Plant with provided Id" + plantId + " does not exist in wishlist");
//                return wishList;
            }
            WishList savedWishList = wishListRepo.save(wishList);
            user.setWishList(savedWishList);
            return savedWishList;
        }catch(DataAccessException ex){
            throw new DatabaseException("Database exception occurred while deleting  plant from your wishlist!");
        }
    }

    @Transactional
    public WishList deleteEquipmentFromWishList( Long equipId,  Long userId){
        try {
            User user = userService.findUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User with provided id " + userId + " does not"+
                " exist so cannot delete equipment with id " + equipId + "from wishList");
//                return null;
            }
            WishList wishList = user.getWishList();

            List<Equipments> equipsWishList = wishList.getEquipment();
            Iterator<Equipments> pItr = equipsWishList.iterator();

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
                throw new EquipmentNotFoundException("Equipment with provided Id" + equipId + " does not exist in wishlist");
//                return wishList;
            }
            WishList savedWishList = wishListRepo.save(wishList);
            user.setWishList(savedWishList);
            return savedWishList;
        }catch(DataAccessException ex){
            throw new DatabaseException("Database exception occurred while deleting  plant from your wishlist!");
        }
    }

}


