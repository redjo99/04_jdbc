package org.ho.section02.update;


import java.util.Scanner;

public class Application {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("변경할 메뉴 번호 : ");
    int menuCode = sc.nextInt();
    System.out.print("변경할 메뉴 이름 : ");
    sc.nextLine();
    String menuName = sc.nextLine();
    System.out.print("변경할 메뉴 가격 : ");
    int menuPrice = sc.nextInt();

    Menu modifyMenu = new Menu(menuCode, menuPrice, menuName);

    MenuService menuService = new MenuService();

    int result = menuService.modifyMenu(modifyMenu);

    if(result>0) System.out.println(menuCode+"번 메뉴가 수정되었습니다.");
    else System.out.println("메뉴코드가 잘못 입력 되었습니다.");
  }
}