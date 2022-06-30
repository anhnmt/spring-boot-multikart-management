package com.example.multikart.domain.model;

import com.example.multikart.domain.dto.PaymentRequestDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments", indexes = @Index(columnList = "status"))
public class Payment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @NotBlank
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    // Trạng thái
    @Column(name = "status", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    public Payment(PaymentRequestDTO input) {
        name = input.getName();
        status = input.getStatus();
    }
}
