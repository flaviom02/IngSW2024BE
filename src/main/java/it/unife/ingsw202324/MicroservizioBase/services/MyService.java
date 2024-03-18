package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.MyTable;
import it.unife.ingsw202324.MicroservizioBase.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/* Service class per interrogare il db  */
@Service
public class MyService {
    @Autowired
    private MyRepository myRepository;

    /* Metodo che effettua una select all sulla tabella Mysql */
    public List<MyTable> getAll() {
        return myRepository.findAll();
    }


    /* Metodo che salva un record sulla tabella  */
    public void insert(MyTable record){
        myRepository.save(record);
    }

    /* Metodo che inserisce dati e li recupera da un db H2 (in assenza di mysql) */
    public List<MyTable> addElements() {
        this.insert(new MyTable(1L, "Test 1"));
        this.insert(new MyTable(2L, "Test 2"));
        this.insert(new MyTable(3L, "Test 3"));
        return this.getAll();
    }


}
