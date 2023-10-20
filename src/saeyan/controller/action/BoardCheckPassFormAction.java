package saeyan.controller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardCheckPassFormAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String url ="board/boardCheckPass.jsp";
       RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
       requestDispatcher.forward(request, response);
    }
    //비번 채크 폼 이동
}
