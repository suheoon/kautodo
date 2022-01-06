package com.example.java_project.src.task;

import com.example.java_project.utils.BaseException;
import com.example.java_project.src.task.model.TaskEntity;
import static com.example.java_project.utils.BaseResponseStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskProvider {

    private TaskDao taskDao;

    @Autowired
    public TaskProvider (TaskDao taskDao) {
        this.taskDao = taskDao;
    }
    // task 검색
    public List<TaskEntity> retrieveTask(final String userIdx) throws BaseException {
        try {
            return taskDao.findByUserIdx(userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
