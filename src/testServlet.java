import saeyan.controller.dao.BoardDAO;
import saeyan.controller.dto.BoardVO;
import util.DBManager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/test.do")
public class testServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("서블릿이다~~");

        BoardDAO dao = BoardDAO.getInstance();
//        dao.deleteBoard("2");
        List<BoardVO> result = dao.selectAllBoards();


        //값 확인차 찍어볼건데
        //궁금한게 for문 해보고 스트림으로도 찍어보자
        for (BoardVO value : result){
            System.out.println("Num: " + value.getNum());
            System.out.println("Name: " + value.getName());
            System.out.println("Pass: " + value.getPass());
            System.out.println("Readcount: " + value.getReadcount());
            System.out.println("Title: " + value.getTitle());
            System.out.println("Writedate: " + value.getWritedate());
            System.out.println("=========전체 조회해봤다.=========================");

        }

                BoardVO select3 =dao.selectOneBoardByNum("3");
                System.out.println("3번 Num: " + select3.getNum());
                System.out.println("3번Name: " + select3.getName());
                System.out.println("3번Pass: " + select3.getPass());
                System.out.println("3번Readcount: " + select3.getReadcount());
                System.out.println("3번Title: " + select3.getTitle());
                System.out.println("3번Writedate: " + select3.getWritedate());
                System.out.println("=========3번 조회해봤다.=========================");




        BoardVO bVo = new BoardVO();
        bVo.setNum(3);
        bVo.setPass("비번또수정하");
        bVo.setName("수수말하고");
        bVo.setEmail("정정이메일웃고@djfks");
        bVo.setTitle("과과제목은말야");
        bVo.setContent("맛맛집콘텐츠는 재밌게");
//        bVo.setReadcount(1);
        Timestamp now = Timestamp.valueOf(java.time.LocalDateTime.now());
        bVo.setWritedate(now); //현재시간!
// 기본값이 지정이 안되네

//추가하기
//        dao.insertBoard(bVo);




//        dao.updateReadCount(String.valueOf( bVo.getNum()) );

        dao.updateBoard(bVo);

        //3번 수정 후 조회
        BoardVO updateSelect3 =dao.selectOneBoardByNum("3");
        System.out.println("3번 Num: " + updateSelect3.getNum());
        System.out.println("3번Name: " + updateSelect3.getName());
        System.out.println("3번Pass: " + updateSelect3.getPass());
        System.out.println("3번Readcount: " + updateSelect3.getReadcount());
        System.out.println("3번Title: " + updateSelect3.getTitle());
        System.out.println("3번Writedate: " + updateSelect3.getWritedate());
        System.out.println("===수정후 ==3번 조회해봤다.=========================");


        String pass ="password123";
        String num = "5";
      //checkPassword 가자~!

       BoardVO d=  dao.checkPassword(pass, num);
        System.out.println("====비번 쵁쵁================");
        System.out.println("Num: " + d.getNum());
        System.out.println("Name: " + d.getName());
        System.out.println("Pass: " + d.getPass());
        System.out.println("Readcount: " + d.getReadcount());
        System.out.println("Title: " + d.getTitle());
        System.out.println("Writedate: " + d.getWritedate());



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
