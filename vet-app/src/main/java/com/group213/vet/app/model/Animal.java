package com.group213.vet.app.model;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "Animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animalId")
    private int animalId;
    private String species;
    private int weight;
    private int tattooNum;
    private String cityTattoo;
    private String birthDate;
    private String breed;
    private String sex;
    private long rfid;
    private long microchip;
    private String theStatus;
    private String diet;
    private String region;
    private String subspecies;
    private String distinguishingFeatures;
    private String color;

    public Animal(int animalId, String species, int weight, int tattooNum, String cityTattoo,
                  String birthDate, String breed, String sex, int rfid, int microchip,
                  String theStatus, String diet, String region, String subspecies,
                  String distinguishingFeatures, String color)
    {
        this.animalId = animalId;
        this.species = species;
        this.weight = weight;
        this.tattooNum = tattooNum;
        this.cityTattoo = cityTattoo;
        this.birthDate = birthDate;
        this.breed = breed;
        this.sex = sex;
        this.rfid = rfid;
        this.microchip = microchip;
        this.theStatus = theStatus;
        this.diet = diet;
        this.region = region;
        this.subspecies = subspecies;
        this.distinguishingFeatures = distinguishingFeatures;
        this.color = color;
    }

    public int getAnimalId() {
        return animalId;
    }

    @OneToMany(targetEntity = PrescriptionRecords.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "animalId", referencedColumnName = "animalId")
    private List<PrescriptionRecords> prescriptionRecords;

    @OneToMany(targetEntity = AnimalStatus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "animalId", referencedColumnName = "animalId")
    private List<AnimalStatus> animalStatus;
}