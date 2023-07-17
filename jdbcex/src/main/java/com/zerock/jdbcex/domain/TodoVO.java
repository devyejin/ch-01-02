package com.zerock.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO { //VO는 주로 읽기 전용 -> 외부에 수정 권한X,  set은 수정이고 builder는 생성이고
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished; //boolean 타입은 get/set isXXX로 만들어짐
}
