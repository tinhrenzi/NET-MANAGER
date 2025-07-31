/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Order;
import java.util.List;

/**
 *
 * @author Baoau
 */
public interface OrderDAO {
    Order insert(Order order);
    List<Order> findAll();
    List<Order> findByMay(String maMay);aaaa
}
