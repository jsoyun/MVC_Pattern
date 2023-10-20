package saeyan.controller;

import saeyan.controller.action.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
//커맨트(command) 패턴으로 작업처리를 위한 명령 클래스. ActionFactory

//언제실행?? 이 클래스 어떻게 쓸지 제일 긴가민가함.
//command를 확인하고 그에 해당하는 액션 객체를 생성한다.
public class ActionFactory  {

    //객체 생성, 싱글톤으로 진행하는군. getInstance 호출해서 생성된 객체 사용!
    private static ActionFactory instance = new ActionFactory();

    //생성자를 기술해줘야하나?
    private ActionFactory(){
        super();
    }
    public static ActionFactory getInstance(){
        return instance;
    }
    //지금 기술하는 메소드가 바로 BoardServlet의 doGet메소드에서 호출된다. getAction메소드에서는 command를 받아서 거기에 맞는 액션을 리턴한다.
    public Action getAction(String command){
        Action action = null; // 인터페이스아니었나? Action?
        System.out.println("ActionFatory:"+ command);
        if(command.equals("board_list")){
            action = new BoardListAction(); //이게 어떻게 가능할까? BoardListAction은 action으로부터 상속받았다.
        } else if (command.equals("board_write_form")) {
            action = new BoardWriteFormAction();
        } else if (command.equals("board_write")){
            action = new BoardWriteAction();

        } else if (command.equals("board_view")) {
            action = new BoardViewAction();
        } else if (command.equals("board_write")){
            action = new BoardWriteAction();
        } else if (command.equals("board_check_pass_form")){
            action = new BoardCheckPassFormAction();
        } else if (command.equals("board_check_pass")){
            action = new BoardCheckPassAction();
        }
        return action;
    }




}
