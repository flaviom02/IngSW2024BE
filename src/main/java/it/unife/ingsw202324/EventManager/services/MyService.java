package it.unife.ingsw202324.EventManager.services;

import it.unife.ingsw202324.EventManager.models.Categories;
import it.unife.ingsw202324.EventManager.models.MyTable;
import it.unife.ingsw202324.EventManager.repositories.CategoriesRepository;
import it.unife.ingsw202324.EventManager.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/* Service class per interrogare il db  */
@Service
public class MyService {
    @Autowired
    private MyRepository myRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;


    /* Metodo che effettua una select all sulla tabella Mysql */
    public List<MyTable> getAll() {
        return myRepository.findAll();
    }

    /* Metodo che effettua una select all sulla tabella Mysql */
    public List<Categories> getAllCat() {
        return categoriesRepository.findAll();
    }


    /* Metodo che salva un record sulla tabella  */
    public void insert(MyTable record){
        myRepository.save(record);
    }

    public void insert(Categories record){
        categoriesRepository.save(record);
    }

    /* Metodo che inserisce dati e li recupera da un db H2 (in assenza di mysql) */
    public List<Categories> addCategory() {
        this.insert(new Categories(1L,"CIAO",false,null));
        return this.getAllCat();
    }

    public List<MyTable> addElements() {
        this.insert(new MyTable(1L, "Test 1"));
        this.insert(new MyTable(2L, "Test 2"));
        this.insert(new MyTable(3L, "Test 3"));
        return this.getAll();
    }


}
