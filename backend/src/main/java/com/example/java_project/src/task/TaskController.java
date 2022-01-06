package com.example.java_project.src.task;


import com.example.java_project.src.task.model.TaskDTO;
import com.example.java_project.src.task.model.TaskEntity;
import com.example.java_project.utils.BaseException;
import com.example.java_project.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import static com.example.java_project.utils.BaseResponseStatus.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskProvider taskProvider;

    @Autowired
    public TaskController(TaskService taskService, TaskProvider taskProvider) {
        this.taskService = taskService;
        this.taskProvider = taskProvider;
    }

    // todo list 추가
    @PostMapping
    public BaseResponse<List<TaskDTO>> createTask(@AuthenticationPrincipal String userIdx, @RequestBody TaskDTO taskDTO) {
        // todo 리스트에 추가 할 내용이 비었을 때
        if (taskDTO.getContents().compareTo("") == 0 || taskDTO.getContents() == null) {
            return new BaseResponse<>(TASKS_EMPTY_CONTENTS);
        }

        try {
            TaskEntity taskEntity = TaskDTO.toEntity(taskDTO);
            taskEntity.setUserIdx(userIdx);
            List<TaskEntity> taskEntities = taskService.createTask(taskEntity);
            // 자바 스트림
            List<TaskDTO> taskDTOList = taskEntities.stream().map(TaskDTO::new).collect(Collectors.toList());
            return new BaseResponse<>(taskDTOList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // todo list 조회
    @GetMapping
    public BaseResponse<List<TaskDTO>> retrieveTask(@AuthenticationPrincipal String userIdx) {
        try {
            List<TaskEntity> taskEntityList = taskProvider.retrieveTask(userIdx);
            List<TaskDTO> taskDTOList = taskEntityList.stream().map(TaskDTO::new).collect(Collectors.toList());
            return new BaseResponse<>(taskDTOList);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // todo 리스트 수정
    @PutMapping
    public BaseResponse<List<TaskDTO>> updateTask(@AuthenticationPrincipal String userIdx, @RequestBody TaskDTO taskDTO) {
        try {
            TaskEntity taskEntity = TaskDTO.toEntity(taskDTO);
            taskEntity.setUserIdx(userIdx);
            List<TaskEntity> taskEntityList = taskService.updateTask(taskEntity);
            List<TaskDTO> taskDTOList = taskEntityList.stream().map(TaskDTO::new).collect(Collectors.toList());
            return new BaseResponse<>(taskDTOList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // todo 리스트 삭제
    @DeleteMapping
    public BaseResponse<List<TaskDTO>> deleteTask(@AuthenticationPrincipal String userIdx, @RequestBody TaskDTO taskDTO) {
        try {
            TaskEntity taskEntity = taskDTO.toEntity(taskDTO);
            taskEntity.setUserIdx(userIdx);
            List<TaskEntity> taskEntityList = taskService.deleteTask(taskEntity);
            List<TaskDTO> taskDTOList = taskEntityList.stream().map(TaskDTO::new).collect(Collectors.toList());
            return new BaseResponse<>(taskDTOList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
