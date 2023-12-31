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

    }


}
