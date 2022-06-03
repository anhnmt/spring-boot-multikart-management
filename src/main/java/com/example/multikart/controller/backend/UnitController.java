package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.UnitRequestDTO;
import com.example.multikart.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/units")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @GetMapping
    public String index(Model model) {
        return unitService.findAllUnits(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return unitService.createUnit(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("unit") UnitRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return unitService.storeUnit(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return unitService.editUnit(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("unit") UnitRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return unitService.updateUnit(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return unitService.deleteUnit(id, model, redirect);
    }
}
