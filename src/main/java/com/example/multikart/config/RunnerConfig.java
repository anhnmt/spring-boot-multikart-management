package com.example.multikart.config;

import com.example.multikart.domain.model.*;
import com.example.multikart.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RunnerConfig implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        roleSeeder();
        userSeeder();
        unitSeeder();
        categorySeeder();
        transportSeeder();
        paymentSeeder();
        supplierSeeder();
        customerSeeder();
    }

    private void roleSeeder() {
        var count = roleRepository.count();
        if (count <= 0) {
            List<Role> roles = new ArrayList<>();
            roles.add(Role.builder()
                    .name("ADMIN")
                    .status(1)
                    .build());
            roles.add(Role.builder()
                    .name("USER")
                    .status(1)
                    .build());

            roleRepository.saveAll(roles);
        }
    }

    private void userSeeder() {
        String encodedPassword = bcryptPasswordEncoder.encode("123456");

        var count = userRepository.count();
        if (count <= 0) {
            List<User> users = new ArrayList<>();
            users.add(User.builder()
                    .email("admin@gmail.com")
                    .name("Trịnh Văn Musk")
                    .roleId(1L)
                    .password(encodedPassword)
                    .status(1)
                    .build());
            users.add(User.builder()
                    .email("user@gmail.com")
                    .name("Đỗ Nam Trung")
                    .roleId(2L)
                    .password(encodedPassword)
                    .status(1)
                    .build());

            userRepository.saveAll(users);
        }
    }

    private void unitSeeder() {
        var count = unitRepository.count();
        if (count <= 0) {
            List<Unit> units = new ArrayList<>();
            units.add(Unit.builder()
                    .name("Hộp")
                    .status(1)
                    .build());
            units.add(Unit.builder()
                    .name("Gói")
                    .status(1)
                    .build());
            units.add(Unit.builder()
                    .name("Khay")
                    .status(1)
                    .build());
            units.add(Unit.builder()
                    .name("Thùng")
                    .status(1)
                    .build());
            units.add(Unit.builder()
                    .name("Lon")
                    .status(1)
                    .build());
            units.add(Unit.builder()
                    .name("Chai")
                    .status(1)
                    .build());

            unitRepository.saveAll(units);
        }
    }

    private void categorySeeder() {
        var count = categoryRepository.count();
        if (count <= 0) {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder()
                    .name("Rau - Củ - Trái Cây")
                    .slug("rau-cu-trai-cay")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Thịt - Trứng - Hải Sản")
                    .slug("thit-trung-hai-san")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Thực Phẩm Chế Biến")
                    .slug("thuc-pham-che-bien")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Thực Phẩm Đông Lạnh")
                    .slug("thuc-pham-dong-lanh")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Thực Phẩm Khô - Gia Vị")
                    .slug("thuc-pham-kho-gia-vi")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Bánh Kẹo - Đồ Ăn Vặt")
                    .slug("banh-keo-do-an-vat")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Sữa - Sản Phẩm Từ Sữa")
                    .slug("sua-san-pham-tu-sua")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Đồ Uống - Giải Khát")
                    .slug("do-uong-giai-khat")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Hóa Mỹ Phẩm")
                    .slug("hoa-my-pham")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Chăm Sóc Cá Nhân")
                    .slug("cham-soc-ca-nhan")
                    .status(1)
                    .build());
            categories.add(Category.builder()
                    .name("Chăm Sóc Mẹ Và Bé")
                    .slug("cham-soc-me-va-be")
                    .status(1)
                    .build());

            categoryRepository.saveAll(categories);
        }
    }

    private void transportSeeder() {
        var count = transportRepository.count();
        if (count <= 0) {
            List<Transport> transports = new ArrayList<>();
            transports.add(Transport.builder()
                    .name("Giao Hàng Nhanh")
                    .status(1)
                    .build());
            transports.add(Transport.builder()
                    .name("Giao Hàng Tiết Kiệm")
                    .status(1)
                    .build());
            transports.add(Transport.builder()
                    .name("Viettel Post")
                    .status(1)
                    .build());

            transportRepository.saveAll(transports);
        }
    }

    private void paymentSeeder() {
        var count = paymentRepository.count();
        if (count <= 0) {
            List<Payment> payments = new ArrayList<>();
            payments.add(Payment.builder()
                    .name("Thanh toán trực tuyến (Online)")
                    .status(1)
                    .build());
            payments.add(Payment.builder()
                    .name("Thanh toán tiền mặt")
                    .status(1)
                    .build());

            paymentRepository.saveAll(payments);
        }
    }

    private void supplierSeeder() {
        var count = supplierRepository.count();
        if (count <= 0) {
            List<Supplier> suppliers = new ArrayList<>();
            suppliers.add(Supplier.builder()
                    .name("Công ty TNHH Thương Mại Và Dịch Vụ Thực Phẩm Số 1")
                    .address("Số 1, Đường 3/2, Phường Tân Phú, Quận 7, TP. Hồ Chí Minh")
                    .email("thucpham@gmail.com")
                    .phone("0987654321")
                    .status(1)
                    .build());
            suppliers.add(Supplier.builder()
                    .name("Công ty TNHH Thương Mại Và Dịch Vụ Thực Phẩm Số 2")
                    .address("Số 111, Hai bà trưng, Hà Nội")
                    .email("thucpham2@gmail.com")
                    .phone("0987654321")
                    .status(1)
                    .build());
            suppliers.add(Supplier.builder()
                    .name("Công ty CP Thực Phẩm Số 3")
                    .address("Tầng 6, Toà nhà trung tâm Quốc tế, số 17 Ngô Quyền, Phường Tràng Tiền, Quận Hoàn Kiếm, Thành Phố Hà Nội")
                    .email("thucphamso3@gmail.com")
                    .phone("0987654321")
                    .status(1)
                    .build());

            supplierRepository.saveAll(suppliers);
        }
    }

    private void customerSeeder() {
        String encodedPassword = bcryptPasswordEncoder.encode("123456");

        var count = customerRepository.count();
        if (count <= 0) {
            List<Customer> customers = new ArrayList<>();
            customers.add(Customer.builder()
                    .email("cus@gmail.com")
                    .name("Trương Vô Kỵ")
                    .password(encodedPassword)
                    .status(1)
                    .build());
            customers.add(Customer.builder()
                    .email("cus2@gmail.com")
                    .name("Hoàng Được Sư")
                    .password(encodedPassword)
                    .status(1)
                    .build());

            customerRepository.saveAll(customers);
        }

    }
}