/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import dao.MonAnDAO;
import daoImpl.MonAnDAOImpl;

/**
 *
 * @author VINH
 */
public class ThucDonController {
    private MonAnDAO menuDAO;

    public ThucDonController() {
        menuDAO = new MonAnDAOImpl();
    }
}

