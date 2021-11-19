package com.group213.vet.app.controller;

import com.group213.vet.app.model.PrescriptionRecords;
import com.group213.vet.app.service.PrescriptionRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/animals/prescriptions")
public class PrescriptionRecordsController {

    @Autowired
    PrescriptionRecordsService prescriptionRecordsService;

    @GetMapping("")
    public List<PrescriptionRecords> getPrescriptionRecords(){
        return prescriptionRecordsService.listAllPrescriptionRecords();
    }

    @GetMapping("/{scriptRecord}")
    public ResponseEntity<PrescriptionRecords> getPrescrptionRecordBySR(@PathVariable Integer scriptRecord){
        try {
            PrescriptionRecords prescriptionRecords = prescriptionRecordsService.getPrescriptionRecordBySR(scriptRecord);
            return new ResponseEntity<>(prescriptionRecords, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{scriptRecord}")
    public ResponseEntity<?> updatePrescriptionRecord(@RequestBody PrescriptionRecords prescriptionRecord, @PathVariable Integer scriptRecord) {
        try {
            PrescriptionRecords prescriptionRecords = prescriptionRecordsService.getPrescriptionRecordBySR(scriptRecord);
            prescriptionRecords.setScriptRecord(scriptRecord);
            prescriptionRecordsService.savePrescriptionRecord(prescriptionRecord);
            return new ResponseEntity<>(prescriptionRecords, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addPrescriptionRecord(@RequestBody PrescriptionRecords prescriptionRecord){
        try {
            prescriptionRecordsService.savePrescriptionRecord(prescriptionRecord);
            return new ResponseEntity<>(prescriptionRecord, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{scriptRecord}")
    public ResponseEntity<?> removePrescriptionRecord(@PathVariable Integer scriptRecord){
        try{
            prescriptionRecordsService.deletePrescriptionRecord(scriptRecord);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

