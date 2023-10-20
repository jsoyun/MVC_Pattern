package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; //얘는 언제 쓸까? 아 값 받아올때!
import java.sql.Statement; //쿼리문날려줄때!

public class DBManager {

    public static Connection getConnect(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String server ="localhost";
        String DBname="inventory_management";
        String user ="root";
        String password = "1517";


        try{
            Class.forName("org.mariadb.jdbc.Driver"); //드라이브 연결
            System.out.println("드라이버 연결완료");
        } catch(Exception e){
            System.out.println("드라이버 로딩 안됨"+ e.getMessage());

        }

        try{
            conn =
                    DriverManager.getConnection("jdbc:mariadb://"+server+"/" +
                            DBname+"?useSSL=false", user,password); //쉼표로 해야되네..



        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("드라이버 연결성공");
        }
        return  conn;


    }
    //select를 수행한 후 연결끊기, 왜냐면 result 값이 있으니까!
    public static void Close( Connection conn ,Statement stmt, ResultSet rs ){
        try{
            if (conn != null) conn.close();
            if (stmt !=null) stmt.close();
            if( rs != null) rs.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("연결 끊기완");
        }

    }
    //DML( insert, update, delete ) 를 수행하고 리소스 해제
    public  static void Close( Connection conn ,Statement stmt ){
        try{
            if (conn != null) conn.close();
            if (stmt !=null) stmt.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("연결 끊기완");
        }

    }



}
