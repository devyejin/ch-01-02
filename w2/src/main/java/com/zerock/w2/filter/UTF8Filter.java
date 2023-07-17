package com.zerock.w2.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}) //모든 요청에 필터 적용
@Log4j2
public class UTF8Filter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("=======UTF8 filter=========");

        HttpServletRequest req = (HttpServletRequest) request;
        //요청을  UTF8로 받기
        req.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }
}
