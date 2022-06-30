package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.PaymentRequestDTO;
import com.example.multikart.domain.model.Payment;
import com.example.multikart.repo.PaymentRepository;
import com.example.multikart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String findAllPayments(Model model) {
        var payments = paymentRepository.findAllByStatusNot(DefaultStatus.DELETED);
        model.addAttribute("payments", payments);

        return "backend/payment/index";
    }

    @Override
    public String createPayment(Model model) {
        model.addAttribute("payment", Payment.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/payment/create";
    }

    @Override
    public String storePayment(PaymentRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("payment", input);

            return "backend/payment/create";
        }

        var count = paymentRepository.countByNameAndStatusNot(input.getName(), DefaultStatus.DELETED);
        if (count > 0) {
            result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("payment", input);

            return "backend/payment/create";
        }

        var newPayment = new Payment(input);
        paymentRepository.save(newPayment);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/payments";
    }

    @Override
    public String editPayment(Long id, Model model, RedirectAttributes redirect) {
        var payment = paymentRepository.findByPaymentIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(payment)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/payments";
        }

        model.addAttribute("payment", payment);

        return "backend/payment/edit";
    }

    @Override
    public String updatePayment(Long id, PaymentRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var payment = paymentRepository.findByPaymentIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(payment)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/payments";
        }

        if (result.hasErrors()) {
            model.addAttribute("payment", input);

            return "backend/payment/create";
        }

        if (!payment.getName().equals(input.getName())) {
            var count = paymentRepository.countByNameAndStatusNot(input.getName(), DefaultStatus.DELETED);
            if (count > 0) {
                result.rejectValue("name", "", "Tên đơn vị đã được sử dụng");
            }

            payment.setName(input.getName());
        }

        if (result.hasErrors()) {
            model.addAttribute("payment", input);

            return "backend/payment/create";
        }

        payment.setStatus(input.getStatus());
        paymentRepository.save(payment);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/payments";
    }

    @Override
    public String deletePayment(Long id, Model model, RedirectAttributes redirect) {
        var payment = paymentRepository.findByPaymentIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(payment)) {
            redirect.addFlashAttribute("error", "Đơn vị không tồn tại");

            return "redirect:/dashboard/payments";
        }

        payment.setStatus(DefaultStatus.DELETED);
        paymentRepository.save(payment);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/payments";
    }
}
