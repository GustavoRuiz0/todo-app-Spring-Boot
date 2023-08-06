package com.cyber.inter.internalizarworkflow.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyber.inter.internalizarworkflow.models.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, UUID>  {
    List<TaskModel> findAll();

    TaskModel findByIdTask(UUID idTask);

    List<TaskModel> findByActiveFalse();


    Optional<TaskModel> findByActiveFalseAndIdTask(UUID id);

    Optional<TaskModel> findByActiveTrueAndIdTask(UUID id);

    List<TaskModel> findByActiveTrue();

}
