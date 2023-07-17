package com.zerock.jdbcex.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor //기본 생성자
@Getter
@Setter
@Builder
@ToString
public class TodoDTO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
