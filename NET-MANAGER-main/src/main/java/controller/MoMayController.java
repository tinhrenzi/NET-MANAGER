/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.MayTinh;

/**
 *
 * @author VINH
 */
public interface MoMayController extends crudController<MayTinh>{
    void open();
    void close();
    void exit();
}
