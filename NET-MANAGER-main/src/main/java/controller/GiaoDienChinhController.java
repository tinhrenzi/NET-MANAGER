/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import ui.DangNhapJDialog;
import ui.WelcomeJDialog;
import ui.manager.QuanLyNhanVienJDialog;
import ui.manager.QuanLyThongKeJDialog;
import ui.manager.QuanLyThucDonJDialog;
import ui.manager.ThanhToanJDialog;
import ui.manager.*;
import util.XDialog;

/**
 *
 * @author VINH
 */
public interface GiaoDienChinhController {

    void init();

    default void exit() {
        if (XDialog.confirm("Ban xac nhan thoat")) {
            System.exit(0);
        }
    }

    default void showDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showWelcome(JFrame jFrame) {
        this.showDialog(new WelcomeJDialog(jFrame, true));
    }

    default void showLogin(JFrame jFrame) {
        this.showDialog(new DangNhapJDialog(jFrame, true));
    }

    default void showNhanVien(JFrame jFrame) {
        this.showDialog(new QuanLyNhanVienJDialog(jFrame, true));
    }

    default void showDichVu(JFrame jFrame) {
        this.showDialog(new ThucDonJDialog(jFrame, true));
    }

    default void showQLOrder(JFrame jFrame) {
        this.showDialog(new QuanLyThucDonJDialog(jFrame, true));
    }

    default void showQLThongke(JFrame jFrame) {
        this.showDialog(new QuanLyThongKeJDialog(jFrame, true));
    }

    default void showQLmay(JFrame jFrame) {
    }

    default void showThanhtoan(JFrame jFrame) {
        this.showDialog(new ThanhToanJDialog(jFrame, true));
    }

    default void showMoMay(JFrame jFrame) {
        this.showDialog(new MoMayJDialog(jFrame, true));
    }

}
