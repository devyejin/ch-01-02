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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(value="/todo/register")
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("======== call todo register form =============");
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("======== call todo register =============");

        TodoDTO todoDTO = TodoDTO.builder().title(req.getParameter("title"))
                //request param으로 들어오는건 다 "String"임!!
                .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
                .build();

        try {
            todoService.register(todoDTO); //이제 여긴 예외를 잡아야 할 곳! 던지기 그만
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //PRG패턴!
        resp.sendRedirect("/todo/list");

    }
}