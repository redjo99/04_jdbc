package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* JDBCTemplate 클래스 내 static 메서드 호출 시 클래스명을 생략할 수 있게 함 */
import static com.ohgiraffers.section02.template.JDBCTemplate.*;

/* properties 파일 내용을 읽어와 DB와 연결할 수 있다 */
public class Application {

  public static void main(String[] args) {
    
    Connection con = null;

    try {
      con = getConnection();
      System.out.println("con = " + con);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(con);
    }

  }
}
