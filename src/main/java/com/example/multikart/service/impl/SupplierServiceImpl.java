package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.SupplierRequestDTO;
import com.example.multikart.domain.model.Supplier;
import com.example.multikart.repo.SupplierRepository;
import com.example.multikart.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public String findAllSuppliers(Model model) {
        var suppliers = supplierRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("suppliers", suppliers);

        return "backend/supplier/index";
    }

    @Override
    public String createSupplier(Model model) {
        model.addAttribute("supplier", Supplier.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/supplier/create";
    }

    @Override
    public String storeSupplier(SupplierRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("supplier", input);

            return "backend/supplier/create";
        }

        var count = supplierRepository.countByNameAndStatus(input.getName(), DefaultStatus.ACTIVE);
        if (count > 0) {
            result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("supplier", input);

            return "backend/supplier/create";
        }

        var newSupplier = new Supplier(input);
        supplierRepository.save(newSupplier);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/suppliers";
    }

    @Override
    public String editSupplier(Long id, Model model, RedirectAttributes redirect) {
        var supplier = supplierRepository.findBySupplierIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(supplier)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/suppliers";
        }

        model.addAttribute("supplier", supplier);

        return "backend/supplier/edit";
    }

    @Override
    public String updateSupplier(Long id, SupplierRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var supplier = supplierRepository.findBySupplierIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(supplier)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/suppliers";
        }

        if (result.hasErrors()) {
            model.addAttribute("supplier", input);

            return "backend/supplier/create";
        }

        if (!supplier.getName().equals(input.getName())) {
            var count = supplierRepository.countByNameAndStatus(input.getName(), DefaultStatus.ACTIVE);
            if (count > 0) {
                result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
            }

            supplier.setName(input.getName());
        }

        if (result.hasErrors()) {
            model.addAttribute("supplier", input);

            return "backend/supplier/create";
        }

        supplier.setStatus(input.getStatus());
        supplierRepository.save(supplier);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/suppliers";
    }

    @Override
    public String deleteSupplier(Long id, Model model, RedirectAttributes redirect) {
        var supplier = supplierRepository.findBySupplierIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(supplier)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/suppliers";
        }

        supplier.setStatus(DefaultStatus.DELETED);
        supplierRepository.save(supplier);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/suppliers";
    }
}
