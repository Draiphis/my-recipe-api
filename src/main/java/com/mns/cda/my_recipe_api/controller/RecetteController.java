package com.mns.cda.my_recipe_api.controller;



import com.fasterxml.jackson.annotation.JsonView;
import com.mns.cda.my_recipe_api.dao.RecetteDao;
import com.mns.cda.my_recipe_api.model.Recette;
import com.mns.cda.my_recipe_api.view.RecetteView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RecetteController {


    protected RecetteDao recetteDao;

    @Autowired
    public RecetteController(RecetteDao recetteDao) {
        this.recetteDao = recetteDao;
    }

    @GetMapping("/recette/list")
    @JsonView(RecetteView.class)
    public List<Recette> getAll(){

        return recetteDao.findAll();



    }
    @GetMapping("/recette/{id}")
    @JsonView(RecetteView.class)
    public ResponseEntity<Recette> get(@PathVariable int id){

        Optional<Recette> optionalRecette = recetteDao.findById(id);

        if (optionalRecette.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalRecette.get(),HttpStatus.OK);
    }

    @DeleteMapping("/recette/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){

        Optional<Recette> optionalRecette = recetteDao.findById(id);

        if (optionalRecette.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recetteDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/recette")
    @JsonView(RecetteView.class)
    public ResponseEntity<Recette> create(@RequestBody @Valid Recette recetteToInsert) {

        recetteToInsert.setId(null);
        recetteDao.save(recetteToInsert);

        return new ResponseEntity<>(recetteToInsert, HttpStatus.CREATED);

    }
    @PutMapping("/recette/{id}")
    public ResponseEntity<Void> update(@PathVariable int id,
                                       @RequestBody
                                       @Valid Recette recetteToUpdate){


        Optional<Recette> optionalRecette = recetteDao.findById(id);

        if (optionalRecette.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recetteToUpdate.setId(id);


        recetteDao.save(recetteToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
