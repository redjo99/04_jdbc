package org.ho.section03.delete;

import java.util.Scanner;

public class Application {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("삭제할 메뉴 코드 입력 : ");
    int menuCode = sc.nextInt();

    MenuRepository menuRepository=new MenuRepository();
    MenuService menuService = new MenuService(menuRepository);
    int result =menuService.removeMenu(menuCode);

    if(result>0){
      System.out.println(menuCode+"번 메뉴가 삭제 되었습니다.");
    }else{
      System.out.println("메뉴 코드가 일치하는 메뉴가 없습니다.");
    }
  }
}
