package com.example.multikart.domain.dto;

import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.Order;
import com.example.multikart.domain.model.Payment;
import com.example.multikart.domain.model.Transport;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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

    private String province;

    private String district;

    private String ward;

    private Float totalPrice;

    public OrderDTO(Order order, Customer customer, Payment payment, Transport transport) {
        this.orderId = order.getOrderId();
        this.name = order.getName();

        this.customerId = customer.getCustomerId();
        this.customerName = customer.getName();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();

        this.paymentId = payment.getPaymentId();
        this.paymentName = payment.getName();

        this.transportId = transport.getTransportId();
        this.transportName = transport.getName();

        this.address = order.getAddress();

        this.province = order.getProvince();

        this.district = order.getDistrict();

        this.ward = order.getWard();

        this.totalPrice = order.getTotalPrice();
    }
}
