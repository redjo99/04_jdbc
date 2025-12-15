package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

  public static void main(String[] args) {

    Connection con = null; // DB 연결 객체
    Statement stmt = null; // SQL 전달 후 수행 결과 받아오는 객체
    ResultSet rset = null; // SELECT 수행 결과를 얻어와 저장하는 객체

    Scanner sc = new Scanner(System.in);
    System.out.println("조회할 메뉴 코드 입력 : ");
    int menuCode = sc.nextInt();

    try {
      con = getConnection();

      /* Statement : 쿼리를 운반하고 결과를 반환하는 객체 */
      stmt = con.createStatement();

      String sql = "select * from tbl_menu where menu_code = " + menuCode;

      // executeQuery() : SELECT 수행 후 ResultSet 반환
      /* ResultSet : SELECT 결과를 다루는 객체(1행씩) */
      rset = stmt.executeQuery(sql);

      if(rset.next()) { // 다음 행이 있다면 true 반환

        // 한 행에서 특정 컬럼 값을 자바 자료형에 맞춰 얻어오기
        System.out.println("menu_code : " + rset.getInt("menu_code"));
        System.out.println("menu_name : " + rset.getString("menu_name"));
        System.out.println("menu_price : " + rset.getInt("menu_price"));
        System.out.println("category_code : " + rset.getInt("category_code"));
        System.out.println("orderable_status : " + rset.getString("orderable_status"));
      } else {
        System.out.println("해당 메뉴 코드와 일치하는 메뉴가 없습니다.");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {

      // 프로그램 안정성을 위해 JDBC 객체를 생성 역순으로 반환
      close(rset);
      close(stmt);
      close(con);
    }
  }

}
