package br.com.softal.gerenciadortarefas.controllers;

import br.com.softal.gerenciadortarefas.models.User;
import br.com.softal.gerenciadortarefas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/registration");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@Valid User user, BindingResult result) {
        ModelAndView mv = new ModelAndView();

        User userDatabase = userService.getByEmail(user.getEmail());
        if (userDatabase != null) {
            result.rejectValue("email", "user.existing.user.in.the.database", "Existing user in the database!");
        }

        if (result.hasErrors()) {
            mv.setViewName("user/registration");
            mv.addObject("user", new User());
        } else {
            userService.save(user);
            mv.setViewName("redirect:/login");
        }

        return mv;
    }
}
