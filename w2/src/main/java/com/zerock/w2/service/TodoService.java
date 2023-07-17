package com.zerock.w2.service;

import com.zerock.w2.dao.TodoDAO;
import com.zerock.w2.domain.TodoVO;
import com.zerock.w2.dto.TodoDTO;
import com.zerock.w2.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service도 싱글턴으로 유지하기 위해 enum 이용
 */
@Log4j2
public enum TodoService {

    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper; // VO <-> DTO 라이브러리

    TodoService() { //Service생성될 때 의존성 주입 => composition스퇄ㅋㅋ
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws SQLException {

        //dao에 insert하기위해서는 vo로 변환해야함!
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);//todoDTO를 TodoVO 스퇄로 바꿔줭
        log.info("todoVO = {}",todoVO);
        dao.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws SQLException {

        //DAO로부터 VO를 받겠지, VO ->DTO 변환해서 넘겨주기
        List<TodoVO> list = dao.selectAll();

        //ModelMapper 라이브러리로 list컬렉션 변환방법
        //스트림에서 하나씩 돌리기
        List<TodoDTO> dtoList = list.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public TodoDTO get(Long tno) throws SQLException {

        //DTO로부터 VO로 받은걸 DTO로 변환해서 Controller로
        TodoVO todoVO = dao.selectOne(tno);

        return modelMapper.map(todoVO, TodoDTO.class);
    }

    public void remove(Long tno) throws SQLException {
        dao.deleteOne(tno);
    }

    public void update(TodoDTO todoDTO) throws SQLException {
        //VO로 변환해서
        TodoVO vo = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(vo);
    }




}
