package saeyan.controller.action;

import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


public class BoardWriteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //얘는 데이터베이스와 연결되는구나.

        BoardVO bVO = new BoardVO();
        bVO.setName(request.getParameter("name"));
        bVO.setPass(request.getParameter("pass"));
        bVO.setEmail(request.getParameter("email"));
        bVO.setTitle(request.getParameter("title"));
        bVO.setContent(request.getParameter("content"));
        Timestamp now = Timestamp.valueOf(java.time.LocalDateTime.now());
        bVO.setWritedate(now);

        BoardDAO Dao = BoardDAO.getInstance();
        Dao.insertBoard(bVO);

        //아 쓰고나서 목록을 보여주는 Action으로 넘기는거구나.
        new BoardListAction().execute(request, response);

    }
}
