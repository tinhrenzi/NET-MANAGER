/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import ui.manager.ThanhToanJDialog;

/**
 *
 * @author VINH
 */
public interface MayTinhController {
        default void showDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
        default void showThanhtoan(JFrame jFrame) {
        this.showDialog(new ThanhToanJDialog(jFrame, true));
    }
}
