package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Cart;
import com.jsp.the_green_corner.entity.WishList;

public interface WishListService {
    WishList addPlantToWishList(Long plantId, Long userId);
    WishList addEquipmentToWishList(Long equipId, Long userId);
    WishList displayWishList(Long userId);
    WishList deletePlantFromWishList(Long plantId, Long userId);
    WishList deleteEquipmentFromWishList( Long equipId,  Long userId);
}
