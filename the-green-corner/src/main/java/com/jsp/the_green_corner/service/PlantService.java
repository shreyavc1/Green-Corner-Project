package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Plant;
import org.springframework.data.domain.Page;



import java.util.List;

public interface PlantService {

    Plant savePlant(Plant plant);

    List<Plant> findAllPlants();

    List<Plant> findPlantsByName(String name);

    Plant findPlantById(Long id);

    List<Plant> findPlantsByRating( Double rating);
    List<Plant> findPlantsFromLowToHighPrice();
    List<Plant> findPlantsFromHighToLowPrice();

    List<Plant> findPlantsByCategory( String category);
    Page<Plant> findPlantsByPage(int page,int size);

    }
