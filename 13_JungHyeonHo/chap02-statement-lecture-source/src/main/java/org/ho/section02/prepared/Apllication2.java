package org.ho.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.ho.common.JDBCTemplate.*;
/*
PreparedStatement(준비된 Statement)
- Statement에 SQL을 미리 준비 시켜 놓고 ?(placeholder) 자리에 알맞은 값을 세팅 후 수행하는 객체
- Statement의 자식 인터페이스
### 장점
1. SQL Injection 방지
2. SQL 조합이 간단
3. 속도가 빠름(DBMS 캐싱)
 */
public class Apllication1 {
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    try {
      conn = getConnection();
      // placeholder(?)를 통해 간단하게 기본 쿼리문 작성. ?에 값 대입 예정.
      String sql = "select * from tbl_menu where menu_code = ?";

      // PreparedStatement 객체가 만들어지는 시점에
      // SQL이 DBMS에 전달된다.
      pstmt=conn.prepareStatement(sql);

      // ? 에 알맞는 값 세팅하기
      pstmt.setInt(1,16);

      // 실행 후 결과(ResultSet 반환 받기)
      // **중요**
      // executeQuery()에 전달 인자가 없는 이유
      // -> 이미 SQL 전달하고, ?에 값이 세팅 완료된 상태니까
      //    SQL을 수행만 시키면 되기 때문이다!
      rset = pstmt.executeQuery();

      if(rset.next()){
        System.out.println(rset.getInt(1)
            +" / "
            +rset.getString(2));
      }else{
        System.out.println("일치하는 메뉴 없음");
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      close(rset);
      close(pstmt);
      close(conn);
    }
  }
}
