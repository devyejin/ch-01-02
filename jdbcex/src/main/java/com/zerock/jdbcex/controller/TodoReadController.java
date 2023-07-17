package com.zerock.jdbcex.controller;

import com.zerock.jdbcex.dto.TodoDTO;
import com.zerock.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(value = "/todo/read")
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //쿼리 파라미터는 request에서 뽑아내기
        try {
//            TodoDTO tno = todoService.get(Long.valueOf(req.getParameter("tno")));
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);

            req.setAttribute("dto", todoDTO); //Model(req)에 데이터 담아 넘겨주기

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
}
