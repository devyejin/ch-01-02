package com.zerock.jdbcex.service;

import com.zerock.w2.dto.TodoDTO;
import com.zerock.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;


@Log4j2
class TodoServiceTest {

    private TodoService todoService;

    @BeforeEach
    public void makeTodoService() {
        todoService = TodoService.INSTANCE;
    }

    /**
     * ModelMapper를 Service가 소유하고, register 로직 내부에서 DTO->VO로 변환하기 때문에
     * 사용할때는 그냥 DTO만 넣어주면 정상 작동함!
     */
    @Test
    public void testRegister() throws SQLException {
        //given
        TodoDTO todoDTO = TodoDTO.builder()
                .title("메퍼 테스트용 데이터 타이틀")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();
        log.info(todoDTO);
        //when
        todoService.register(todoDTO);

    }

}