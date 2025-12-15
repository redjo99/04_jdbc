package org.ho.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.ho.common.JDBCTemplate.*;
public class Application1 {
  public static void main(String[] args) {

    Connection conn = null; //1.DB 연결 객체
    Statement stmt = null;  //2.SQL 전달 후 수행 결과 받아오는 객체
    ResultSet rset = null;  //3.SELECT 수행 결과를 얻어와 저장하는 객체
    try{
      conn = getConnection();

      /* Statement : 쿼리를 운반하고 결과를 반환하는 객체 */
      stmt = conn.createStatement();

      String sql = "select * from tbl_menu";

      // executeQuery() : SELECT 수행 후 ResultSet 반환
      /* ResultSet : SELECT 결과를 다루는 객체(1행씩) */
      rset = stmt.executeQuery(sql);
      while(rset.next()) { // 다음 행이 있다면 이동하고 true 반환(반복)


        // 한 행에서 특정 컬럼 값을 자바 자료형에 맞춰 얻어오기
        System.out.printf("menu_code : %d \n",rset.getInt("menu_code"));
        System.out.printf("menu_name : %s \n",rset.getString("menu_name"));
        System.out.printf("menu_price : %d \n",rset.getInt("menu_price"));
        System.out.printf("category_code : %d \n",rset.getInt("category_code"));
        System.out.printf("orderable_status : %s \n",rset.getString("orderable_status"));
        System.out.println("----------------------------------------------------------");
      }

    }catch(SQLException e){
      throw new RuntimeException(e);
    }finally {
      // 프로그램 안정성을 위해 JDBC 객체를 생성 역순으로 반환
      close(rset);
      close(stmt);
      close(conn);
    }

  }
}
