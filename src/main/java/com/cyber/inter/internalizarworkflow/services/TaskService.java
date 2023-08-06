package com.cyber.inter.internalizarworkflow.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cyber.inter.internalizarworkflow.dtos.TaskRecordDto;
import com.cyber.inter.internalizarworkflow.models.TaskModel;
import com.cyber.inter.internalizarworkflow.repositories.TaskRepository;

import jakarta.validation.Valid;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public ResponseEntity<List<TaskModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll());
    }

      public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
        var taskModel = new TaskModel();
        taskModel.setActive(true);
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskModel));
    }

    public ResponseEntity<Object> completeTask(@PathVariable(value = "id") UUID id){
        Optional<TaskModel> taskO = taskRepository.findById(id);

        if(taskO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TASK NOT FOUND");
        }

        var taskModel = taskO.get();
        taskModel.setActive(false);
        taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body("TASK COMPLETED");
    }

    public ResponseEntity<List<TaskModel>> getAllActives(){
        List<TaskModel> activesTasks = taskRepository.findByActiveTrue();

        return ResponseEntity.status(HttpStatus.OK).body(activesTasks);
    }


    public ResponseEntity<?> getOneActive(@PathVariable(value = "id") UUID id){
        Optional<TaskModel> taskO = taskRepository.findByActiveTrueAndIdTask(id);

        if (!taskO.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found or is not active");
        }
    
        return ResponseEntity.status(HttpStatus.OK).body(taskO.get());
    }

    public ResponseEntity<List<TaskModel>> getAllComplete(){
        List<TaskModel> completedTasks = taskRepository.findByActiveFalse();

        return ResponseEntity.status(HttpStatus.OK).body(completedTasks);
    }

    public ResponseEntity<?> getOneComplete(@PathVariable(value = "id") UUID id){
        Optional<TaskModel> taskO = taskRepository.findByActiveFalseAndIdTask(id);

        if(!taskO.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found or Is Not Completed");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(taskO.get());
    }

    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id){
        Optional<TaskModel> taskO = taskRepository.findById(id);

        if (!taskO.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found");
        }

        taskRepository.delete(taskO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task Deleted");
    }

}
