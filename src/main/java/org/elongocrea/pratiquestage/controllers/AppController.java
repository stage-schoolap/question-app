package org.elongocrea.pratiquestage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    @GetMapping("home")
    public String getHome(HttpServletRequest request, Model model){ // Handler Method

        log.info("info, hello word");
        model.addAttribute("myName","Rilord Fimpa Malangu");
        model.addAttribute("myApp","App-01");
        model.addAttribute("myGroup", "Eagle");

        return "index";// View name
    }
}
