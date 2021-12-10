package com.group213.vet.app.controller;

import com.group213.vet.app.model.Animal;
import com.group213.vet.app.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/animals")

public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("")
    public List<Animal> getAnimal(){
        return animalService.listAllAnimal();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id){
        try {
            Animal animal = animalService.getAnimal(id);
            return new ResponseEntity<Animal>(animal, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addAnimal(@RequestBody Animal animal){
        try {
            animalService.saveAnimal(animal);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnimal(@RequestBody Animal animal, @PathVariable Integer id){
        try{
            Animal existingAnimal = animalService.getAnimal(id);
            animal.setAnimalId(id);
            animalService.saveAnimal(animal);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id){
        animalService.deleteAnimal(id);
    }
}