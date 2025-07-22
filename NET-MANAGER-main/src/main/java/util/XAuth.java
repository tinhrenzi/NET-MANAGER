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
    public static Users user = null; // ban đầu null

    public static boolean isLogin() {
        return user != null;
    }

    public static boolean isQuanLy() {
        return isLogin() && user.getVaiTro() == 1;
    }

    public static boolean isNhanVien() {
        return isLogin() && user.getVaiTro() == 0;
    }

    public static void clear() {
        user = null;
    }
}