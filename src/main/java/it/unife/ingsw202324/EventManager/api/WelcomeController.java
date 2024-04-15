package it.unife.ingsw202324.EventManager.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
