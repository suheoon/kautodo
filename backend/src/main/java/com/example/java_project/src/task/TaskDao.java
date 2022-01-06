package com.example.java_project.src.task;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.java_project.src.task.model.*;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<TaskEntity, String> {
    List<TaskEntity> findByIdx(String idx);
    List<TaskEntity> findByUserIdx(String userIdx);
}
