/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.MayTinh;
import entity.SuDungMay;

/**
 *
 * @author NITRO 5
 */
public interface MoMayController {
    void FilltblMayTinh();
    void FilltblSDMay();
    SuDungMay getFromOne();
    SuDungMay getFromBytoShutdow();
    MayTinh getFromTwo();
    void MoMay();
    void TatMay();
    void Clear();
    void DongHo();
    void TatDongHo();
    void NgayHienTai();

}
