package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application1 {

  public static void main(String[] args) {

    Connection con = null; // DB 연결 객체
    Statement stmt = null; // SQL 전달 후 수행 결과 받아오는 객체
    ResultSet rset = null; // SELECT 수행 결과를 얻어와 저장하는 객체
    
    try{
      con = getConnection();

      /* Statement : 쿼리를 운반하고 결과를 반환하는 객체 */
      stmt = con.createStatement();

      String sql = "select * from tbl_menu";

      // executeQuery() : SELECT 수행 후 ResultSet 반환
      rset = stmt.executeQuery(sql);


    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally{
      
    }

  }
}
