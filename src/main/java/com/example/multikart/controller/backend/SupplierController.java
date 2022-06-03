package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.SupplierRequestDTO;
import com.example.multikart.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String index(Model model) {
        return supplierService.findAllSuppliers(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return supplierService.createSupplier(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("supplier") SupplierRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return supplierService.storeSupplier(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return supplierService.editSupplier(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("supplier") SupplierRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return supplierService.updateSupplier(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return supplierService.deleteSupplier(id, model, redirect);
    }
}
