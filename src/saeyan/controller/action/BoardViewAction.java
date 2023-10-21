package saeyan.controller.action;

import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("board_view")
public class BoardViewAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num =request.getParameter("num");
        BoardDAO bDao = BoardDAO.getInstance();

        bDao.updateReadCount(num);
        BoardVO oneBoardByNum = bDao.selectOneBoardByNum(num);
        request.setAttribute("board", oneBoardByNum); //board 객체로 속성값 설정하기!!


        String url ="board/boardView.jsp";
        request.getRequestDispatcher(url).forward(request,response);



    }
}
