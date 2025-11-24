package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Cart;
import org.springframework.web.bind.annotation.PathVariable;

public interface CartService {

    Cart addPlantToCart(Long plantId,Long userId);

    Cart addEquipmentToCart( Long equipId,  Long userId);

    Cart displayCart(Long userId);

    Cart deletePlantFromCart( Long plantId,  Long userId);
    Cart deleteEquipmentFromCart( Long equipId,  Long userId);
}
