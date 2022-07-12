package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.TransportRequestDTO;
import com.example.multikart.domain.model.Transport;
import com.example.multikart.repo.TransportRepository;
import com.example.multikart.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;

    @Override
    public String findAllTransports(Model model) {
        var transports = transportRepository.findAllByStatusNot(DefaultStatus.DELETED);
        model.addAttribute("transports", transports);

        return "backend/transport/index";
    }

    @Override
    public String createTransport(Model model) {
        model.addAttribute("transport", Transport.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/transport/create";
    }

    @Override
    public String storeTransport(TransportRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("transport", input);

            return "backend/transport/create";
        }

        var count = transportRepository.countByNameAndStatusNot(input.getName().trim(), DefaultStatus.DELETED);
        if (count > 0) {
            result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("transport", input);

            return "backend/transport/create";
        }

        var newTransport = new Transport(input);
        transportRepository.save(newTransport);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/transports";
    }

    @Override
    public String editTransport(Long id, Model model, RedirectAttributes redirect) {
        var transport = transportRepository.findByTransportIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(transport)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/transports";
        }

        model.addAttribute("transport", transport);

        return "backend/transport/edit";
    }

    @Override
    public String updateTransport(Long id, TransportRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var transport = transportRepository.findByTransportIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(transport)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/transports";
        }

        if (result.hasErrors()) {
            input.setTransportId(id);
            model.addAttribute("transport", input);

            return "backend/transport/edit";
        }

        if (!transport.getName().equals(input.getName())) {
            var count = transportRepository.countByNameAndStatusNot(input.getName().trim(), DefaultStatus.DELETED);
            if (count > 0) {
                result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
            }

            transport.setName(input.getName());
        }

        if (result.hasErrors()) {
            input.setTransportId(id);
            model.addAttribute("transport", input);

            return "backend/transport/edit";
        }

        transport.setStatus(input.getStatus());
        transportRepository.save(transport);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/transports";
    }

    @Override
    public String deleteTransport(Long id, Model model, RedirectAttributes redirect) {
        var transport = transportRepository.findByTransportIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(transport)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/transports";
        }

        transport.setStatus(DefaultStatus.DELETED);
        transportRepository.save(transport);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/transports";
    }
}
