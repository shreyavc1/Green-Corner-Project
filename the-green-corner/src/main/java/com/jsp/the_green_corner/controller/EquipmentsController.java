//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.Equipments;
//import com.jsp.the_green_corner.entity.Plant;
//import com.jsp.the_green_corner.entity.Review;
//import com.jsp.the_green_corner.service.EquipmentsService;
//import com.jsp.the_green_corner.service.ReviewService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/equipments")
//public class EquipmentsController {
//
//    private final EquipmentsService equipmentsService;
//    private final ReviewService reviewService;
//
//    @Autowired
//    public EquipmentsController(EquipmentsService equipmentsService, ReviewService reviewService){
//        this.equipmentsService=equipmentsService;
//        this.reviewService = reviewService;
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<Equipments> saveEquipment(@Valid @RequestBody Equipments equipment){
//        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentsService.saveEquipment(equipment));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Equipments> fetchEquipmentById(@PathVariable long id){
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentById(id));
//    }
//
//    @GetMapping("/fetchAllByName/{name}")
//    public ResponseEntity<List<Equipments>> fetchAllEquipmentsByName(@PathVariable String name){
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentByName(name));
//    }
//
//    @GetMapping("/fetchAll")
//    public ResponseEntity<List<Equipments>> fetchAllEquipment(){
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findAllEquipments());
//    }
//
//    @PutMapping("/{equipId}/review")
//    public ResponseEntity<Equipments> assignReviewToEquipment( @PathVariable long equipId,@Valid @RequestBody Review review){
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.addReviewToEquipment(equipId,review));
//    }
//
//    @GetMapping("/rating")
//    public ResponseEntity<List<Equipments>> fetchEquipmentsByRating(@RequestParam Double rating){
//
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByRating(rating));
//    }
//
//    @GetMapping("/fetchByPriceLowToHigh")
//    public ResponseEntity<List<Equipments>> fetchEquipmentsByLowToHighPrice(){
//
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsFromLowToHighPrice());
//    }
//
//    @GetMapping("/fetchByPriceHighToLow")
//    public ResponseEntity<List<Equipments>> fetchEquipmentsByPriceHighToLowPrice(){
//
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsFromHighToLowPrice());
//    }
//
//    @GetMapping("/categories")
//    public ResponseEntity<List<Equipments>> fetchEquipmentsByCategory(@RequestParam String category){
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByCategory(category));
//    }
//
//    @GetMapping("/page")
//    public ResponseEntity<Page<Equipments>> fetchEquipmentsByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
//        return ResponseEntity.status(HttpStatus.OK).body(equipmentsService.findEquipmentsByPage(page,size));
//    }
//}
