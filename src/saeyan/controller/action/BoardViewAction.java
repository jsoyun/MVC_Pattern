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
        //엇 여기서 readcount 수 높여야되지 않나? 상세 보기 하면 추가되는 작e동!
        bDao.updateReadCount(num);
        BoardVO oneBoardByNum = bDao.selectOneBoardByNum(num);
        request.setAttribute("board", oneBoardByNum);


//    아 밑에 작업 필요 없구나!!
//        request.setAttribute("name",oneBoardByNum.getName());
//        request.setAttribute("pass",oneBoardByNum.getPass());
//        request.setAttribute("email",oneBoardByNum.getEmail());
//        request.setAttribute("title",oneBoardByNum.getTitle());
//
//        request.setAttribute("readcound",oneBoardByNum.getReadcount());
//        request.setAttribute("writedate",oneBoardByNum.getWritedate());


        String url ="board/boardView.jsp";
        request.getRequestDispatcher(url).forward(request,response);



    }
}
