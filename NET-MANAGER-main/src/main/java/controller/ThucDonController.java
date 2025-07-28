/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import dao.orderDAO;
import daoImpl.OrderDAOImpl;
import entity.DoAn;
import entity.DoUong;
import entity.Order;
import java.util.List;

/**
 *
 * @author VINH
 */
public class ThucDonController {
    private orderDAO menuDAO;

    public ThucDonController() {
        menuDAO = new OrderDAOImpl();
    }

    public List<DoAn> layDoAn() {
        return menuDAO.getAllDoAn();
    }

    public List<DoUong> layNuocUong() {
        return menuDAO.getAllNuocUong();
    }

    public void luuMon(String MaMay, Order mon) {
        menuDAO.themMon(MaMay, mon);
    }
}

