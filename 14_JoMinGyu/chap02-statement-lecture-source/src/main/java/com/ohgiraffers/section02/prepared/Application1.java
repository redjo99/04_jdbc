package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.ohgiraffers.common.JDBCTemplate.*;

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
public class Application1 {

  public static void main(String[] args) {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    try {
      con = getConnection();

      // placeholder(?)를 포함한 SQL
      String sql = "select * from tbl_menu where menu_code = ?";

      // PreparedStatement 객체가 만들어지는 시점에
      // 이미 SQL이 DBMS에 전달되어 있다.
      pstmt = con.prepareStatement(sql);

      // ?에 알맞는 값 세팅하기
      pstmt.setInt(1, 16);

      // 실행 후 결과(ResultSet 반환 받기)
      // **중요**
      // executeQuery()에 전달 인자가 없는 이유
      // -> 이미 SQL 전달하고, ?에 값이 세팅 완료된 상태니까
      //    SQL을 수행만 시키면 되기 때문이다!
      rset = pstmt.executeQuery();

      // 조희 결과가 있다면
      if(rset.next()) {
        System.out.println(
            rset.getInt("menu_code")
            + " / "
            + rset.getString("menu_name")
        );
      } else {
        System.out.println("일치하는 메뉴 없음");
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
