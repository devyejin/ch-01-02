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
import java.util.List;

@Log4j2
@WebServlet(name="todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

    private TodoService todoListController = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("call todo get request");

        try {
            List<TodoDTO> dtoList = todoListController.listAll();
            //받은 데이터를 Model(Request)에 담아서 jsp로 전달해주기
            req.setAttribute("dtoList",dtoList );
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,resp);

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
    }
}
