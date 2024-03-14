package it.unife.ingsw202324.MicroservizioBase.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
