/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import ui.DangNhapJDialog;
import ui.manager.DanhSachDoAnJDialog;
import ui.manager.DanhSachDoUongJDialog;


/**
 *
 * @author Baoau
 */
public interface QuanLyThucDonController {
    void init();
    default void showDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showDanhSachDoAn(JFrame jFrame) {
        this.showDialog(new DanhSachDoAnJDialog(jFrame, true));
    }
        default void showDanhSachDoUong(JFrame jFrame) {
        this.showDialog(new DanhSachDoUongJDialog(jFrame, true));
    }

}
