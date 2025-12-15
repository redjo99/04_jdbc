package org.ho.section03.delete;

import org.ho.common.JDBCTemplate;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

import static org.ho.common.JDBCTemplate.*;

public class MenuRepository {
  public int removeMenu(Connection conn,int menuCode) {
    Properties prop = new Properties();
    PreparedStatement pstmt = null;
    int result;
    try {
      prop.loadFromXML(new FileInputStream("src/main/java/org/ho/mapper/MenuMapper.xml"));

      String deleteSQL = prop.getProperty("deleteMenu");

      pstmt = conn.prepareStatement(deleteSQL);
      pstmt.setInt(1, menuCode);

      result = pstmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
    }
    return result;
  }
}
