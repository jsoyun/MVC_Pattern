package saeyan.controller.action;

import java.util.List;
import saeyan.controller.ActionFactory;
import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//요청을 받으면 게시글 리스트 화면을 표히사기 위한 액션 클래스(모델)을 만든다.

public class BoardListAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //여기 코드 따라침요!!
        String jspUrl ="/board/boardList.jsp";
        BoardDAO boardDAO =BoardDAO.getInstance();
        List<BoardVO> boardVOList = boardDAO.selectAllBoards();
        request.setAttribute("boardList", boardVOList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspUrl);
        requestDispatcher.forward(request, response);

//       String command=  request.getParameter("command");
//        ActionFactory af = ActionFactory.getInstance();
//        Action action = af.getAction(command);
//        action.execute(request,response);

    }
    //BoardServlet 에서 command가 리스트라서 여기로 오면
    //ActionFactory가 command에 따라서 실행하는거 아닐까? 객체 생성을...어떤 객체를 생성하는데? 액션 객체를 생성하겠지?

}
