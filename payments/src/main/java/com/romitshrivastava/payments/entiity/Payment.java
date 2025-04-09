package com.romitshrivastava.payments.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String paymentStatus;
    private String paymentMode;
    private LocalDateTime paymentTime;
    private Long orderId;
    @Column (unique = true)
    private String transactionId;
}
