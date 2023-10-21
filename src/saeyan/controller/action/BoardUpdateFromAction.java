package saeyan.controller.action;

import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("board_update_form")
public class BoardUpdateFromAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //업데이트하는 폼으로 이동
        String sql ="/board/boardUpdate.jsp";

        //업데이트 폼 이동할 때 조회수 올리기
        String num = request.getParameter("num");
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.updateReadCount(num);

        //board 속성값을 넘겨줘야함!!! 이게 안되어서 업데이트 수정이 안됨.
        BoardVO bVo = boardDAO.selectOneBoardByNum(num);
        request.setAttribute("board",bVo );

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(sql);
        requestDispatcher.forward(request,response);
    }
}
