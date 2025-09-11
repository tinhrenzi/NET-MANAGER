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

    void UpSoluong(Menu entity);

    Menu Mua(Menu order);

    Menu findById(int id);

    List<Menu> findByMay(String maSDMay);

    List<Menu> findByIdSD(String maSDMay);

    List<Menu> findAll();
}
