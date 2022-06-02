package com.example.multikart.service;

import com.example.multikart.domain.dto.CreateUnitRequestDTO;
import com.example.multikart.domain.dto.UpdateUnitRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UnitService {
    String findAllUnits(Model model);

    String createUnit(Model model);

    String storeUnit(CreateUnitRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editUnit(Long id, Model model, RedirectAttributes redirect);

    String updateUnit(Long id, UpdateUnitRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteUnit(Long id, Model model, RedirectAttributes redirect);
}
