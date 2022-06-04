package com.example.multikart.common;

public class Const {
    public static final class DefaultStatus {
        public static final Integer DELETED = 0;
        public static final Integer ACTIVE = 1;
        public static final Integer DISABLED = 2;
    }

    public static final class OrderStatus {
        public static final Integer DELETED = 0;
        public static final Integer ACTIVE = 1;
        public static final Integer DISABLED = 2;
        public static final Integer PENDING = 3;
        public static final Integer SHIPPING = 4;
        public static final Integer CANCELED = 5;
        public static final Integer SHIPPED = 6;
    }
}
