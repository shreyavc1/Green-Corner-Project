//package com.jsp.the_green_corner.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//@RestController
//public class PaymentProcessController {
//
//    private String basePaymentUrl = "upi://pay?";
//
//    @GetMapping("/payment/apiService")
//    public ResponseEntity<String> generatePaymentUrl(@RequestParam String upiId, @RequestParam String name,
//                                                     @RequestParam Double amount ){
//
//        String paymentUrl = basePaymentUrl +
//                "pa=" + URLEncoder.encode(upiId, StandardCharsets.UTF_8) +
//                "&pn=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
//                "&am=" + amount +
//                "&cu=INR";
//        return ResponseEntity.status(HttpStatus.OK).body(paymentUrl);
//
//    }
//}
