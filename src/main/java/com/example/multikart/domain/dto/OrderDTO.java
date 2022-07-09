package com.example.multikart.domain.dto;

import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.Order;
import com.example.multikart.domain.model.Payment;
import com.example.multikart.domain.model.Transport;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -1813145955899712226L;

    private Long orderId;
    private String name;

    private Long customerId;
    private String customerName;
    private String phone;
    private String email;

    private Long paymentId;
    private String paymentName;

    private Long transportId;
    private String transportName;

    private String address;

    private String provinceId;

    private String districtId;

    private String wardId;

    private Float totalPrice;

    private Integer status;

    private LocalDateTime createdAt;

    public OrderDTO(Order order, Customer customer) {
        this.orderId = order.getOrderId();
        this.name = order.getName();

        this.address = order.getAddress();

        this.provinceId = order.getProvinceId();

        this.districtId = order.getDistrictId();

        this.wardId = order.getWardId();

        this.totalPrice = order.getTotalPrice();

        this.status = order.getStatus();

        this.createdAt = order.getCreatedAt();

        if (!DataUtils.isNullOrEmpty(customer)) {
            this.customerId = customer.getCustomerId();
            this.customerName = customer.getName();
            this.phone = customer.getPhone();
            this.email = customer.getEmail();
        }
    }

    public OrderDTO(Order order, Customer customer, Payment payment, Transport transport) {
        this.orderId = order.getOrderId();
        this.name = order.getName();

        if (!DataUtils.isNullOrEmpty(payment)) {
            this.paymentId = payment.getPaymentId();
            this.paymentName = payment.getName();
        }

        if (!DataUtils.isNullOrEmpty(transport)) {
            this.transportId = transport.getTransportId();
            this.transportName = transport.getName();
        }

        this.address = order.getAddress();

        this.provinceId = order.getProvinceId();

        this.districtId = order.getDistrictId();

        this.wardId = order.getWardId();

        this.totalPrice = order.getTotalPrice();

        this.status = order.getStatus();

        this.createdAt = order.getCreatedAt();

        if (!DataUtils.isNullOrEmpty(customer)) {
            this.customerId = customer.getCustomerId();
            this.customerName = customer.getName();
            this.phone = customer.getPhone();
            this.email = customer.getEmail();
        }
    }

    public OrderDTO(Order order, Customer customer, Payment payment) {
        this.orderId = order.getOrderId();
        this.name = order.getName();

        if (!DataUtils.isNullOrEmpty(payment)) {
            this.paymentId = payment.getPaymentId();
            this.paymentName = payment.getName();
        }

        this.address = order.getAddress();

        this.provinceId = order.getProvinceId();

        this.districtId = order.getDistrictId();

        this.wardId = order.getWardId();

        this.totalPrice = order.getTotalPrice();

        this.status = order.getStatus();

        this.createdAt = order.getCreatedAt();

        if (!DataUtils.isNullOrEmpty(customer)) {
            this.customerId = customer.getCustomerId();
            this.customerName = customer.getName();
            this.phone = customer.getPhone();
            this.email = customer.getEmail();
        }
    }
}
