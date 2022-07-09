package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.*;
import com.example.multikart.domain.model.Category;
import com.example.multikart.domain.model.Product;
import com.example.multikart.domain.model.Supplier;
import com.example.multikart.domain.model.Unit;
import com.example.multikart.repo.*;
import com.example.multikart.service.ProductService;
import com.example.multikart.service.RedisCache;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private RedisCache redisCache;


    @Override
    public String findAllProducts(Model model) {
        List<ItemProductDTO> productsCache = redisCache.getObject(ScreenRedis.PRODUCT.name(), "FIND_ALL", new TypeReference<>() {
        });
        if (productsCache == null) {
            var products = productRepository.findAllByStatusNot(DefaultStatus.DELETED);
            model.addAttribute("products", products);
            redisCache.putObject(ScreenRedis.PRODUCT.name(), "FIND_ALL", products);
        } else {
            model.addAttribute("products", productsCache);
        }

        return "backend/product/index";
    }

    @Override
    public String createProduct(Model model) {
        model.addAttribute("product", Product.builder().status(DefaultStatus.ACTIVE).build());
        model.addAttribute("categories", categoryRepository.findAllByStatus(DefaultStatus.ACTIVE));
        model.addAttribute("units", unitRepository.findAllByStatus(DefaultStatus.ACTIVE));
        model.addAttribute("suppliers", supplierRepository.findAllByStatus(DefaultStatus.ACTIVE));

        return "backend/product/create";
    }

    @Override
    public String storeProduct(ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("product", input);
            model.addAttribute("categories", categoryRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("units", unitRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("suppliers", supplierRepository.findAllByStatus(DefaultStatus.ACTIVE));
            return "backend/product/create";
        }

        var count = productRepository.countByNameAndStatusNot(input.getName(), DefaultStatus.DELETED);
        if (count > 0) {
            result.rejectValue("name", "", "Tên sản phẩm đã được sử dụng");
        }

        var countSlug = productRepository.countBySlugAndStatusNot(input.getSlug(), DefaultStatus.DELETED);
        if (countSlug > 0) {
            result.rejectValue("slug", "", "Đường dẫn đã được sử dụng");
        }

        var checkCategory = categoryRepository.existsById(input.getCategoryId());
        if (!checkCategory) {
            result.rejectValue("categoryId", "", "Danh mục không tồn tại");
        }

        var checkUnit = unitRepository.existsById(input.getUnitId());
        if (!checkUnit) {
            result.rejectValue("unitId", "", "Đơn vị không tồn tại");
        }
        var checkSupplier = supplierRepository.existsById(input.getSupplierId());
        if (!checkSupplier) {
            result.rejectValue("supplierId", "", "Nhà cung cấp không tồn tại");
        }

        if (result.hasErrors()) {
            model.addAttribute("product", input);
            model.addAttribute("categories", categoryRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("units", unitRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("suppliers", supplierRepository.findAllByStatus(DefaultStatus.ACTIVE));
            return "backend/product/create";
        }

        var newProduct = new Product(input);
        productRepository.save(newProduct);

        redisCache.delete(ScreenRedis.HOME.name());
        redisCache.delete(ScreenRedis.PRODUCT.name());
        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/products";
    }

    @Override
    public String editProduct(Long id, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatusNot(id, DefaultStatus.DELETED);

        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAllByStatus(DefaultStatus.ACTIVE));
        model.addAttribute("units", unitRepository.findAllByStatus(DefaultStatus.ACTIVE));
        model.addAttribute("suppliers", supplierRepository.findAllByStatus(DefaultStatus.ACTIVE));

        return "backend/product/edit";
    }

    @Override
    public String updateProduct(Long id, ProductRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        if (result.hasErrors()) {
            model.addAttribute("product", input);
            model.addAttribute("categories", categoryRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("units", unitRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("suppliers", supplierRepository.findAllByStatus(DefaultStatus.ACTIVE));
            return "backend/product/edit";
        }

        if (!product.getName().equals(input.getName())) {
            var count = productRepository.countByNameAndStatusNot(input.getName(), DefaultStatus.DELETED);
            if (count > 0) {
                result.rejectValue("name", "", "Tên sản phẩm đã được sử dụng");
            } else {
                product.setName(input.getName());
            }
        }
        if (!product.getSlug().equals(input.getSlug())) {
            var countSlug = productRepository.countBySlugAndStatusNot(input.getSlug(), DefaultStatus.DELETED);
            if (countSlug > 0) {
                result.rejectValue("slug", "", "Đường dẫn đã được sử dụng");
            } else {
                product.setSlug(input.getSlug());
            }
        }

        var checkCategory = categoryRepository.existsById(input.getCategoryId());
        if (!checkCategory) {
            result.rejectValue("categoryId", "", "Danh mục không tồn tại");
        }

        var checkUnit = unitRepository.existsById(input.getUnitId());
        if (!checkUnit) {
            result.rejectValue("unitId", "", "Đơn vị không tồn tại");
        }
        var checkSupplier = supplierRepository.existsById(input.getSupplierId());
        if (!checkSupplier) {
            result.rejectValue("supplierId", "", "Nhà cung cấp không tồn tại");
        }

        if (result.hasErrors()) {
            model.addAttribute("product", input);
            model.addAttribute("categories", categoryRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("units", unitRepository.findAllByStatus(DefaultStatus.ACTIVE));
            model.addAttribute("suppliers", supplierRepository.findAllByStatus(DefaultStatus.ACTIVE));
            return "backend/product/edit";
        }

        product.setDescription(input.getDescription());
        product.setAmount(input.getAmount());
        product.setImportPrice(input.getImportPrice());
        product.setExportPrice(input.getExportPrice());
        product.setCategoryId(input.getCategoryId());
        product.setUnitId(input.getUnitId());
        product.setSupplierId(input.getSupplierId());
        product.setStatus(input.getStatus());
        productRepository.save(product);
        //delete cache redis
        redisCache.delete(ScreenRedis.HOME.name());
        redisCache.delete(ScreenRedis.PRODUCT.name());
        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/products";
    }

    @Override
    public String deleteProduct(Long id, Model model, RedirectAttributes redirect) {
        var product = productRepository.findByProductIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(product)) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại");

            return "redirect:/dashboard/products";
        }

        product.setStatus(DefaultStatus.DELETED);
        productRepository.save(product);

        redisCache.delete(ScreenRedis.HOME.name());
        redisCache.delete(ScreenRedis.PRODUCT.name());
        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/products";
    }

    @Override
    public String frontendProduct(String slug, Model model, RedirectAttributes redirect) {
        var product = productRepository.findItemProductBySlugAndStatus(slug, DefaultStatus.ACTIVE);
        model.addAttribute("product", product);

        var images = productImageRepository.findAllByProductIdAndStatusOrderByPositionAsc(product.getProductId(), DefaultStatus.ACTIVE);
        model.addAttribute("images", images);

        var pageRequest = PageRequest.of(0, 12);
        var relatedProducts = productRepository.findRelatedByProductIdAndStatus(product.getProductId(), DefaultStatus.ACTIVE, pageRequest);
        model.addAttribute("relatedProducts", relatedProducts);

        var cart = AddToCartRequestDTO.builder().productId(product.getProductId()).quantity(1).build();
        model.addAttribute("cart", cart);

        return "frontend/product";
    }

    @Override
    @Transactional
    public String multiDeleteProduct(List<Long> delete, Model model, RedirectAttributes redirect) {
        log.info("multiDeleteProduct: {}", delete);

        try {
            productRepository.deleteAllByProductIdInAndStatusNot(delete, DefaultStatus.DELETED);
            productImageRepository.deleteAllByProductIdInAndStatusNot(delete, DefaultStatus.DELETED);
            redisCache.delete(ScreenRedis.PRODUCT.name());
            redisCache.delete(ScreenRedis.HOME.name());

            redirect.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Xóa không thành công");
        }

        return "redirect:/dashboard/products";
    }

    @Override
    @Transactional
    public String upload(MultipartFile file, Model model, RedirectAttributes redirect) {

        if (DataUtils.isNullOrEmpty(file)) {
            redirect.addFlashAttribute("error", "Vui lòng chọn file");

            return "redirect:/dashboard/products/create";
        }

        try {
            var listProducts = readSheetToProducts(file.getInputStream());

            log.info("listProducts: {}", listProducts);

            var categories = categoryRepository.findAllByStatusNot(DefaultStatus.DELETED);
            var units = unitRepository.findAllByStatusNot(DefaultStatus.DELETED);
            var suppliers = supplierRepository.findAllByStatusNot(DefaultStatus.DELETED);

            List<Product> products = new ArrayList<>();
            listProducts.parallelStream().forEach(x -> {

                // check empty name
                if (DataUtils.isNullOrEmpty(x.getName())
                        || DataUtils.isNullOrEmpty(x.getCategoryName())
                        || DataUtils.isNullOrEmpty(x.getUnitName())
                        || DataUtils.isNullOrEmpty(x.getSupplierName())
                ) {
                    return;
                }

                var product = new Product();
                product.setName(x.getName());

                // check slug not empty
                var slug = "";
                if (DataUtils.isNullOrEmpty(x.getSlug())) {
                    slug = Utils.toSlug(x.getName());
                } else {
                    slug = x.getSlug();
                }
                product.setSlug(slug);

                // check if exist
                var count = productRepository.countByNameOrSlugAndStatusNot(x.getName(), slug, DefaultStatus.DELETED);
                if (count > 0) {
                    return;
                }

                product.setAmount(x.getAmount());
                product.setExportPrice(x.getExportPrice());
                product.setImportPrice(x.getImportPrice());
                product.setStatus(x.getStatus());

                // find or create category
                var category = findOrCreateCategory(categories, x.getCategoryName());
                product.setCategoryId(category.getCategoryId());

                // find or create unit
                var unit = findOrCreateUnit(units, x.getUnitName());
                product.setUnitId(unit.getUnitId());

                // find or create supplier
                var supplier = findOrCreateSupplier(suppliers, x.getSupplierName());
                product.setSupplierId(supplier.getSupplierId());

                products.add(product);
            });

            productRepository.saveAll(products);

            //delete cache redis
            redisCache.delete(ScreenRedis.HOME.name());
            redisCache.delete(ScreenRedis.PRODUCT.name());

            redirect.addFlashAttribute("success", "Thêm thành công");
            return "redirect:/dashboard/products";
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Có lỗi xảy ra!");

            return "redirect:/dashboard/products/create";
        }
    }

    private List<ProductExcelDTO> readSheetToProducts(InputStream inputStream) throws IOException {
        List<ProductExcelDTO> products = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cellsInRow = currentRow.iterator();

            ProductExcelDTO product = new ProductExcelDTO();

            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();

                switch (cellIdx) {

                    case 0:
                        product.setName(currentCell.getStringCellValue());
                        break;
                    case 1:
                        product.setSlug(currentCell.getStringCellValue());
                        break;
                    case 2:
                        product.setAmount((int) currentCell.getNumericCellValue());
                        break;
                    case 3:
                        product.setCategoryName(currentCell.getStringCellValue());
                        break;
                    case 4:
                        product.setExportPrice((float) currentCell.getNumericCellValue());
                        break;
                    case 5:
                        product.setImportPrice((float) currentCell.getNumericCellValue());
                        break;
                    case 6:
                        var status = currentCell.getStringCellValue();
                        if (status.equalsIgnoreCase("Active")) {
                            product.setStatus(DefaultStatus.ACTIVE);
                        } else {
                            product.setStatus(DefaultStatus.DISABLED);
                        }
                        break;
                    case 7:
                        product.setSupplierName(currentCell.getStringCellValue());
                        break;
                    case 8:
                        product.setUnitName(currentCell.getStringCellValue());
                        break;
                    case 9:
                        product.setDescription(currentCell.getRichStringCellValue().getString());
                        break;
                    default:
                        break;
                }

                cellIdx++;
            }

            products.add(product);
        }

        workbook.close();

        return products;
    }

    private Category findOrCreateCategory(List<Category> categories, String categoryName) {
        var category = categories.parallelStream()
                .filter(s -> s.getName().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElse(null);

        if (DataUtils.isNullOrEmpty(category)) {
            category = categoryRepository.save(Category.builder()
                    .name(categoryName)
                    .slug(Utils.toSlug(categoryName))
                    .status(DefaultStatus.ACTIVE)
                    .build());
        }

        return category;
    }

    private Unit findOrCreateUnit(List<Unit> units, String unitName) {
        var unit = units.parallelStream()
                .filter(s -> s.getName().equalsIgnoreCase(unitName))
                .findFirst()
                .orElse(null);

        if (DataUtils.isNullOrEmpty(unit)) {
            unit = unitRepository.save(Unit.builder()
                    .name(unitName)
                    .status(DefaultStatus.ACTIVE)
                    .build());
        }

        return unit;
    }

    private Supplier findOrCreateSupplier(List<Supplier> suppliers, String supplierName) {
        var supplier = suppliers.parallelStream()
                .filter(s -> s.getName().equalsIgnoreCase(supplierName))
                .findFirst()
                .orElse(null);

        if (DataUtils.isNullOrEmpty(supplier)) {
            supplier = supplierRepository.save(Supplier.builder()
                    .name(supplierName)
                    .status(DefaultStatus.ACTIVE)
                    .build());
        }

        return supplier;
    }
}
