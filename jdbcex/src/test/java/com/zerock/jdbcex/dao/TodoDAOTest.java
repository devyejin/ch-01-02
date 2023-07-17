package com.zerock.jdbcex.dao;

import com.zerock.jdbcex.domain.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
class TodoDAOTest {

    private TodoDAO todoDAO;

    @BeforeEach
    void setTodoDAO() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() {
        System.out.println(todoDAO.getTime());
    }

    @Test
    void testInsert() throws SQLException {

        //given
        TodoVO todoVO = TodoVO.builder()
                .title("테스트 타이틀25555")
                .dueDate(LocalDate.now())
                .build(); //.finished(false) 기본값이기 때문에 필요 없음

        //when
        todoDAO.insert(todoVO);

        //then (아직 SELECT 없어서 여기까지만 하고 수작업 확인 ㅋㅋ)
    }

    @Test
    void testSelectAll() throws SQLException {
        //given
        //when
        List<TodoVO> list = todoDAO.selectAll();

        //then
//        for(TodoVO vo : list) {
//            log.debug("vo.title ={}", vo.getTitle());
//        }

        //list.forEach(vo -> log.info(vo))
        list.forEach(log::info);

        Assertions.assertThat(list.size()).isEqualTo(15);
    }

    @Test
    void testSelectOne() throws SQLException {
        //given
//        TodoVO todoVO = TodoVO.builder()
//                .title("단건조회테스트용")
//                .dueDate(LocalDate.now()).build();
//        todoDAO.insert(todoVO); <-- autoIncrement 라 불가

        //when
        TodoVO findTodoVO = todoDAO.selectOne(1L);
        log.info("findTodoVO={}", findTodoVO);

        //then
        //Assertions.assertThat(todoVO.getTitle()).isEqualTo(findTodoVO.getTitle());

    }

    @Test
    void deleteOne() throws SQLException {

        //given
        todoDAO.deleteOne(1L);
    }

    @Test
    void testUpdateOne() throws SQLException {
        //given
        TodoVO vo = TodoVO.builder()
                .tno(3L)
                .dueDate(LocalDate.now())
                .title("변경해볼거야 타이틀 후후후")
                .finished(true)
                .build();

        //when
        todoDAO.updateOne(vo);

    }
}