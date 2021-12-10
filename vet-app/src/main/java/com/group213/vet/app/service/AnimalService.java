package com.group213.vet.app.service;

import com.group213.vet.app.model.Animal;
import com.group213.vet.app.respository.AnimalRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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



    public JSONArray getAnimalProfiles() {

        List<String> animalAttributes = new ArrayList<>(
                Arrays.asList("animalId", "species", "weight", "tattooNum", "cityTattoo", "birthDate",
                        "breed", "sex", "rfid", "microchip", "diet", "region", "subspecies",
                        "distinguishingFeatures", "color", "alerts")
        );

        try {
            List<String> allAnimalAttributeValues = animalRepository.findAnimalBreedSpecies();
            JSONArray jsonArray = new JSONArray();
            JSONObject obj = new JSONObject();

            int counter = 0;
                    for (int i = 0; i < allAnimalAttributeValues.size(); i++){
                    List<String> stringList = Arrays.asList(allAnimalAttributeValues.get(i).split(","));

                    for (Object o : stringList) {
                        obj.put(animalAttributes.get(counter), o);
                        counter++;
                        System.out.println(counter);
                    }

//                    if (counter == animalAttributes.size() - 1) {
//                        counter = 0;
//                        jsonArray.add(obj);
//                        obj = null;
//                    }
                    jsonArray.add(obj);
                    System.out.println(jsonArray);
                    return jsonArray;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}