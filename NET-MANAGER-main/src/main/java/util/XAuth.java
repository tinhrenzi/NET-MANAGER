/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import entity.Users;

/**
 *
 * @author Baoau
 */
public class XAuth {
      public static Users user;

    static {
        user = new Users();
        user.setTenDangNhap("bao1");
        user.setMatKhau("123");
        user.setEnable(true);
        user.setVaiTro(1);
        user.setEmail("bao@gmail.com");
        user.setSoDienThoai("0123456789");
        user.setNgayTaoUser(new java.util.Date());
    }
}
