package org.ho.section03.delete;

import org.ho.common.JDBCTemplate;

import java.sql.Connection;

public class MenuService {
  private MenuRepository menuRepository;

  public MenuService(MenuRepository menuRepository) {
    this.menuRepository = menuRepository;
  }

  public int removeMenu(int menuCode) {
    int result;

    Connection conn = JDBCTemplate.getConnection();

    result =menuRepository.removeMenu(conn,menuCode);

    if(result>0) JDBCTemplate.commit(conn);
    else JDBCTemplate.rollback(conn);

    return result;
  }
}
