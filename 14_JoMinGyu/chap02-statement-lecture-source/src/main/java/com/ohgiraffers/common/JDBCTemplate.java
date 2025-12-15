package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/* 반복되는 JDBC 관련 코드를 미리 작성해서 Util용으로 사용할
  JDBCTemplate 클래스를 만들 수 있다 */
public class JDBCTemplate {

  public static Connection getConnection() {

    Properties prop = new Properties();
    Connection con = null;

    try {
      prop.load(new FileReader("src/main/java/com/ohgiraffers/config/jdbc-config.properties"));

      // properties 파일에서 읽어온 데이터를 Key를 통해 얻어옴
      String url = prop.getProperty("url");
      String user = prop.getProperty("user");
      String password = prop.getProperty("password");

      con = DriverManager.getConnection(url, user, password);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return con;
  }

  /**
   * Connection을 닫는 메서드
   * - 메모리 자원 반환
   * - DBMS에 연결된 커넥션 제거
   * @param con
   */
  public static void close(Connection con) {
    try {
      if(con != null && !con.isClosed()) con.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(Statement stmt) {
    try {
      if(stmt != null && !stmt.isClosed()) stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(ResultSet rset) {
    try {
      if(rset != null && !rset.isClosed()) rset.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
}
