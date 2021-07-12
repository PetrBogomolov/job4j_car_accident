package ru.job4j.accident.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.entity.User;
import ru.job4j.accident.service.RegAndAuthService;

@Controller
public class RegControl {
    private final RegAndAuthService service;

    public RegControl(RegAndAuthService service) {
        this.service = service;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String informAboutUserAlreadyReg() {
        return "redirect:/reg?regError=true";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(value = "regError", required = false) String regError,
                      Model model) {
        String errorMessage = null;
        if (regError != null) {
            errorMessage = "Такой пользователь уже зарегистрирован";
        }
        model.addAttribute("error", errorMessage);
        return "reg";
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        service.saveUser(user);
        return "login";
    }
}
