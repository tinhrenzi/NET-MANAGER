/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Menu;
import java.util.List;

/**
 *
 * @author VINH
 */
public interface MenuDAO {
Menu insert(Menu order);
    List<Menu> findAll();
    List<Menu> findByMay(String maMay);
    List<Menu> findById(String MaMenu);
}
