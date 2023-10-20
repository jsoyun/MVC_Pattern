package saeyan.controller;

import saeyan.controller.action.Action; //인터페이스 사용하려면 가져와야돼?

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//MVC 패턴의 컨트롤러 역할을 한다.
//command를 통해 서블릿으로 넘겨준다.

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //생성자는 왜 필요할까?
    public BoardServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String command =request.getParameter("command");
        System.out.println("command 요청받음 확인:"+command);

//        if (command.equals("boardList") ){
//            out.println("여기는 BoardList");
//
//        }
        out.println("여기는 BoardServlet");
        //여기로 커맨드와 함께 넘어와야겠네.

        //사실 여기가 제일 이해안가요
        ActionFactory af = ActionFactory.getInstance();
        Action action = af.getAction(command);
        //근데 어떻게 실행하지?
        if(action != null){
            action.execute(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    //post로 요청되더라도 doGet 호출
    doGet(request,response);
    }
}
