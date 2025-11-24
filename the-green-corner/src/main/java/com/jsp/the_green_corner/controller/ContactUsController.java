//package com.jsp.the_green_corner.controller;
//
//import com.jsp.the_green_corner.entity.ContactUs;
//import com.jsp.the_green_corner.service.ContactUsService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/contactUs")
//public class ContactUsController {
//
//    private ContactUsService contactUsService;
//    @Autowired
//    public ContactUsController(ContactUsService contactUsService){
//        this.contactUsService=contactUsService;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> saveAndSendEmail(@Valid @RequestBody ContactUs contactUs){
//        return ResponseEntity.status(HttpStatus.OK)
//               .body(contactUsService.saveAndSendEmailToAdminAndAutoReplyPlusManualReply(contactUs));
//    }
//}
