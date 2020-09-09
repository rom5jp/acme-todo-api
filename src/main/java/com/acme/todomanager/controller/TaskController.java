package com.acme.todomanager.controller;

import com.acme.todomanager.entity.Task;
import com.acme.todomanager.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> Update(@PathVariable("id") Long id, @PathParam("done") boolean done) {
        Optional<Task> fetchedTask = this.repository.findById(id);

        if (fetchedTask.isPresent()) {
            Task updatedTask = fetchedTask.get();
            updatedTask.setDone(done);
            this.repository.save(updatedTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Task> Delete(@PathVariable("id") Long id) {
        Optional<Task> fetchedTask = this.repository.findById(id);
        if (fetchedTask.isPresent()) {
            this.repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
