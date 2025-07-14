/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import ui.manager.QuanLyNhanVienJDialog;
import ui.manager.QuanLyThucDonJDialog;
import util.XDialog;

/**
 *
 * @author VINH
 */
public interface GiaoDienChinhController {
    void init();
    
    default void exit(){
        if (XDialog.confirm("Ban xac nhan thoat")) {
            System.exit(0);
        }
    }
    
    default void showDialog(JDialog dialog){
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    default void showNhanVien(JFrame jFrame){
        this.showDialog(new QuanLyNhanVienJDialog(jFrame, true));
    }
    
    default void showDichVu(JFrame jFrame){
        this.showDialog(new QuanLyThucDonJDialog(jFrame, true));
    }
    
    default void showThongTin(){
        
    }
    
    default void showTaiKhoan(){
        
    }
    
}
