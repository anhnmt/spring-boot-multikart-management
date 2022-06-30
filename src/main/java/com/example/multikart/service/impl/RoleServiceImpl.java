package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.RoleRequestDTO;
import com.example.multikart.domain.model.Role;
import com.example.multikart.repo.RoleRepository;
import com.example.multikart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String findAllRoles(Model model) {
        var roles = roleRepository.findAllByStatusNot(DefaultStatus.DELETED);
        model.addAttribute("roles", roles);

        return "backend/role/index";
    }

    @Override
    public String createRole(Model model) {
        model.addAttribute("role", Role.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/role/create";
    }

    @Override
    public String storeRole(RoleRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("role", input);

            return "backend/role/create";
        }

        var count = roleRepository.countByNameAndStatusNot(input.getName(), DefaultStatus.DELETED);
        if (count > 0) {
            result.rejectValue("name", "", "Tên quyền đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("role", input);

            return "backend/role/create";
        }

        var newRole = new Role(input);
        roleRepository.save(newRole);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/roles";
    }

    @Override
    public String editRole(Long id, Model model, RedirectAttributes redirect) {
        var role = roleRepository.findByRoleIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(role)) {
            redirect.addFlashAttribute("error", "Quyền không tồn tại");

            return "redirect:/dashboard/roles";
        }

        model.addAttribute("role", role);

        return "backend/role/edit";
    }

    @Override
    public String updateRole(Long id, RoleRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var role = roleRepository.findByRoleIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(role)) {
            redirect.addFlashAttribute("error", "Quyền không tồn tại");

            return "redirect:/dashboard/roles";
        }

        if (result.hasErrors()) {
            model.addAttribute("role", input);

            return "backend/role/create";
        }

        if (!role.getName().equalsIgnoreCase(input.getName())) {
            var count = roleRepository.countByNameAndStatusNot(input.getName(), DefaultStatus.DELETED);
            if (count > 0) {
                result.rejectValue("name", "", "Tên quyền đã được sử dụng");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("role", input);

            return "backend/role/create";
        }

        role.setName(input.getName());
        role.setStatus(input.getStatus());
        roleRepository.save(role);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/roles";
    }

    @Override
    public String deleteRole(Long id, Model model, RedirectAttributes redirect) {
        var role = roleRepository.findByRoleIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(role)) {
            redirect.addFlashAttribute("error", "Quyền không tồn tại");

            return "redirect:/dashboard/roles";
        }

        role.setStatus(DefaultStatus.DELETED);
        roleRepository.save(role);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/roles";
    }
}
