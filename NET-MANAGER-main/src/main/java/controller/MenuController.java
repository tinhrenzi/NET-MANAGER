/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.Menu;
import entity.MonAn;

/**
 *
 * @author NITRO 5
 */
public interface MenuController{ // Kế thừa CrudController để thực hiện các chức năng bên trong
    void filltblDaNMua();
    Menu getFromDaMua();
    void SuaSoLuong();
}
