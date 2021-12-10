package com.group213.vet.app.controller;

import com.group213.vet.app.model.Animal;
import com.group213.vet.app.service.AnimalService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/animals")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHING_TECHNICIAN') or hasRole('STUDENT')" +
            " or hasRole('ADMIN') or hasRole('ANIMAL_HEALTH_TECHNICIAN') or hasRole('ANIMAL_CARE_ATTENDANT')")
    public List<Animal> getAnimal(){
        return animalService.listAllAnimal();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHING_TECHNICIAN') or hasRole('STUDENT')" +
            " or hasRole('ADMIN') or hasRole('ANIMAL_HEALTH_TECHNICIAN') or hasRole('ANIMAL_CARE_ATTENDANT')")
    public JSONArray getAnimalProfiles(){
        return animalService.getAnimalProfiles();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHING_TECHNICIAN') or hasRole('STUDENT')" +
            " or hasRole('ADMIN') or hasRole('ANIMAL_HEALTH_TECHNICIAN') or hasRole('ANIMAL_CARE_ATTENDANT')")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id){
        try {
            Animal animal = animalService.getAnimal(id);
            return new ResponseEntity<Animal>(animal, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAnimal(@RequestBody Animal animal){
        try {
            animalService.saveAnimal(animal);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ANIMAL_CARE_ATTENDANT') or hasRole('TEACHING_TECHNICIAN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAnimal(@PathVariable Integer id){
        animalService.deleteAnimal(id);
    }
}