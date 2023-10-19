import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;
import util.DBManager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test.do")
public class testServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("서블릿이다~~");

        BoardDAO dao = BoardDAO.getInstance();
//        dao.deleteBoard("2");
        dao.selectAllBoards();

        BoardVO bVo = new BoardVO();
        bVo.setPass("비밀번호1234");
        bVo.setName("이소윤");
        bVo.setEmail("soyun123@djfks");
        bVo.setTitle("제목짓는게 어렵긴해");
        bVo.setContent("좋은 콘텐츠 만들어주세요");
//        bVo.setReadcount(1);
//        bVo.setWritedate();  //기본값이 지정이 안되네
        dao.insertBoard(bVo);

//        dao.Connect();
//        DBManager dbManager =new DBManager();
//        dbManager.getConnect();
        System.out.println("getConnect실행~!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
