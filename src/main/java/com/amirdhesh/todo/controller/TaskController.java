package com.amirdhesh.todo.controller;

import com.amirdhesh.todo.model.Task;
import com.amirdhesh.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskservice;

    @Autowired
    public TaskController(TaskService taskservice) {
        this.taskservice = taskservice;
    }

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody Task data) {
        Task task = taskservice.addTask(data);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<Task>> getTask(@PathVariable int id) {
        List<Task> tasks = taskservice.listTask(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/task/status")
    public ResponseEntity<Task> updateStatus(@RequestParam int id, @RequestParam Task.TaskStatus status) {
        return new ResponseEntity<>(taskservice.updateStatus(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        taskservice.deleteTask(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
