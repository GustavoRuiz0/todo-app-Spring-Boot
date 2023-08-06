package com.cyber.inter.internalizarworkflow.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.inter.internalizarworkflow.dtos.TaskRecordDto;
import com.cyber.inter.internalizarworkflow.models.TaskModel;
import com.cyber.inter.internalizarworkflow.repositories.TaskRepository;
import com.cyber.inter.internalizarworkflow.services.TaskService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<TaskModel>> getAll(){
        return taskService.getAll();
    }

    @PostMapping(value="/tasks")
    public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
        return taskService.saveTask(taskRecordDto);
    }

    @GetMapping(value = "tasks/actives")
    public ResponseEntity<List<TaskModel>> getAllActives(){
        return taskService.getAllActives();
    }

    @GetMapping(value = "tasks/actives/{id}")
    public ResponseEntity<?> getOneActive(@PathVariable(value = "id") UUID id){
        return taskService.getOneActive(id);
    }

    @GetMapping(value = "tasks/complete")
    public ResponseEntity<List<TaskModel>> getAllCompleted(){
        return taskService.getAllComplete();
    }

    @GetMapping(value = "tasks/complete/{id}")
    public ResponseEntity<?> getOneCompleted(@PathVariable(value = "id") UUID id){
        return taskService.getOneComplete(id);
    }

    @PutMapping(value = "/tasks/complete/{id}")
    public ResponseEntity<Object> complteTask(@PathVariable(value = "id") UUID id){
        return taskService.completeTask(id);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id){
        return taskService.deleteTask(id);
    }    
    
}
