package com.group213.vet.app.controller;

import com.group213.vet.app.model.TreatmentMethod;
import com.group213.vet.app.service.TreatmentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/animals/treatments")
public class TreatmentMethodController {

    @Autowired
    TreatmentMethodService treatmentMethodService;

    @GetMapping("")
    public List<TreatmentMethod> treatmentMethodList(){
        return treatmentMethodService.listAllTreatmentMethods();
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<TreatmentMethod> getTreatmentMethodById(@PathVariable Integer treatmentId){
        try{
            TreatmentMethod treatmentMethod = treatmentMethodService.getTreatmentMethod(treatmentId);
            return new ResponseEntity<TreatmentMethod>(treatmentMethod, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addTreatmentMethod(@RequestBody TreatmentMethod treatmentMethod){
        try{
            treatmentMethodService.saveTreatmentMethod(treatmentMethod);
            return new ResponseEntity<>(treatmentMethod, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{treatmentId}")
    public ResponseEntity<?> updateTreatmentMethod(@RequestBody TreatmentMethod treatmentMethod, @PathVariable Integer treatmentId){
        try{
            TreatmentMethod existingTreatmentMethod = treatmentMethodService.getTreatmentMethod(treatmentId);
            existingTreatmentMethod.setTreatmentId(treatmentId);
            treatmentMethodService.saveTreatmentMethod(treatmentMethod);
            return new ResponseEntity<>(treatmentMethod, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{treatmentId}")
    public void deleteTreatmentMethod(@PathVariable Integer treatmentId){
        treatmentMethodService.deleteTreatmentMethod(treatmentId);
    }
}
