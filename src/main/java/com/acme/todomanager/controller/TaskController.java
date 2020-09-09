package com.acme.todomanager.controller;

import com.acme.todomanager.entity.Task;
import com.acme.todomanager.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private ITaskRepository repository;

    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public List<Task> List() {
        return this.repository.findAll();
    }

    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public Task Create(@RequestBody Task task) {
        return this.repository.save(task);
    }
}
