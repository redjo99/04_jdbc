package org.ho.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* 반복되는 JDBC 관련 코드를 미리 작성해서
   Util용으로 사용할 JDBCTemplate 클래스를 만들 수 있다. */
public class JDBCTemplate {
  public static Connection getConnection() {
    Properties prop = new Properties();
    Connection conn = null;

    try {
      prop.load(new FileReader("src/main/java/org/ho/section01/connection/jdbc-config.properites"));

      // properties 파일에서 읽어온 데이터를 key를 통해 얻어옴
      String url = prop.getProperty("url"); //url스킴으로 mysql인지 확인
      String user = prop.getProperty("user");
      String password = prop.getProperty("password");

      conn = DriverManager.getConnection(url, user, password);
      System.out.println("conn = " + conn); //객체가 잘 생성되었다면, 주소 출력
    } catch (SQLException e) {
      throw new RuntimeException(e);  // UnCheckedException = 꼭 예외처리 구문을 안써줘도 된다
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return conn;
  }

  /**
   * Connection을 닫는 메서드
   * - 메모리 자원 반환
   * - DBMS 연결된 커넥션 제거
   * @param conn
   */
  public static void close(Connection conn){
    try {
      if(conn!=null && !conn.isClosed())conn.close();
      // 만약 connection을 close 안해주게 된다면
      // java Application과 DBMS를 연결하는 connection이
      // java가 종료되더라도 유지되게 된다.
      // => 그러면 메모리가 누수와 같이 자원(메모리,커넥션)을 낭비하게 된다.
    } catch (SQLException e) {
      // CheckedException인 SQLException을 UnCheckedException인 RuntimeException으로 바꿔서 프로그램의 강제 종료를 막는다.
      throw new RuntimeException(e);
    }
  }
}
