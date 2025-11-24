package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.Equipments;
import com.jsp.the_green_corner.entity.Plant;
import com.jsp.the_green_corner.entity.Review;

public interface ReviewService {

   Plant addReviewToPlant(long plantId, Review review);
   Equipments addReviewToEquipment(Long equipmentId, Review review);
    Review likeReview(Long reviewId);
    Review dislikeReview(Long reviewId);
    Review findReviewById(Long reviewId);
}
