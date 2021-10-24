package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccessService;

@Controller
public class RegControl {
    private AccessService accessService;

    public RegControl(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        accessService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
