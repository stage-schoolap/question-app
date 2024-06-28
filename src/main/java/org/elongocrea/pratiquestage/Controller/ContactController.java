package org.elongocrea.pratiquestage.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
    @GetMapping("/contact")
    public String showContactPage() {
        return "contact-page"; // Assumes index.html is in templates folder
    }
}

