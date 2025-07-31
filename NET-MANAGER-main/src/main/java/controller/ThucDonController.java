/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import daoImpl.OrderManagerDAOImpl;
import entity.DoAn;
import entity.DoUong;
import entity.OrderManager;
import java.util.List;
import dao.orderManagerDAO;

/**
 *
 * @author VINH
 */
public class ThucDonController {
    private orderManagerDAO menuDAO;

    public ThucDonController() {
        menuDAO = new OrderManagerDAOImpl();
    }
}

