package com.group213.vet.app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity // Annotate the class is an entity in the database
@Table(name="Users") //Annotate the name of the table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String theType;
    private String email;
    private String activationDate;

    public User(){
    }

    public User(int id, String username, String theType, String email, String activationDate){
        this.id = id; //Can set to use Random UUID
        this.username = username;
        this.theType = theType;
        this.email = email;
        this.activationDate = activationDate;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generate strategies for the values of primary keys
//    public int getId(){
//        return id;
//    }

    public void setId(Integer id){
        this.id = id;
    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getTheType() {
        return theType;
    }

    public void setTheType(String theType) {
        this.theType = theType;
    }

//    @OneToMany(mappedBy =  "userId",fetch = FetchType.EAGER, cascade = CascadeType.ALL )
//    private List<PrescriptionRecords> prescriptionRecords;
    @OneToMany(targetEntity = PrescriptionRecords.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<PrescriptionRecords> prescriptionRecords;
}