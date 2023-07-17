package com.zerock.w2.controller;

import com.zerock.w2.dto.TodoDTO;
import com.zerock.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        //로그인한 사용자만 게시글 등록 가능 -> 로그인한 사용자인지 체크(How? -> JSESSIONID를 통해서)
        HttpSession session = req.getSession(); //기존 세션이 존재하면 -> 기존session반환, 없으면 새로운 session 반환

        if(session.isNew()) {
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
            resp.sendRedirect("/login");
            return;
        }

        //세션(공간)은 있지만, 만료되서 시션 컨텍스트 key 불일치하는 경우
        //서버에서 발급한 JSESSIONID key가 loginInfo 인 상황 <---글고 지금은 야매로, 그냥 loginInfo라는 이름의 세션이 있는지만 체크중임..
        if(session.getAttribute("loginInfo") == null) {
            log.info("JSESSIONID 만료된 회원, 사용자 정보 없음");
            resp.sendRedirect("/login");
            return;
        }

        //정상적 로그인 회원
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