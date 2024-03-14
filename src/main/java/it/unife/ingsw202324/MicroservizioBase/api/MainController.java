package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.MyTable;
import it.unife.ingsw202324.MicroservizioBase.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    MyService myService;
    @RequestMapping("/hello")
    public List<MyTable> hello() {
        /* In attesa di una connessione db
        return myService.getAll();
         */


        List<MyTable> myServiceMock =  myService.getMock();
        return myServiceMock;
    }

}
