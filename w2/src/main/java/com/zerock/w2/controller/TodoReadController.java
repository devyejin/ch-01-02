package com.zerock.w2.controller;

import com.zerock.w2.dto.TodoDTO;
import com.zerock.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * <쿠키 활용>
 *     조회한 목록 쿠키에 기록하기 -> tno 활용
 *     (목록 뿌려줄 때 마다 쿠키 확인해야겠지)
 *
 * 현재 요청에 있는 모든 쿠키 중 조회 목록 쿠키(viewTodos) 찾아내는 메서드
 * 조회한 tno는 viewTodos 이름 쿠키에 1-3-5 이런형태로 담기, 한번 조회했던건 중복으로 담지 않음
 * 특정한 tno가 쿠키에 들어있는지 확인
 */
@Log4j2
@WebServlet(value = "/todo/read")
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //쿼리 파라미터는 request에서 뽑아내기
        try {
//            TodoDTO tno = todoService.get(Long.valueOf(req.getParameter("tno")));
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);

            req.setAttribute("dto", todoDTO); //Model(req)에 데이터 담아 넘겨주기

            //클라이언트가 보낸 쿠키들 중 viewTodos(조회목록담긴)쿠키 찾기
            Cookie viewTodosCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodosCookie.getValue(); //쿠키 key는 viewTodos , value를 뽑아
            //value는 1-3-5 이런식으로 tno 담겨있음

            boolean exist = false;

            //String.indexOf(target) : target문자열 위치 int로 반환 ( 0부터 시작)
            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0) { //지금조회하는 tno가 조회목록에 있다면
                exist = true;
            }

            log.info("exist = {}", exist);

            if(!exist) {
                todoListStr += tno + "-";
                viewTodosCookie.setValue(todoListStr);
                viewTodosCookie.setMaxAge(60*60*24); //그리고 주의할 점은 쿠키가 변경되면 유효시간도 다시 갱신된다는 점!!!
                viewTodosCookie.setPath("/");
                resp.addCookie(viewTodosCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);


        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) { //쿠키들 중 특정 쿠키찾기

        Cookie targetCookie = null;

        if(cookies !=null && cookies.length > 0) { //여러 쿠키 중 필요한 쿠키 찾기
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)) {
                    targetCookie = cookie;
                }
            }
        }

        if(targetCookie == null) { //사용자 정의 쿠키가 없다면,  쿠키 만들어서 보내기
             targetCookie = new Cookie(cookieName, "");
             targetCookie.setPath("/");
             targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }
}
