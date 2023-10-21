package saeyan.controller.action;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;


public class BoardCheckPassAction implements Action  {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ////
        //여기서 비번 체크.
        String url = null;
        String num = request.getParameter("num");
        String pass = request.getParameter("pass");

        BoardDAO dao = BoardDAO.getInstance();
//        dao.checkPassword(num, pass); 이거 왜 안쓰지.?
        BoardVO vo = dao.selectOneBoardByNum(num); //번호기준으로 값가져옴

        if (vo.getPass().equals(pass)){
            url ="/board/checkSuccess.jsp";
        } else{ //실패
            url ="/board/boardCheckPass.jsp";
            request.setAttribute("message","비밀번호가 틀렸습니다.");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);





//        비번 체크
//        수정할 때랑
//        삭제할 때가 다를텐데..?
//        맞으면
    }

}
