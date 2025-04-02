package com.amirdhesh.todo.service;

import com.amirdhesh.todo.exception.NotFound;
import com.amirdhesh.todo.model.Task;
import com.amirdhesh.todo.model.User;
import com.amirdhesh.todo.repository.TaskRepo;
import com.amirdhesh.todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepo taskrepo;
    private UserRepo userrepo;

    @Autowired
    public TaskService(TaskRepo taskrepo, UserRepo userrepo) {
        this.taskrepo = taskrepo;
        this.userrepo = userrepo;
    }

    public Task addTask(Task task) {
        return taskrepo.save(task);
    }

    public String updateStatus(int task_id, String status) {
        Task task = taskrepo.findById(task_id).orElseThrow(() -> new NotFound("Task not Found"));
        task.setStatus(status);
        taskrepo.save(task);
        return "Status Updated";
    }

    public void deleteTask(int task_id) {
        taskrepo.deleteById(task_id);
    }


    public List<Task> listTask(int user_id) {
        User user = userrepo.findById(user_id).orElseThrow(() -> new NotFound("Task not Found"));
        return taskrepo.findByUserId(user_id);
    }
}
