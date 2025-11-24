package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Plant;
import com.jsp.the_green_corner.entity.Review;
import com.jsp.the_green_corner.entity.User;
import com.jsp.the_green_corner.exception.InvalidDataFormatException;
import com.jsp.the_green_corner.exception.PlantNotFoundException;
import com.jsp.the_green_corner.repository.PlantRepository;
import com.jsp.the_green_corner.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepo;
    private final UserRepository userRepo;


    @Autowired
    public PlantServiceImpl(PlantRepository plantRepo, UserRepository userRepo) {
        this.plantRepo = plantRepo;
        this.userRepo=userRepo;
    }

    @Transactional
    public Plant savePlant(Plant plant) {

        if (plant.getReviews() != null) {
            for (Review r : plant.getReviews()) {

                if (r.getUsername() == null || r.getUsername().isBlank()) continue;

                Optional<User> optionalUser = userRepo.findByName(r.getUsername());

                if (optionalUser.isPresent()) {

                    User user = optionalUser.get();

                    // Link Review and User
                    r.setUser(user);
                    user.getReviews().add(r);
                }

                // Link Review and Plant
                r.setEquipment(null);
                r.setPlant(plant);

            }
        }

        return plantRepo.save(plant);
    }

//    FIND BL IMPL

    public List<Plant> findAllPlants() {

        List<Plant> plantsList = plantRepo.findAll();
        if (!plantsList.isEmpty()) {
            return plantsList;
        }
//
        throw new PlantNotFoundException("Their are no plants in database");
//        return null;
    }

    public List<Plant> findPlantsByName(String name) {

        if (validatePlantName(name)) {
            List<Plant> plantsList = plantRepo.searchPlants(name);
            if (!plantsList.isEmpty()) {
                return plantsList;
            }
        }
        else{
            throw new InvalidDataFormatException("Name must contain uppercase,lowercase letters and spaces only!");
        }
        throw new PlantNotFoundException("Plants with provided name " + name + " are not available!");
//        return null;
    }

    public Plant findPlantById( Long id) {

        if(id != null && id.toString().matches("^\\d+$")){
            Optional<Plant> optionalPlant = plantRepo.findById(id);
            if(optionalPlant.isPresent()){
                Plant fetchedPlant = optionalPlant.get();
                return fetchedPlant;
            }
        }
        else{
            throw new InvalidDataFormatException("Please check if id you are providing is of valid format!");
        }
        throw new PlantNotFoundException("Plant with provided id " + id + " does not exist.");
//        return null;
    }

    public List<Plant> findPlantsByRating( Double rating){
        List<Plant> plantsList;
       if(Double.compare(rating,5.0) ==0){
           plantsList = plantRepo.findByRating(rating);
           return plantsList;

       }
       else{
           plantsList = plantRepo.findByRatingRange(rating,rating+0.9);
           return plantsList;
       }
    }

    public List<Plant> findPlantsFromLowToHighPrice() {

        List<Plant> plantsList = plantRepo.findByLowToHighPrice();
        if (!plantsList.isEmpty()) {
            return plantsList;
        }

        throw new PlantNotFoundException("No plants in database!");
    }

    public List<Plant> findPlantsFromHighToLowPrice() {

        List<Plant> plantsList = plantRepo.findByHighToLowPrice();
        if (!plantsList.isEmpty()) {
            return plantsList;
        }

        throw new PlantNotFoundException("No plants in database!");
    }

    public List<Plant> findPlantsByCategory( String category){

//        List<Plant> plantsList = findAllPlants();
//        List<Plant> matchedPlantsList=null;
//        for(Plant p: plantsList){
//            if(p.getCategories().contains(category)){
//                matchedPlantsList.add(p);
//            }
//        }
//       if(matchedPlantsList == null){
//            render html page where no plants belong in this category
//        }
//        return matchedPlantsList;

        List<Plant> plantsList = plantRepo.findByCategory(category);
        if (plantsList == null){
            throw new PlantNotFoundException("Their are no plants that belong to "+category+" category");
        }
        return plantsList;

    }

    public Page<Plant> findPlantsByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return plantRepo.findAll(pageable);
    }


    //    Utility functions
    private boolean validatePlantName(String name) {

        return  name!=null && !name.isEmpty() && name.matches("^[A-Za-z ]+$") ;
    }

}
