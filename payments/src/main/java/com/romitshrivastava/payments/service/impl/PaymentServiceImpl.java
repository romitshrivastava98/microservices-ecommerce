package com.romitshrivastava.payments.service.impl;


import com.romitshrivastava.payments.dto.PaymentDTO;
import com.romitshrivastava.payments.entiity.Payment;
import com.romitshrivastava.payments.exception.PaymentNotFoundException;
import com.romitshrivastava.payments.mapper.PaymentMapper;
import com.romitshrivastava.payments.repository.PaymentRepository;
import com.romitshrivastava.payments.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment processPayment(PaymentDTO paymentDto) {

        Payment payment = new Payment();

        payment.setOrderId(paymentDto.getOrderId());
        payment.setPaymentMode(paymentDto.getPaymentMode());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setTransactionId(generateTransactionID());
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment;
    }

    private String generateTransactionID() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomString = UUID.randomUUID().toString().replace("-","").
                substring(0,6).toUpperCase();
        return "TXN-"+date+randomString;
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {

        Optional<Payment> payment = paymentRepository.findByOrderId(orderId);
        if(payment.isEmpty()){
            throw new PaymentNotFoundException("Payment not found for order id " + orderId);
        }

        return payment.get();

    }
}
