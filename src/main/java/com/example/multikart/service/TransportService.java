package com.example.multikart.service;

import com.example.multikart.domain.dto.TransportRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface TransportService {
    String findAllTransports(Model model);

    String createTransport(Model model);

    String storeTransport(TransportRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String editTransport(Long id, Model model, RedirectAttributes redirect);

    String updateTransport(Long id, TransportRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect);

    String deleteTransport(Long id, Model model, RedirectAttributes redirect);
}
