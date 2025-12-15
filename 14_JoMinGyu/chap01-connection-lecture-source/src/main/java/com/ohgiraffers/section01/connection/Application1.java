package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {
  public static void main(String[] args) {
    /* Connection
    * - DB 연결 정보를 저장하고
    *   Java application과 DBMS를 연결해주는 객체
    * */
    Connection con = null;

    try{

      // DriverManager를 이용하여 Connection 객체 생성
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306",
          "swcamp",
          "swcamp"
      );

      System.out.println("con = " + con);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
