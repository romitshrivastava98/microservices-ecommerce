package com.romitshrivastava.payments.mapper;

import com.romitshrivastava.payments.dto.PaymentDTO;
import com.romitshrivastava.payments.entiity.Payment;

public class PaymentMapper {

    public static PaymentDTO mapToPaymentDto(Payment payment, PaymentDTO paymentDTO){

        return paymentDTO;
    }

    public static Payment mapToPayment(Payment payment,PaymentDTO paymentDTO){


        return payment;
    }


}