package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

  public static void main(String[] args) {

    Connection con = null; // DB 연결 객체
    PreparedStatement pstmt = null; // SQL 전달 후 수행 결과 받아오는 객체
    ResultSet rset = null; // SELECT 수행 결과를 얻어와 저장하는 객체
    try {
      con = getConnection();

      int menuCode = 16;
      String menuName = "' or 1=1 and menu_code = '2";

      String sql = "select * from tbl_menu where menu_code = ? and menu_name = ?";

      /* Statement : 쿼리를 운반하고 결과를 반환하는 객체 */
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1, menuCode);
      pstmt.setString(2, menuName);


      // executeQuery() : SELECT 수행 후 ResultSet 반환
      /* ResultSet : SELECT 결과를 다루는 객체(1행씩) */
      rset = pstmt.executeQuery();

      while(rset.next()) { // 다음 행이 있다면 이동하고, true 반환(반복)

        // 한 행에서 특정 컬럼 값을 자바 자료형에 맞춰 얻어오기
        System.out.println("menu_code : " + rset.getInt("menu_code"));
        System.out.println("menu_name : " + rset.getString("menu_name"));
        System.out.println("menu_price : " + rset.getInt("menu_price"));
        System.out.println("category_code : " + rset.getInt("category_code"));
        System.out.println("orderable_status : " + rset.getString("orderable_status"));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {

      // 프로그램 안정성을 위해 JDBC 객체를 생성 역순으로 반환
      close(rset);
      close(pstmt);
      close(con);
    }
  }

}
