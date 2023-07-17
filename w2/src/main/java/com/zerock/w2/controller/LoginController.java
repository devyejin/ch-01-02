package com.zerock.w2.controller;

import com.zerock.w2.dto.MemberDTO;
import com.zerock.w2.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(value="/login")
public class LoginController extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("=====call login page (get) =======");
        req.getRequestDispatcher("/WEB-INF/todo/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("=====call login (post) =======");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String auto = req.getParameter("auto");

        //로그인 요청할 때, 자동로그인 체크여부 확인
        boolean rememberMe = auto != null && auto.equals("on");

        try {
            // (중요) 자동 로그인할거면, uuid를 쿠키로 보내서 확인할건데, 로그인 성공도 못한 사람한테 uuid(로그인 유지용) 줄 필요가 없음
            // 그래서 먼저 로그인 성공 유무부터 호출해봐야함!

            //정상로그인시, 로그인 사용자 정보 세션에 담기
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw); //<-- 여기서 로그인 실패했으면 예외로 넘어갔을 테고 밑은 정상 플로우

            if(rememberMe) {
                String uuid = UUID.randomUUID().toString();

                MemberService.INSTANCE.updateUuid(mid, uuid); //db에 uuid 저장
                memberDTO.setUuid(uuid); //DTO에도 저장해주고

                //클라이언트 쿠키에 보내주기
                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*7); //유효기간 1주일
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);
            }


            HttpSession session = req.getSession(); //사용자 세션 공간, <-- 클라이언트를 위한 공간
            session.setAttribute("loginInfo", memberDTO); // <-- 거기다 memberDTO를 저장(사용자 정보), 세션에 담았기 때문에 생명주기가 SESSION
            resp.sendRedirect("/todo/list");

        } catch (Exception e) {
            resp.sendRedirect("/login?result=error"); //쿼리파라미터로 error났다고 알려줌
        }

    }
}
