package com.zerock.w2.controller;

import com.zerock.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(value="/todo/remove")
public class TodoRemoveController extends HttpServlet {

    protected TodoService todoService = TodoService.INSTANCE;

    //삭제-> 데이터 변화-> POST

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));

        try {
            todoService.remove(tno);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("remove error");
        }

        //삭제하고 목록으로 이동시키기 PRG
        resp.sendRedirect("/todo/list");
    }
}
