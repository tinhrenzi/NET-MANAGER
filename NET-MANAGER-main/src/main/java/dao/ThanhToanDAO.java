/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.MayTinh;
import entity.SuDungMay;
import entity.ThanhToan;

/**
 *
 * @author VINH
 */
public interface ThanhToanDAO{// Khóa chính là kiểu Nvarchar nên để String
    ThanhToan ThanhToan(ThanhToan tt);
    void TatMay(SuDungMay SDM,MayTinh mt);
}
