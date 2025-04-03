package com.amirdhesh.todo.service;

import com.amirdhesh.todo.model.User;
import com.amirdhesh.todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepo userrepo;

    @Autowired
    public UserService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }

    public User addUser(User user) {
        return userrepo.save(user);
    }

    public List<User> listUsers() {
        return userrepo.findAll();
    }

    public User getUser(int id) {
        return userrepo.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userrepo.deleteById(id); //it wont throw any exception
    }
}
