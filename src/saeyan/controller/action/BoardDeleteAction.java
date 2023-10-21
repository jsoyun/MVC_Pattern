package saeyan.controller.action;

import saeyan.controller.dao.BoardDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BoardDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String url ="/BoardServlet?command=board_list";
        BoardDAO dao = BoardDAO.getInstance();
        String num = request.getParameter("num");
        dao.deleteBoard(num);
        System.out.println("삭제 완---------------------------");
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);
        System.out.println("boardList로 포워드 완---------------");

        //디비에 있는 데이터 모두 가져오기
        new BoardListAction().execute(request,response);
    }
}
