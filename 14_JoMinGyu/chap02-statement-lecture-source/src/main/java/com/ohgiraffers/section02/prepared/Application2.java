package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

/* PreparedStatement(준비된 Statement)
* - Statement에 SQL을 미리 준비시켜 놓고
*   ?(위치 홀더, placeholder) 자리에 알맞은 값을 세팅 후 수행하는 객체
*
* - Statement의 자식 인터페이스
* - 장점
*   1) SQL Injection 방지
*   2) SQL 조합이 간단함
*   3) 속도가 빠름(DBMS 캐싱)
* */
public class Application2 {

  public static void main(String[] args) {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    Scanner sc = new Scanner(System.in);

    System.out.println("메뉴 카테고리 번호 입력 : ");
    int categoryCode = sc.nextInt();
    sc.nextLine();
    System.out.println("금액 입력 : ");
    int menuPrice = sc.nextInt();
    sc.nextLine();

    try {
      con = getConnection();

      // placeholder(?)를 포함한 SQL
      String sql = "select * from tbl_menu"
         + " where category_code = ? "
          + " and menu_price >= ? ";

      // PreparedStatement 객체가 만들어지는 시점에
      // 이미 SQL이 DBMS에 전달되어 있다.
      pstmt = con.prepareStatement(sql);

      // ?에 알맞는 값 세팅하기
      pstmt.setInt(1, categoryCode);
      pstmt.setInt(2, menuPrice);

      // 실행 후 결과(ResultSet 반환 받기)
      rset = pstmt.executeQuery();

      // 조희 결과가 있다면
      while(rset.next()) {
        System.out.println(
            rset.getInt("menu_price")
            + " / "
            + rset.getString("menu_name")
        );
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      close(rset);
      close(pstmt);
      close(con);
    }

  }

}
