//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.Plant;
//import com.jsp.the_green_corner.entity.Review;
//import com.jsp.the_green_corner.service.PlantService;
//import com.jsp.the_green_corner.service.ReviewService;
//
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.FutureOrPresent;
//import org.springframework.data.domain.Page;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/plant")
//public class PlantController {
//
//    private final PlantService plantService;
//    private final ReviewService reviewService;
//
//
//    @Autowired
//    public PlantController(PlantService plantService, ReviewService reviewService){
//        this.plantService=plantService;
//        this.reviewService=reviewService;
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<Plant> savePlant(@Valid @RequestBody Plant plant){
//        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.savePlant(plant));
//    }
//
////    GET MAPPINGS
//
//    @GetMapping("/findAll")
//    public ResponseEntity<List<Plant>> fetchAllPlants(){
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findAllPlants());
//    }
//
//    @GetMapping("/findAllByName/{name}")
//    public ResponseEntity<List<Plant>> fetchPlantByName(@PathVariable String name){
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantByName(name));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Plant> fetchPlantById(@PathVariable Long id){
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantById(id));
//    }
//
//    @PutMapping("/{plantId}/review")
//    public ResponseEntity<Plant> assignReviewToPlant( @PathVariable long plantId,@Valid @RequestBody Review review){
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.addReviewToPlant(plantId,review));
//    }
//
//    @GetMapping("/rating")
//    public ResponseEntity<List<Plant>> fetchPlantsByRating(@RequestParam Double rating){
//
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByRating(rating));
//    }
//
//    @GetMapping("/fetchByPriceLowToHigh")
//    public ResponseEntity<List<Plant>> fetchPlantsByLowToHighPrice(){
//
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsFromLowToHighPrice());
//    }
//
//    @GetMapping("/fetchByPriceHighToLow")
//    public ResponseEntity<List<Plant>> fetchPlantsByPriceHighToLowPrice(){
//
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsFromHighToLowPrice());
//    }
//
//    @GetMapping("/categories")
//    public ResponseEntity<List<Plant>> fetchByCategory(@RequestParam String category){
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByCategory(category));
//    }
//
//    @GetMapping("/page")
//    public ResponseEntity<Page<Plant>> fetchPlantsByPage(@RequestParam(defaultValue = "0") int page,
//                                                         @RequestParam(defaultValue = "3") int size){
//        return ResponseEntity.status(HttpStatus.OK).body(plantService.findPlantsByPage(page,size));
//    }
//}
