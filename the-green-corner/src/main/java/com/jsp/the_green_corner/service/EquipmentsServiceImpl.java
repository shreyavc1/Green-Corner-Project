package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Equipments;
import com.jsp.the_green_corner.entity.Plant;
import com.jsp.the_green_corner.entity.Review;
import com.jsp.the_green_corner.entity.User;
import com.jsp.the_green_corner.exception.DatabaseException;
import com.jsp.the_green_corner.exception.EquipmentNotFoundException;
import com.jsp.the_green_corner.exception.InvalidDataFormatException;
import com.jsp.the_green_corner.repository.EquipmentsRepository;
import com.jsp.the_green_corner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class EquipmentsServiceImpl implements EquipmentsService {

    private final EquipmentsRepository equipmentsRepo;
    private final UserRepository userRepo;


    @Autowired
    public EquipmentsServiceImpl(EquipmentsRepository equipmentsRepo, UserRepository userRepo) {
        this.userRepo = userRepo;
        this.equipmentsRepo = equipmentsRepo;
    }

//    Saving to DB BL IMPL
@Transactional
public Equipments saveEquipment(Equipments equipment) {

    if (equipment.getReviews() != null) {
        for (Review r : equipment.getReviews()) {

            if (r.getUsername() == null || r.getUsername().isBlank()) continue;

            Optional<User> optionalUser = userRepo.findByName(r.getUsername());

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();

                r.setUser(user);
                user.getReviews().add(r);
            }

            r.setPlant(null);
            r.setEquipment(equipment);
        }
    }

;    return equipmentsRepo.save(equipment);
}

//    FETCHING BL IMPL
    public Equipments findEquipmentById(Long id){


            if (id != null && id.toString().matches("^\\d+$")) {
                try {
                    Optional<Equipments> optinalEqip = equipmentsRepo.findById(id);

                    if (optinalEqip.isPresent()) {
                        Equipments fetchedEqip = optinalEqip.get();
                        return fetchedEqip;
                    }
                }catch(DataAccessException ex){
                    throw new DatabaseException("Database error occurred while finding " +
                            "equipment with id " + id + " in database!");                }

                }
         else {
                throw new InvalidDataFormatException("Id can have only numeric values!");
         }

        throw new EquipmentNotFoundException("Equipment with provided id " + id + " does not exist.");
//            return null;
    }

    public List<Equipments> findAllEquipments() {
        try{
        List<Equipments> equipsList = equipmentsRepo.findAll();
        if (!equipsList.isEmpty()) {
            return equipsList;
        } else{
            throw new EquipmentNotFoundException("Equipments List is Empty!");
        }
        } catch (DataAccessException ex){
            throw new DatabaseException("Database error occurred while finding all equipments " +
                    "from database!");
        }
//        return null;
    }

    public List<Equipments> findEquipmentsByName(String name) {

        if (validateEquipmentName(name)) {
            try {

                List<Equipments> equipsList = equipmentsRepo.findAllByNameIgnoreCase(name);
                if (!equipsList.isEmpty()) {
                    return equipsList;
                } else {
                    throw new EquipmentNotFoundException("Equipments with provided name " + name + " are not available!");
                }
            } catch (DataAccessException ex) {
                throw new DatabaseException("Database error occurred while finding  equipments " +
                        "with provided name " + name + " from database!");
            }
        }
        else{
                throw new InvalidDataFormatException("Name must contain letters and spaces only!");
        }
    }

    public List<Equipments> findEquipmentsByRating( Double rating){
        List<Equipments> equipsList;
        try {
            if (Double.compare(rating, 5.0) == 0) {
                equipsList = equipmentsRepo.findByRating(rating);
                if(equipsList.isEmpty()){
                    throw new EquipmentNotFoundException("No equipments present with provided " +
                            "rating" + rating);
                }
            } else {
                equipsList = equipmentsRepo.findByRatingRange(rating, rating + 0.9);
                if(equipsList.isEmpty()){
                    throw new EquipmentNotFoundException("No equipments present with provided " +
                            "rating range" + rating + "-" + rating+0.9);
                }
            }
        }catch (DataAccessException ex){
            throw new DatabaseException("Database error occurred while finding  equipments " +
                    "by rating " + rating + " from database!");
        }

        return equipsList;
    }

    public List<Equipments> findEquipmentsFromLowToHighPrice() {
        try {
            List<Equipments> equipsList = equipmentsRepo.findByLowToHighPrice();
            if (!equipsList.isEmpty()) {
                return equipsList;
            } else {
                throw new EquipmentNotFoundException("Equipments is Empty!");
            }
        } catch (DataAccessException e) {
            throw new DatabaseException("Database error occurred while fetching equipments from high to low " +
                    "price from database!");
        }
//        return null;
    }

    public List<Equipments> findEquipmentsFromHighToLowPrice() {
        try {
            List<Equipments> equipsList = equipmentsRepo.findByHighToLowPrice();
            if (!equipsList.isEmpty()) {
                return equipsList;
            } else {
                throw new EquipmentNotFoundException("Equipments is Empty!");
            }
        }catch (DataAccessException ex) {
            throw new DatabaseException("Database error occurred while finding all equipments in wishlist from database!");
        }
//        return null;
    }

//    DELETE BL IMPL BUT ACTUALLY WE WILL NOT DELETE ANY EQUIPMENT WE WILL MAKE IT UNAVAILABLE BUT IF IN CASE OR ACC TO REQUIREMENT WE WANT TO DELETE
    public String deleteEquipmentById(Long id){

            Equipments existingEquip = findEquipmentById(id);
            if(existingEquip!=null){

                equipmentsRepo.deleteById(id);
                return "Equipment with provided id " + id + " having name " + existingEquip.getName() + " deleted successfully!";
            }

        return "Equipment with provided id " + id + " not deleted!";
    }

    public List<Equipments> findEquipmentsByCategory( String category){
        List<Equipments> equipsList = equipmentsRepo.findByCategory(category);
        return equipsList;
    }

    public Page<Equipments> findEquipmentsByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return equipmentsRepo.findAll(pageable);
    }



    //    UTILITY FUNCTIONS
    private boolean validateEquipmentName(String name){
        return name!=null && !name.isEmpty() && name.matches("^[a-zA-Z0-9 ]+$");
    }
}