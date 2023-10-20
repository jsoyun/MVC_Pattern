package saeyan.controller.action;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {

    //추상메소드 정의할 때 abstract 붙이진 않네? -> 명시적으로 붙이지 않아도 암시적으로 추상메소드로 감지됨
    //모든 모델들은 Action인터페이스의 상속을 받는 구현 객체이어야해서 이 객체를 액션 객체라고 한다.
    //컨트롤러에서는 직접 액션객체를 생성하지 않는 대신 액션 객체를 생성해내는 팩토리 역할을 하는 클래스를 통해 생성한다.
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
}
