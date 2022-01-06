package com.example.java_project.src.task;



import com.example.java_project.utils.BaseException;
// static 으로 import
import static com.example.java_project.utils.BaseResponseStatus.*;
import com.example.java_project.src.task.model.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskDao taskDao;
    private TaskProvider taskProvider;

    @Autowired
    public TaskService (TaskDao taskDao, TaskProvider taskProvider) {
        this.taskDao = taskDao;
        this.taskProvider = taskProvider;
    }

    // task 생성
    public List<TaskEntity> createTask(final TaskEntity taskEntity) throws BaseException {
        try {
            taskDao.save(taskEntity);
            return taskDao.findByUserIdx(taskEntity.getUserIdx());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // task 수정
    public List<TaskEntity> updateTask(final TaskEntity taskEntity) throws BaseException {
        try {
            final List<TaskEntity> taskEntityByIdx = taskDao.findByIdx(taskEntity.getIdx());
            TaskEntity tempEntity = taskEntityByIdx.get(0);
            tempEntity.setContents(taskEntity.getContents());
            tempEntity.setDone(taskEntity.isDone());
            taskDao.save(tempEntity);
            return taskProvider.retrieveTask(taskEntity.getUserIdx());

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // task 삭제
    public  List<TaskEntity> deleteTask(final TaskEntity taskEntity) throws BaseException {
        try {
            taskDao.delete(taskEntity);
            return taskProvider.retrieveTask(taskEntity.getUserIdx());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
