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

    public static void clear() {
        user = null;
    }
}