package com.example.java_project.src.task.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String  idx;
    private String userIdx;
    private String contents;
    private boolean done;

    public TaskDTO(TaskEntity taskEntity) {
        this.idx = taskEntity.getIdx();
        this.userIdx = taskEntity.getIdx();
        this.contents = taskEntity.getContents();
        this.done = taskEntity.isDone();
    }

    // taskDTO를 taskEntity로 전환하는 함수
    public static TaskEntity toEntity(final TaskDTO taskDTO) {
        return TaskEntity.builder()
                .idx(taskDTO.getIdx())
                .userIdx(taskDTO.getUserIdx())
                .contents(taskDTO.getContents())
                .done(taskDTO.isDone())
                .build();
    }
}
