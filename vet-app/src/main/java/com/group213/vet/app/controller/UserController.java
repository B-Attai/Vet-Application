package com.group213.vet.app.controller;

import com.group213.vet.app.model.User;
import com.group213.vet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping("")
        @PreAuthorize("hasRole('ADMIN')")
        public List<User> getUser(){
            return userService.listAllUser();
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Integer id){
            try {
                User user = userService.getUser(id);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("")
        public ResponseEntity<?> addUser(@RequestBody User user){
            try {
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Integer id){
            try{
                User existingUser = userService.getUser(id);
                user.setId(id);
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


        @DeleteMapping("/{id}")
        public void deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
        }
}

