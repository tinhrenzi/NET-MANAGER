/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Order;
import java.awt.Menu;
import java.util.List;

/**
 *
 * @author VINH
 */
public interface orderDAO extends crudDAO<Order, String>{// Khóa chính là kiểu Nvarchar nên để String
    List<Order> getAllOrders();
    void insertOrder(Order order);
    void deleteOrder(String id);
    void updateOrder(Order order);
}
