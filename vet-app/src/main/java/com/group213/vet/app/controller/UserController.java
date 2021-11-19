package com.group213.vet.app.controller;

import com.group213.vet.app.model.User;
import com.group213.vet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping("")
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

        @PostMapping("/new")
        public ResponseEntity<?> addUser(@RequestBody User user){
            try {
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        @PutMapping("/modify/{id}")
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


        @DeleteMapping("/remove/{id}")
        public void deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
        }
}

