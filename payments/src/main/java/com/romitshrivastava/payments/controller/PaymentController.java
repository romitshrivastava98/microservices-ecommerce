package com.romitshrivastava.payments.controller;


import com.romitshrivastava.payments.dto.PaymentDTO;
import com.romitshrivastava.payments.entiity.Payment;
import com.romitshrivastava.payments.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@Slf4j
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentDTO paymentDTO) {
        log.info(paymentDTO.toString());
        System.out.println(paymentDTO.toString());
        Payment payment = paymentService.processPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId) {

        log.info("Getting payment for order id " + orderId);
        Payment payment = paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }
}
