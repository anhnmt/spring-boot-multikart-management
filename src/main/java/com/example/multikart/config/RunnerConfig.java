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

    @Autowired
    private ProductRepository productRepository;

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
            roles.add(Role.builder().name("ADMIN").status(1).build());
            roles.add(Role.builder().name("USER").status(1).build());

            roleRepository.saveAll(roles);
        }
    }

    private void userSeeder() {
        String encodedPassword = bcryptPasswordEncoder.encode("123456");

        var count = userRepository.count();
        if (count <= 0) {
            List<User> users = new ArrayList<>();
            users.add(User.builder().email("admin@gmail.com").name("Trịnh Văn Musk").roleId(1L).password(encodedPassword).status(1).build());
            users.add(User.builder().email("user@gmail.com").name("Đỗ Nam Trung").roleId(2L).password(encodedPassword).status(1).build());

            userRepository.saveAll(users);
        }
    }

    private void unitSeeder() {
        var count = unitRepository.count();
        if (count <= 0) {
            List<Unit> units = new ArrayList<>();
            units.add(Unit.builder().name("Hộp").status(1).build());
            units.add(Unit.builder().name("Gói").status(1).build());
            units.add(Unit.builder().name("Khay").status(1).build());
            units.add(Unit.builder().name("Thùng").status(1).build());
            units.add(Unit.builder().name("Lon").status(1).build());
            units.add(Unit.builder().name("Chai").status(1).build());
            units.add(Unit.builder().name("Quả").status(1).build());
            units.add(Unit.builder().name("1 Kg").status(1).build());

            unitRepository.saveAll(units);
        }
    }

    private void categorySeeder() {
        var count = categoryRepository.count();
        if (count <= 0) {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder().name("Rau - Củ - Trái Cây").slug("rau-cu-trai-cay").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Thịt - Trứng - Hải Sản").slug("thit-trung-hai-san").icon("/assets/svg/vegetables/meats.svg").status(1).build());
            categories.add(Category.builder().name("Thực Phẩm Chế Biến").slug("thuc-pham-che-bien").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Thực Phẩm Đông Lạnh").slug("thuc-pham-dong-lanh").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Thực Phẩm Khô - Gia Vị").slug("thuc-pham-kho-gia-vi").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Bánh Kẹo - Đồ Ăn Vặt").slug("banh-keo-do-an-vat").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Sữa - Sản Phẩm Từ Sữa").slug("sua-san-pham-tu-sua").icon("/assets/svg/vegetables/milk.svg").status(1).build());
            categories.add(Category.builder().name("Đồ Uống - Giải Khát").slug("do-uong-giai-khat").icon("/assets/svg/vegetables/drink.svg").status(1).build());
            categories.add(Category.builder().name("Hóa Mỹ Phẩm").slug("hoa-my-pham").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Chăm Sóc Cá Nhân").slug("cham-soc-ca-nhan").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());
            categories.add(Category.builder().name("Chăm Sóc Mẹ Và Bé").slug("cham-soc-me-va-be").icon("/assets/svg/vegetables/vegetable.svg").status(1).build());

            categoryRepository.saveAll(categories);
        }
    }

    private void transportSeeder() {
        var count = transportRepository.count();
        if (count <= 0) {
            List<Transport> transports = new ArrayList<>();
            transports.add(Transport.builder().name("Giao Hàng Nhanh").status(1).build());
            transports.add(Transport.builder().name("Giao Hàng Tiết Kiệm").status(1).build());
            transports.add(Transport.builder().name("Viettel Post").status(1).build());

            transportRepository.saveAll(transports);
        }
    }

    private void paymentSeeder() {
        var count = paymentRepository.count();
        if (count <= 0) {
            List<Payment> payments = new ArrayList<>();
            payments.add(Payment.builder().name("Thanh toán trực tuyến (Online)").status(1).build());
            payments.add(Payment.builder().name("Thanh toán tiền mặt").status(1).build());

            paymentRepository.saveAll(payments);
        }
    }

    private void supplierSeeder() {
        var count = supplierRepository.count();
        if (count <= 0) {
            List<Supplier> suppliers = new ArrayList<>();
            suppliers.add(Supplier.builder().name("Công ty TNHH Thương Mại Và Dịch Vụ Thực Phẩm Số 1").address("Số 1, Đường 3/2, Phường Tân Phú, Quận 7, TP. Hồ Chí Minh").email("thucpham@gmail.com").phone("0987654321").status(1).build());
            suppliers.add(Supplier.builder().name("Công ty TNHH Thương Mại Và Dịch Vụ Thực Phẩm Số 2").address("Số 111, Hai bà trưng, Hà Nội").email("thucpham2@gmail.com").phone("0987654321").status(1).build());
            suppliers.add(Supplier.builder().name("Công ty CP Thực Phẩm Số 3").address("Tầng 6, Toà nhà trung tâm Quốc tế, số 17 Ngô Quyền, Phường Tràng Tiền, Quận Hoàn Kiếm, Thành Phố Hà Nội").email("thucphamso3@gmail.com").phone("0987654321").status(1).build());

            supplierRepository.saveAll(suppliers);
        }
    }

    private void customerSeeder() {
        String encodedPassword = bcryptPasswordEncoder.encode("123456");

        var count = customerRepository.count();
        if (count <= 0) {
            List<Customer> customers = new ArrayList<>();
            customers.add(Customer.builder().email("cus@gmail.com").name("Trương Vô Kỵ").password(encodedPassword).status(1).build());
            customers.add(Customer.builder().email("cus2@gmail.com").name("Hoàng Được Sư").password(encodedPassword).status(1).build());

            customerRepository.saveAll(customers);
        }
    }

    private void productSeeder() {

        var count = productRepository.count();
        if (count <= 0) {
            List<Product> products = new ArrayList<>();
            //category 1
            products.add(Product.builder().name("Táo Queen New Zealand PG size 90-120").categoryId(1L).unitId(1L).supplierId(1L).slug("tao-queen-new-zealand-pg-size-90-120").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dưa lưới sạch Đế Vương King size ruột xanh").categoryId(1L).unitId(1L).supplierId(1L).slug("dua-luoi-de-vuong-ruot-xanh-vineco-king").importPrice(99.900F).exportPrice(109.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bí xanh (bí đao) WinEco").categoryId(1L).unitId(1L).supplierId(1L).slug("bi-xanh-bi-dao-loai-1-vineco").importPrice(10.450F).exportPrice(11.450F).amount(999).status(1).build());
            products.add(Product.builder().name("Lê đỏ Nam phi").categoryId(1L).unitId(1L).supplierId(1L).slug("le-do-nam-phi").importPrice(39.950F).exportPrice(59.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Súp lơ (Bông cải) xanh").categoryId(1L).unitId(1L).supplierId(1L).slug("sup-lo-bong-cai-xanh").importPrice(42.000F).exportPrice(59.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Hành tây").categoryId(1L).unitId(1L).supplierId(1L).slug("hanh-tay").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Đậu cove").categoryId(1L).unitId(1L).supplierId(1L).slug("dau-cove").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Măng nứa tươi Kim Bôi túi 500g").categoryId(1L).unitId(1L).supplierId(1L).slug("mang-nua-tuoi-kim-boi-goi-500g").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nấm Bunashimeji Biovegi hộp 100g").categoryId(1L).unitId(1L).supplierId(1L).slug("biovegi-nam-bunashimeji-goi-100g").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dưa lê trắng").categoryId(1L).unitId(1L).supplierId(1L).slug("dua-le-trang").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dưa hấu Sài Gòn").categoryId(1L).unitId(1L).supplierId(1L).slug("dua-hau-sai-gon").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Thanh long").categoryId(1L).unitId(1L).supplierId(1L).slug("thanh-long").importPrice(99.900F).exportPrice(79.900F).amount(999).status(1).build());

            //category 2
            products.add(Product.builder().name("Thịt nạc vai heo Meat Deli size S").categoryId(2L).unitId(1L).supplierId(1L).slug("thit-nac-vai-heo-meat-deli-size-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nạc dăm heo (nạc vai) Meat Deli (S)").categoryId(2L).unitId(1L).supplierId(1L).slug("nac-dam-heo-nac-vai-meat-deli-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Thịt heo xay đặc biệt Meat Deli (S)").categoryId(2L).unitId(1L).supplierId(1L).slug("thit-heo-xay-dac-biet-meat-deli-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Thịt đùi heo Meat Deli (S)").categoryId(2L).unitId(1L).supplierId(1L).slug("thit-dui-heo-meat-deli-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Thăn chuột heo (S) Meat Deli").categoryId(2L).unitId(1L).supplierId(1L).slug("than-chuot-heo-s-meat-deli").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Thịt vai heo Meat Deli (S)").categoryId(2L).unitId(1L).supplierId(1L).slug("thit-vai-heo-meat-deli-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sườn St. Louis MeatDeli Premium (S)").categoryId(2L).unitId(1L).supplierId(1L).slug("meat-deli-pre-suon-st-louis-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sụn heo MeatDeli Premium (S)").categoryId(2L).unitId(1L).supplierId(1L).slug("meat-deli-pre-sun-heo-s").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 3
            products.add(Product.builder().name("Giò tai lưỡi xào Thu Hằng Food gói 250g").categoryId(3L).unitId(1L).supplierId(1L).slug("gio-tai-luoi-xao-thu-hang-food-goi-250g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Kim chi cải thảo cắt lát Ông Kim's túi 100g").categoryId(3L).unitId(1L).supplierId(1L).slug("ong-kims-kim-chi-cai-thao-cat-lat-100g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Xúc xích Roma Đức Việt gói 500g").categoryId(3L).unitId(1L).supplierId(1L).slug("duc-viet-xuc-xich-roma-goi-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Xúc xích Klobasa Ông Già Ika gói 500g").categoryId(3L).unitId(1L).supplierId(1L).slug("ong-gia-ika-xuc-xich-klobasa-goi-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Xúc xích hong khói (50g) Đức Việt gói 500g").categoryId(3L).unitId(1L).supplierId(1L).slug("duc-viet-x.xich-hong-khoi-goi-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Ba rọi xông khói Le Gourmet gói 200g").categoryId(3L).unitId(1L).supplierId(1L).slug("le-gourmet-ba-roi-xong-khoi-goi-200g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Xúc xích vườn bia Le Gourmet gói 200g").categoryId(3L).unitId(1L).supplierId(1L).slug("le-gourmet-xuc-xich-vuon-bia-goi-200g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Kim chi cải thảo cắt lát Ông Kim's hộp 500g").categoryId(3L).unitId(1L).supplierId(1L).slug("ong-kims-kim-chi-cai-thao-cat-lat-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 4
            products.add(Product.builder().name("Xúc xích Red tiệt trùng CP gói 200g").categoryId(4L).unitId(1L).supplierId(1L).slug("cp-xuc-xich-red-tiet-trung-goi-200g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Cá trứng Đôi Đũa Vàng khay 400g").categoryId(4L).unitId(1L).supplierId(1L).slug("doi-dua-vang-ca-trung-khay-400g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Há cảo Thực Phẩm Cầu Tre gói 500g").categoryId(4L).unitId(1L).supplierId(1L).slug("cau-tre-ha-cao-goi-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Há cảo tôm cua Thực Phẩm Cầu Tre gói 500g").categoryId(4L).unitId(1L).supplierId(1L).slug("cau-tre-ha-cao-hai-san-goi-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Tôm nõn size 21/25 Miền Hạ Long khay 500g").categoryId(4L).unitId(1L).supplierId(1L).slug("mien-ha-long-tom-non-size-2125-500g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 5
            products.add(Product.builder().name("Nước tương đậm đặc Maggi 300ml").categoryId(5L).unitId(1L).supplierId(1L).slug("nuoc-tuong-dam-dac-maggi-300ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước chấm hương cá hồi Chin-su chai 500ml").categoryId(5L).unitId(1L).supplierId(1L).slug("nuoc-cham-huong-ca-hoi-chin-su-chai-500ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dầu đậu nành không biến đổi gen Amur Pearl chai 1L").categoryId(5L).unitId(1L).supplierId(1L).slug("dau-dau-nanh-khong-bien-doi-gen-amur-pearl-chai-1l").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Gạo ST25 Ngọc Nương túi 5Kg").categoryId(5L).unitId(1L).supplierId(1L).slug("gao-st25-ngoc-nuong-tui-5kg").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Tương ớt Chin-su 250g").categoryId(5L).unitId(1L).supplierId(1L).slug("tuong-ot-chin-su-250g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước tương đậm đặc Maggi 300ml").categoryId(5L).unitId(1L).supplierId(1L).slug("nuoc-tuong-dam-dac-maggi-300ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 6
            products.add(Product.builder().name("Snack mực lăn muối ớt Poca gói 75g").categoryId(6L).unitId(1L).supplierId(1L).slug("snack-muc-lan-muoi-ot-poca-goi-75g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Mực tẩm gia vị Thái Bento gói 24g").categoryId(6L).unitId(1L).supplierId(1L).slug("muc-tam-gia-vi-thai-bento-goi-24g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bánh quy bơ Leibniz gói 100g").categoryId(6L).unitId(1L).supplierId(1L).slug("banh-quy-bo-leibniz-goi-100g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bánh cracker dinh dưỡng AFC vị lúa mì hộp 200g").categoryId(6L).unitId(1L).supplierId(1L).slug("banh-cracker-dinh-duong-afc-vi-lua-mi-hop-200g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bánh Chocopie Orion vị cacao 360g").categoryId(6L).unitId(1L).supplierId(1L).slug("banh-chocopie-orion-vi-cacao-360g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Kẹo sữa caramen Alpenliebe gói 120g").categoryId(6L).unitId(1L).supplierId(1L).slug("keo-sua-caramen-alpenliebe-goi-120g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bánh Tipo Hữu Nghị trà xanh hộp 90g").categoryId(6L).unitId(1L).supplierId(1L).slug("banh-tipo-huu-nghi-tra-xanh-hop-90g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 7
            products.add(Product.builder().name("Lốc 4 hộp sữa lúa mạch B'FAST canxi 180ml").categoryId(7L).unitId(1L).supplierId(1L).slug("loc-4-hop-sua-lua-mach-bfast-canxi-180ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Lốc 4 hộp sữa tươi tiệt trùng TH True Milk có đường 180ml").categoryId(7L).unitId(1L).supplierId(1L).slug("loc-4-hop-sua-tuoi-tiet-trung-th-true-milk-co-duong-180ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa tiệt trùng Cô gái Hà Lan không đường hộp 1lít").categoryId(7L).unitId(1L).supplierId(1L).slug("sua-tiet-trung-co-gai-ha-lan-khong-duong-hop-1lit").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa tươi nguyên chất Promess hộp 1L").categoryId(7L).unitId(1L).supplierId(1L).slug("sua-tuoi-nguyen-chat-promess-hop-1-lit").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa tươi thanh trùng Mộc Châu không đường chai 900g").categoryId(7L).unitId(1L).supplierId(1L).slug("moc-chau-sua-thanh-trung-k.duong-880ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa tươi thanh trùng Mộc Châu có đường chai 880ml").categoryId(7L).unitId(1L).supplierId(1L).slug("moc-chau-sua-thanh-trung-co-.duong-880ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 8
            products.add(Product.builder().name("Lốc 6 lon nước ngọt 7UP 330ml").categoryId(8L).unitId(1L).supplierId(1L).slug("loc-6-lon-nuoc-ngot-7up-330ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Lốc 6 chai nước tăng lực nhân sâm Sting 330ml").categoryId(8L).unitId(1L).supplierId(1L).slug("sting-nc-t.luc-vi-nhan-sam-chai-330ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước đào Vfresh Vinamilkhộp 1 lít").categoryId(8L).unitId(1L).supplierId(1L).slug("nuoc-dao-vfresh-vinamilkhop-1-lit").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước nho ép Vfresh Vinamilk hộp 1 lít").categoryId(8L).unitId(1L).supplierId(1L).slug("vfresh-nuoc-nho-1l").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước táo ép Vfresh hộp 1 lít").categoryId(8L).unitId(1L).supplierId(1L).slug("nuoc-tao-ep-vfresh-hop-1-lit").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước giải khát vị cam cực mạnh Mirinda chai 1.5L").categoryId(8L).unitId(1L).supplierId(1L).slug("mirinda-nuoc-ngot-chai-1.5l").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 9
            products.add(Product.builder().name("Bột giặt Joins 2 trong 1 túi 4,5kg").categoryId(9L).unitId(1L).supplierId(1L).slug("bot-giat-joins-2-trong-1-tui-45kg").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước giặt Ariel hương Downy túi 3.25kg").categoryId(9L).unitId(1L).supplierId(1L).slug("nuoc-giat-ariel-huong-downy-tui-3.25kg").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước giặt quần áo em bé D-nee Lovely Sky can 3L").categoryId(9L).unitId(1L).supplierId(1L).slug("nuoc-giat-quan-ao-em-be-d-nee-lovely-sky-can-3l").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước giặt Downy vườn hoa thơm ngát túi 2.15kg").categoryId(9L).unitId(1L).supplierId(1L).slug("nuoc-giat-downy-vuon-hoa-thom-ngat-tui-2.15kg").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Nước giặt Downy Matic Biển xanh tươi mát túi 2,15kg").categoryId(9L).unitId(1L).supplierId(1L).slug("nuoc-giat-downy-matic-bien-xanh-tuoi-mat-tui-215kg").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 10
            products.add(Product.builder().name("Sữa rửa mặt đất sét trắng Senka Perfect Whiteclay 120g").categoryId(10L).unitId(1L).supplierId(1L).slug("sua-rua-mat-dat-set-trang-senka-perfect-whiteclay-120g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bộ đôi nước súc miệng diệt khuẩn Listerine Cool Mint 750ml").categoryId(10L).unitId(1L).supplierId(1L).slug("bo-doi-nuoc-suc-mieng-diet-khuan-listerine-cool-mint-750ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dầu gội cao cấp dành cho tóc hư tổn Kerasys Salon Care Nutritive Ampoule 600g").categoryId(10L).unitId(1L).supplierId(1L).slug("kerasys-dg-saloncare-hu-ton-600g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dầu gội thảo dược Kerasys Oriental Premium Shampoo 600g").categoryId(10L).unitId(1L).supplierId(1L).slug("kerasys-dg-oriental-thao-duoc-600g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dầu gội Pantene Pro-V dưỡng tóc suôn mượt óng ả 650g").categoryId(10L).unitId(1L).supplierId(1L).slug("dau-goi-pantene-pro-v-duong-toc-suon-muot-ong-a-650g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dầu gội Pantene Pro-V dưỡng chất ngăn rụng tóc 650g").categoryId(10L).unitId(1L).supplierId(1L).slug("dau-goi-pantene-pro-v-duong-chat-ngan-rung-toc-650g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Dầu xả thảo dược Kerasys Oriental Premium 600ml").categoryId(10L).unitId(1L).supplierId(1L).slug("kerasys-dx-oriental-prem-t.duoc-do-600ml").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            //category 11
            products.add(Product.builder().name("Sữa bột Abbott Ensure Gold hương vani hộp 400g").categoryId(11L).unitId(1L).supplierId(1L).slug("sua-bot-abbott-ensure-gold-huong-vani-hop-400g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa bột Abbott Ensure Gold ít ngọt hương vani hộp 850g").categoryId(11L).unitId(1L).supplierId(1L).slug("sua-bot-abbott-ensure-gold-it-ngot-huong-vani-hop-850g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa bột Abbott Glucerna hương vani hộp 850g").categoryId(11L).unitId(1L).supplierId(1L).slug("sua-bot-abbott-glucerna-huong-vani-hop-850g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Bàn chải đánh răng trẻ em Colgate Ultra Soft siêu mềm Barbie/Spiderman").categoryId(11L).unitId(1L).supplierId(1L).slug("ban-chai-danh-rang-tre-em-colgate-ultra-soft-sieu-mem-barbiespiderman").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa bột hương vani Ensure Gold hộp 850g").categoryId(11L).unitId(1L).supplierId(1L).slug("sua-bot-huong-vani-ensure-gold-hop-850g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());
            products.add(Product.builder().name("Sữa bột hương vani PediaSure lon 850g").categoryId(11L).unitId(1L).supplierId(1L).slug("sua-bot-huong-vani-pediasure-lon-850g").importPrice(99.900F).exportPrice(209.900F).amount(999).status(1).build());

            productRepository.saveAll(products);
        }
    }
}
