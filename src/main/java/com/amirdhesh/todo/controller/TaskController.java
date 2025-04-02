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

    @GetMapping("/tasks/{user_id}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable int user_id) {
        List<Task> tasks = taskservice.listTask(user_id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/task/{task_id}")
    public ResponseEntity<String> updateStatus(@PathVariable int task_id, @RequestBody Task task) {
        return new ResponseEntity<>(taskservice.updateStatus(task_id, task.getStatus()), HttpStatus.OK);
    }

    @DeleteMapping("/task/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable int task_id) {
        taskservice.deleteTask(task_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
