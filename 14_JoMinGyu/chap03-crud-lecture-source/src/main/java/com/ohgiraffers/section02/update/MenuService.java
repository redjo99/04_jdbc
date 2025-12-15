package com.ohgiraffers.section02.update;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

/* Service 계층
* - 비즈니스 로직(데이터 가공, 트랜잭션 처리, Repository 호출)을 담당하는 계층
* */
public class MenuService {

  public int modifyMenu(Menu modifyMenu) {
    Connection con = getConnection();

    MenuRepository menuRepository = new MenuRepository();

    // repository 메서드를 호출하여 결과(1:성공, 0:실패) 반환받기
    int result = menuRepository.updateMenu(con, modifyMenu);

    if(result > 0) commit(con);
    else           rollback(con);

    return result;
  }

}
