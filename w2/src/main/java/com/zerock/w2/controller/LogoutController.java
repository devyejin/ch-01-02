package com.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    //변화생기는거니까 POST처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("======log out call===========");

        HttpSession session = req.getSession();

        //세션객체에서 해당 세션 제거
        session.removeAttribute("loginInfo"); //이용자의 세션에는 다양한 정보가 있는데 그 중 loginInfo 속성 제거
        session.invalidate(); //세션 자체를 무효화시킴! (위에꺼만 하면 보안상 위험, invalidate로 무효화만 해도 됨)

        resp.sendRedirect("/");

    }
}
