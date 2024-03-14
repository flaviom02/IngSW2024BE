package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.MyTable;
import it.unife.ingsw202324.MicroservizioBase.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {
    @Autowired
    private MyRepository myRepository;

    public List<MyTable> getAll() {
        return myRepository.findAll();
    }

    public void insert(MyTable record){
        myRepository.save(record);
    }

    public List<MyTable> getMock() {
        this.insert(new MyTable(1L, "Test 1"));
        this.insert(new MyTable(2L, "Test 2"));
        return this.getAll();
    }


}
