package edu.craptocraft.medimanage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebController {

    @RequestMapping("/home")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/gestion")
    public ModelAndView gestion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gestion");
        return modelAndView;
    }

    @RequestMapping("/alta")
    public ModelAndView alta() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("alta");
        return modelAndView;
    }
    
}
