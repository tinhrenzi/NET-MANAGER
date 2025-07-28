/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.DoAn;
import entity.DoUong;
import entity.Order;
import java.awt.Menu;
import java.util.List;

/**
 *
 * @author VINH
 */
public interface orderDAO {
    List<DoAn> getAllDoAn();
    List<DoUong> getAllNuocUong();
    void themMon(String MaMay, Order mon);
}
