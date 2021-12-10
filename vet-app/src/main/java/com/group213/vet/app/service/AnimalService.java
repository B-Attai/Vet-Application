package com.group213.vet.app.service;

import com.group213.vet.app.model.Animal;
import com.group213.vet.app.respository.AnimalRepository;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> listAllAnimal(){
        return animalRepository.findAll();
    }
    public void saveAnimal(Animal animal){
        animalRepository.save(animal);
    }
    public Animal getAnimal(Integer id){
        return animalRepository.findById(id).get();
    }
    public void deleteAnimal(Integer id){
        animalRepository.deleteById(id);
    }

    JSONObject obj = new JSONObject();

    public JSONObject getAnimalBreedSpecies() {
        HashMap<String, String> test0 = new HashMap<>();
        try {
            List<String> test = animalRepository.findAnimalBreedSpecies();
            ArrayList<String> test2 = new ArrayList<String>();
            test2.add("animalId");
            test2.add("distinguishingFeatures");
            test2.add("color");
            test2.add("alerts");

            HashMap<String, String> test3 = new HashMap<>();
            for (int i = 0; i < test.size(); i++) {
                for(int j = 0; j< test2.size(); j++){
                    System.out.println(test.get(i) + test2.get(j));
                    obj.put(test.get(i), test2.get(j));
                }

            }
            return obj;

        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}