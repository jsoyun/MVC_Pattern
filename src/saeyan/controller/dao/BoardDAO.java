package saeyan.controller.dao;

import saeyan.controller.dto.BoardVO;
import util.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    PreparedStatement stmt = null;
    ResultSet rs = null;
//    DBManager dbManager = new DBManager();

    private BoardDAO(){

    }
    //인스턴스 객체 BoardDAO 객체를 리턴한다.
    public static BoardDAO getInstance(){
        return  new BoardDAO();
    }

    //최근 등록한 게시물이 먼저 나오도록 게시글 목록을 출력한다.
    public List<BoardVO> selectAllBoards(){
        Connection conn = DBManager.getConnect();
        List<BoardVO> boardVOList = new ArrayList<>(); //null로 초기화하면 안됨!
//        BoardVO bVo = new BoardVO(); 여기서 초기화하면 모든 레코드가 같은 BoardVO 객체를 참조하게 됩니다. 따라서 마지막 레코드의 값만이 반복적으로 출력됩니다.

   try{
       //sql문으로 게시글 목록 가져옴
       String sql ="select * from board order by writedate desc ";
       stmt = conn.prepareStatement(sql);
       rs =stmt.executeQuery();

       while (rs.next()){
           BoardVO bVo = new BoardVO(); //아 여기서 초기화를 해야되는구나!!!!
           bVo.setNum(rs.getInt("num"));
           bVo.setPass(rs.getString("pass"));
           bVo.setName(rs.getString("name"));
           bVo.setEmail(rs.getString("email"));
           bVo.setTitle(rs.getString("title"));
           bVo.setContent(rs.getString("content"));
           bVo.setReadcount(rs.getInt("readcount"));
           bVo.setWritedate(rs.getTimestamp("writedate"));

           boardVOList.add(bVo);

       }

   } catch (Exception e){
       e.printStackTrace();

   } finally {
       System.out.println("selectAllBoards 실행완료");
       DBManager.Close(conn, stmt,rs);
       System.out.println("selectAllBoards 실행 후 연결끊음");


   }

        return boardVOList;
    }

    //전달인자로 받은 VO 객체를 board 테이블에 삽입한다.
    public void insertBoard(BoardVO bVo){
        //디비 연결하고
        String sql ="insert into board (pass,name, email,title,content, writedate) values (?,?,?,?,?,?)";
        //set으로 테이블에 삽입
        Connection conn = DBManager.getConnect();
        try{
            stmt =conn.prepareStatement(sql);
            stmt.setString(1, bVo.getPass());
            stmt.setString(2, bVo.getName());
            stmt.setString(3, bVo.getEmail());
            stmt.setString(4, bVo.getTitle());
            stmt.setString(5, bVo.getContent());
            stmt.setTimestamp(6,bVo.getWritedate());

            stmt.executeQuery();



        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("insert완료");
            DBManager.Close(conn,stmt);
            System.out.println("insert 후 연결해제");

        }


    }
    //게시글 상세보기 할 때마다 글번호를 증가시킨다.
    public void updateReadCount(String num){
        //이코드도 상당히 반복되는데..

        Connection conn = DBManager.getConnect();


        //num 가져와야겠네
        System.out.println("num"+num);
        String selectSQL ="SELECT readcount FROM board WHERE num =?"; //num변수를 바인딩 변수로 넣어야겠지?
        //readcount 얻어오기
        //거기다가 1더하기 그값으로 넣기
        String UpdateSQL ="UPDATE board SET readcount = ? WHERE num = ? ";

        try{
           stmt = conn.prepareStatement(selectSQL);
           stmt.setString(1,num);
           rs =stmt.executeQuery();
            int readcount =0;
           while(rs.next()){
                readcount= rs.getInt("readcount") +1;

           }

           System.out.println("NEWreadcount" + readcount);
           //이제 구한 readcount
            //근데 연결 끊고 바로 쿼리문 날릴 수 있어?
            stmt = conn.prepareStatement(UpdateSQL);
            stmt.setString(1,String.valueOf(readcount));
            stmt.setString(2, num);

           stmt.executeUpdate();



        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("조회수 update 완료");
            DBManager.Close(conn,stmt,rs);
            System.out.println("조회수 update 후 연결해제");

        }


    }
    //board 테이블에서 게시글 번호로 해당 게시글을 찾아 게시글 정보를 BoardVO 객체로 넣어준다.
    public BoardVO selectOneBoardByNum (String num){

        Connection conn = DBManager.getConnect();
        String sql="select * from board where num= ?" ;
        BoardVO bVo = new BoardVO();
        try{
            stmt= conn.prepareStatement(sql);
            stmt.setString(1,num);
            rs = stmt.executeQuery();
            while (rs.next()){
                bVo.setNum(rs.getInt("num"));
                bVo.setPass(rs.getString("pass"));
                bVo.setName(rs.getString("name"));
                bVo.setEmail(rs.getString("email"));
                bVo.setTitle(rs.getString("title"));
                bVo.setContent(rs.getString("content"));
                bVo.setReadcount(rs.getInt("readcount"));
                bVo.setWritedate(rs.getTimestamp("writedate"));


            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("조회수 update 완료");
            DBManager.Close(conn,stmt,rs);
            System.out.println("조회수 update 후 연결해제");

        }

        return bVo;
    }

    //매개변수로 받은 VO객체 코드로 보드 테이블에서 검색해서 정보를 수정한다.
    public void updateBoard(BoardVO bVo){
        //그러면 받은 값이랑 다르면 업데이트해? 아니면 무조건 업데이트?

        Connection conn = DBManager.getConnect();
        String sql = "UPDATE board\n" +
                "SET pass=?,name =?,email=?, title=?, content = ?,writedate=?" +
                "WHERE num = ?";

       try{
           stmt = conn.prepareStatement(sql);
           stmt.setString(1,bVo.getPass());
           stmt.setString(2, bVo.getName());
           stmt.setString(3, bVo.getEmail());
           stmt.setString(4, bVo.getTitle());
           stmt.setString(5, bVo.getContent());
           stmt.setTimestamp(6, bVo.getWritedate());
           stmt.setInt(7,bVo.getNum()); //  값이 없을 텐데 에러가 안나네?
           System.out.println("bVo.getNum()"+bVo.getNum()); //bVo.getNum()0 오잉 0값으로 들어가네?

           stmt.executeUpdate();


       } catch (Exception e){
           e.printStackTrace();

       } finally {
           System.out.println("VO 받은 값으로 update 완료");
           DBManager.Close(conn,stmt);
           System.out.println("VO 받은 값으로 update 후 연결해제");

       }



    }

    //테이블에서 비번과 번호가 일치한 정보를 찾아서 VO객체로 리턴한다.
    public BoardVO checkPassword(String pass, String num){
        Connection conn = DBManager.getConnect();
        BoardVO vo = new BoardVO();
        String sql ="SELECT * FROM board WHERE pass = ? AND num = ?";
       try{
           stmt =conn.prepareStatement(sql);
           stmt.setString(1,pass);
           stmt.setInt(2,Integer.parseInt(num));
           rs = stmt.executeQuery();

           while (rs.next()){
               vo.setNum(rs.getInt("num"));
               vo.setPass(rs.getString("pass"));
               vo.setName(rs.getString("name"));
               vo.setEmail(rs.getString("email"));
               vo.setTitle(rs.getString("title"));
               vo.setContent(rs.getString("content"));
               vo.setReadcount(rs.getInt("readcount"));
               vo.setWritedate(rs.getTimestamp("writedate"));
           }


       } catch (Exception e){
           e.printStackTrace();

       }finally {
           System.out.println("checkPassword 완료");
           DBManager.Close(conn,stmt,rs);
           System.out.println("checkPassword 후 연결해제");

       }
        return vo;
    }

    //게시글 번호에 해당하는 테이블 정보 삭제한다
    public void deleteBoard(String num){
        Connection conn = DBManager.getConnect();
        String sql ="delete from board where num =?";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,num);
            stmt.executeQuery();

        } catch(Exception e){
            e.printStackTrace();

        }
        finally {
            System.out.println("deleteBoard 완료");
            DBManager.Close(conn,stmt);
            System.out.println("deleteBoard 후 연결해제");

        }




    }






}
