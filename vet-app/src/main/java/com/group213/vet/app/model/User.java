package com.group213.vet.app.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // Annotate the class is an entity in the database
@Table(name="Users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
        }) //Annotate the name of the table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generate strategies for the values of primary keys
    private Integer id;
    @NotBlank
    @Size(max = 30)
    private String username;
    private String theType;
    @NotBlank
    @Size(max = 50)
    private String email;
    private String activationDate;
    @NotBlank
    @Size(max = 120)
    private String password;
    private boolean active;

    public User(){
    }

    public User(String username, String email, String password){ //String theType, String email, String activationDate){
//        this.id = id; //Can set to use Random UUID
        this.username = username;
        this.email = email;
        this.password = password;
//        this.theType = theType;
//        this.email = email;
//        this.activationDate = activationDate;
    }

    public Integer getId(){
        return id;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {return password;}

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    public String getTheType() {
        return theType;
    }

    public void setTheType(String theType) {
        this.theType = theType;
    }

    @OneToMany(targetEntity = PrescriptionRecords.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<PrescriptionRecords> prescriptionRecords;

    @OneToMany (targetEntity = TheComment.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<TheComment> theComment;

    @OneToMany(targetEntity = AnimalPhoto.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<AnimalPhoto> animalPhoto;

    @OneToMany(targetEntity = AnimalRequests.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<AnimalRequests> animalRequests;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<Role> roles = new HashSet<>();

}