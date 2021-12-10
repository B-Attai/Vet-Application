package com.group213.vet.app.respository;

import com.group213.vet.app.model.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository
        extends JpaRepository<Animal, Integer> {

    @Query(value = "select animalId, distinguishingFeatures, color, alerts from Animals", nativeQuery = true)
    List<String> findAnimalBreedSpecies();

}
