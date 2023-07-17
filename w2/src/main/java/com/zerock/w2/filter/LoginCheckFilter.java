package com.zerock.w2.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter(urlPatterns = {"/todo/*"}) // /todo/하위 경로는 해당 필터를 거침 -> 즉, /todo/* Controller로 가기전에 필터부터!
@Log4j2
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("========== login check filter ===============");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginInfo") == null) {
            resp.sendRedirect("/login");
            return; //호출한 곳으로 돌려버림 즉, 다음 doFilter가 실행되지 않음!
        }



        chain.doFilter(request,response);
    }
}
