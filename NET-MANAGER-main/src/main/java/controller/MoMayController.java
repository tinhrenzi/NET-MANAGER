/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.MayTinh;
import javax.swing.JFrame;

/**
 *
 * @author VINH
 */
public interface MoMayController extends crudController<MayTinh> {

    void open();

// Sửa trong interface
default void close(java.awt.Window currentWindow, java.awt.Window mainWindow) {
    currentWindow.dispose();
    mainWindow.setVisible(true);
}
         

}
