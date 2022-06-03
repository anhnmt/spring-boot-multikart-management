package com.example.multikart.controller.backend;

import com.example.multikart.domain.dto.TransportRequestDTO;
import com.example.multikart.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/transports")
public class TransportController {
    @Autowired
    private TransportService transportService;

    @GetMapping
    public String index(Model model) {
        return transportService.findAllTransports(model);
    }

    @GetMapping("/create")
    public String create(Model model) {
        return transportService.createTransport(model);
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("transport") TransportRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return transportService.storeTransport(input, result, model, redirect);
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return transportService.editTransport(id, model, redirect);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("transport") TransportRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        return transportService.updateTransport(id, input, result, model, redirect);
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        return transportService.deleteTransport(id, model, redirect);
    }
}
