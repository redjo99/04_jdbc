package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* properties 파일 내용을 읽어와 DB와 연결할 수 있다 */
public class Application2 {

  public static void main(String[] args) {

    Properties prop = new Properties();
    Connection con = null;

    try {
      prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

      // properties 파일에서 읽어온 데이터를 Key를 통해 얻어옴
      String url = prop.getProperty("url");
      String user = prop.getProperty("user");
      String password = prop.getProperty("password");

      con = DriverManager.getConnection(url, user, password);
      System.out.println("con = " + con);

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
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
