package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Equipments;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EquipmentsService {

    Equipments saveEquipment(Equipments equipment);

    Equipments findEquipmentById(Long id);
    List<Equipments> findAllEquipments();
    List<Equipments> findEquipmentsByName(String name);
    List<Equipments> findEquipmentsByRating(Double rating);

    List<Equipments> findEquipmentsFromLowToHighPrice();
    List<Equipments> findEquipmentsFromHighToLowPrice();
    List<Equipments> findEquipmentsByCategory( String category);
    Page<Equipments> findEquipmentsByPage(int page, int size);

    String deleteEquipmentById(Long id);
}
