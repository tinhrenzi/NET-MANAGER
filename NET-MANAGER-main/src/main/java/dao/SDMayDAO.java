/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

import entity.MayTinh;
import entity.SuDungMay;

/**
 *
 * @author NITRO 5
 */
public interface SDMayDAO {

    void MoMay(SuDungMay SDM, MayTinh mt);

    void TatMay(SuDungMay SDM, MayTinh mt);

    List<SuDungMay> findAll();

    List<MayTinh> finMayTinh();

    boolean isMayDangHoatDong(String tenMay); // Thêm phương thức mới
}
