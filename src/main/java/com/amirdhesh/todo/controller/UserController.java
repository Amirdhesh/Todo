package com.amirdhesh.todo.controller;

import com.amirdhesh.todo.exception.NotFound;
import com.amirdhesh.todo.model.User;
import com.amirdhesh.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userservice.addUser(user), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userservice.getUser(id);
        if (user == null) throw new NotFound("User Not Found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getListOfUsers() {
        return new ResponseEntity<>(userservice.listUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/User/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userservice.deleteUser(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
