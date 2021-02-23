package sbox.learn.unit1.mvc.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import sbox.learn.unit1.mvc.app.entities.Account;
import sbox.learn.unit1.mvc.app.services.LoginService;
import sbox.learn.unit1.mvc.common.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Контроллер работы с пользователями (вход/регистация)
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        logger.info("GET /login returns login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/auth")
    public String authenticate(@Valid LoginForm loginFrom, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login_page";
        } else if (loginService.authenticate(loginFrom.getUsername(),loginFrom.getPassword())) {
            logger.info("login OK redirect to book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("login FAIL redirect back to login");
            bindingResult.addError(new ObjectError("global", "Authentication error"));
            return "login_page";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        logger.info("GET /register returns register.html");
        model.addAttribute("accountForm", new Account());
        return "register_page";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("accountForm") Account accountForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register_page";
        } else if (loginService.register(accountForm)) {
            logger.info("register OK redirect to login");
            return "redirect:/login";
        } else {
            logger.info("register FAIL redirect back to register");
            bindingResult.addError(new ObjectError("global", "Cannot create account"));
            return "register_page";
        }
    }
}
