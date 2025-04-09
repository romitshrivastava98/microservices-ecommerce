package com.romitshrivastava.payments.service;

import com.romitshrivastava.payments.dto.PaymentDTO;
import com.romitshrivastava.payments.entiity.Payment;

public interface PaymentService {
    Payment processPayment(PaymentDTO paymentDto);

    Payment getPaymentByOrderId(Long orderId);
}
