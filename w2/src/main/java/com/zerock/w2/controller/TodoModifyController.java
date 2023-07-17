package com.zerock.w2.controller;

import com.zerock.w2.dto.TodoDTO;
import com.zerock.w2.service.TodoService;
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
@WebServlet(value = "/todo/modify")
public class TodoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final  DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //특정 게시글은 쿼리파라미터
            Long tno = Long.parseLong(req.getParameter("tno"));
            //데이터 담아서 페이지로 이동
            TodoDTO todoDTO = todoService.get(tno);

            req.setAttribute("dto",todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req,resp);

        } catch (SQLException e) {
           log.error(e.getMessage());
           throw new ServletException("========== occur error of modify get request ==========");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));
        String finishedStr = req.getParameter("finished");

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tno)
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
                .finished(finishedStr != null && finishedStr.equals("on")) //on이면 true저장
                .build();

        try {
            todoService.update(todoDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //PRG패턴
        resp.sendRedirect("/todo/list");
    }
}
