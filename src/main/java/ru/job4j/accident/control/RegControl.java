package ru.job4j.accident.control;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccessService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RegControl {
    private AccessService accessService;

    public RegControl(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model, HttpServletResponse response) {
        try {
            accessService.saveUser(user);
        } catch (DataIntegrityViolationException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            model.addAttribute("errorMessage", "Пользователь существует.");
            return "reg";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
