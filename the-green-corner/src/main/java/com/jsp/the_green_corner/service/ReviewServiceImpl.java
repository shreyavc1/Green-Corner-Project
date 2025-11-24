package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Equipments;
import com.jsp.the_green_corner.entity.Plant;
import com.jsp.the_green_corner.entity.Review;
import com.jsp.the_green_corner.entity.User;
import com.jsp.the_green_corner.exception.*;
import com.jsp.the_green_corner.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepo;
    private final PlantService plantService;
    private final EquipmentsService equipService;
    private final UserService userService;

    @Autowired
    public ReviewServiceImpl(PlantService plantService, UserService userService,
                             ReviewRepository reviewRepo,EquipmentsService equipService){
        this.equipService=equipService;
        this.plantService=plantService;
        this.userService=userService;
        this.reviewRepo=reviewRepo;

    }

    // here i am ensuring that the username field in review must be a user so while sending json obj of review from
    // postman plz ensure that username is present as name of an existing user than only review will be saved
    public Plant addReviewToPlant(long plantId, Review review) {

        Plant existingPlant = plantService.findPlantById(plantId);

        User existingUser = userService.findUserByName(review.getUsername());
        try {
            if (existingPlant != null && existingUser != null) {

                existingUser.getReviews().add(review);
                existingPlant.getReviews().add(review);
                review.setUser(existingUser);
                review.setPlant(existingPlant);
                review.setEquipment(null);

                reviewRepo.save(review);

                return existingPlant;
            } else {
                if (existingPlant == null) {
                    throw new PlantNotFoundException("Plant with provided id " +
                            plantId + " does not exist");
                } else {
                    throw new UserNotFoundException("User does not exist. You must sign in to add review");
                }
            }
        }catch(DataAccessException ex){
        throw new DatabaseException("Database error occurred while adding review by " +
                   review.getUsername() +" to plant with provided id " + existingPlant.getName() +
                   " having name "  + existingUser.getName());
    }
//        return null;
    }

    public Equipments addReviewToEquipment(Long equipmentId, Review review) {

        Equipments existingEquipment = equipService.findEquipmentById(equipmentId);
        User existingUser = userService.findUserByName(review.getUsername());

        try {
            if (existingUser != null && existingEquipment != null) {

                existingUser.getReviews().add(review);
                existingEquipment.getReviews().add(review);
                review.setUser(existingUser);
                review.setEquipment(existingEquipment);
                review.setPlant(null);
                reviewRepo.save(review);

                return existingEquipment;
            } else {
                if (existingEquipment == null) {
                    throw new EquipmentNotFoundException("Equipment " +
                            "with provided id " + equipmentId + " does not exist");
                } else {
                    throw new UserNotFoundException("User does not exist. You must sign in to " +
                            "add review");
                }
            }
        } catch(DataAccessException ex) {
            throw new DatabaseException("Database error occurred while adding review by " +
                    review.getUsername() + " to equipment with provided id " +
                    existingEquipment.getName() + " having name " + existingUser.getName());
        }
//        return null;
    }

    public Review likeReview(Long reviewId) {
        Review review = findReviewById(reviewId);
        try{
        if (review != null) {
            review.setLikes(review.getLikes() + 1);
            return reviewRepo.save(review);
        }
        }catch(DataAccessException ex){
        throw new DatabaseException("Failed to like review of " + review.getUsername() +
                " with reviewId " + reviewId);
    }
        return null;
    }

    public Review dislikeReview(Long reviewId) {
        Review review = findReviewById(reviewId);
        try {
            if (review != null) {
                review.setDislikes(review.getDislikes() + 1);
                return reviewRepo.save(review);
            }
        }catch(DataAccessException ex){
            throw new DatabaseException("Failed to dislike review of " + review.getUsername() +
                " with reviewId " + reviewId);
        }

        return null;
    }

    public Review findReviewById(Long reviewId){

        if(reviewId!=null && reviewId.toString().matches("^\\d+$")){
            Optional<Review> optionalReview;
            try{
                optionalReview = reviewRepo.findById(reviewId);

            if(optionalReview.isPresent()){
                Review fetchedReview = optionalReview.get();
                return fetchedReview;
            }
            else{
                throw new ReviewNotFoundException("Review with id " + reviewId + " does not exist in database");
            }
        }catch(DataAccessException ex){
                throw new DatabaseException("Database error occurred while finding review with id " + reviewId);
            }
        }
        else{
            throw new InvalidDataFormatException("Review id must contain numeric values only, without any spaces");
        }
    }

}
