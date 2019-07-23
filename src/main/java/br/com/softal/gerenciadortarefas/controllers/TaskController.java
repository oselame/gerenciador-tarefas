package br.com.softal.gerenciadortarefas.controllers;

import br.com.softal.gerenciadortarefas.models.Task;
import br.com.softal.gerenciadortarefas.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tasks/list");
        mv.addObject("tasks", taskRepository.findAll());
        return mv;
    }

    @GetMapping("/insert")
    public ModelAndView insert() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tasks/insert");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/insert")
    public ModelAndView insert(@Valid Task task, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (task.getExpiration() == null) {
            result.rejectValue("expiration", "task.expiration.required", "The expiration date is required!");
        } else if (task.getExpiration().before(new Date())) {
            result.rejectValue("expiration", "task.expiration.invalid", "The expiration date cannot be earlier than current date!");
        }

        if (result.hasErrors()) {
            mv.setViewName("tasks/insert");
            mv.addObject(task);
        } else {
            taskRepository.save(task);
            mv.setViewName("redirect:/tasks/list");
        }
        return mv;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tasks/update");
        mv.addObject("task", taskRepository.getOne(id));
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Task task, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (task.getExpiration() == null) {
            result.rejectValue("expiration", "task.expiration.required", "The expiration date is required!");
        } else if (task.getExpiration().before(new Date())) {
            result.rejectValue("expiration", "task.expiration.invalid", "The expiration date cannot be earlier than current date!");
        }

        if (result.hasErrors()) {
            mv.setViewName("tasks/update");
            mv.addObject(task);
        } else {
            taskRepository.save(task);
            mv.setViewName("redirect:/tasks/list");
        }
        return mv;
    }
}
