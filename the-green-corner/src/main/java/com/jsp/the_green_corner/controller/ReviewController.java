//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.Review;
//import com.jsp.the_green_corner.service.ReviewService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/reviews")
//public class ReviewController {
//
//    private final ReviewService reviewService;
//
//    @Autowired
//    public ReviewController(ReviewService reviewService){
//        this.reviewService=reviewService;
//    }
//
//    @PutMapping("/{reviewId}/like")
//    public ResponseEntity<Review> likeReview(@PathVariable long reviewId){
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.likeReview(reviewId));
//    }
//
//    @PutMapping("/{reviewId}/dislike")
//    public ResponseEntity<Review> dislikeReview(@PathVariable long reviewId){
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.dislikeReview(reviewId));
//    }
//
//}
