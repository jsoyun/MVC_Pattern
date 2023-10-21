package saeyan.controller.action;

import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

//@WebServlet("board_update")
public class BoardUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //데이터베이스에 업데이트
        BoardVO bVo = new BoardVO();

        BoardDAO dao= BoardDAO.getInstance();


        bVo.setNum(Integer.parseInt(request.getParameter("num"))); //없으면 안됨
        bVo.setName(request.getParameter("name"));
        bVo.setPass(request.getParameter("pass"));
        bVo.setEmail(request.getParameter("email"));
        bVo.setTitle(request.getParameter("title"));
        bVo.setContent(request.getParameter("content"));
        Timestamp now = Timestamp.valueOf(java.time.LocalDateTime.now());
        bVo.setWritedate(now);


        dao.updateBoard(bVo);

        new BoardListAction().execute(request,response);

    }
}
