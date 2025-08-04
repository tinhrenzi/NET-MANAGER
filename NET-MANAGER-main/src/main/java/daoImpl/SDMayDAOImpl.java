/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.SDMayDAO;
import entity.SuDungMay;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author NITRO 5
 */
public class SDMayDAOImpl implements SDMayDAO{
    private Connection conn = XJdbc.openConnection();
    String Sql_MoMay = "Update SDMAY set TrangThai =?, NgayChoi=? ,NgayKetThuc=? , GioBatDau=? ,GioKetThuc=?,TongTien=?  where MaMay =?";
    String Sql_TatMay = "Update SDMAY set TrangThai =?, NgayChoi=null, NgayKetThuc = null, GioBatDau=null ,GioKetThuc=null,TongTien =?  where MaMay =?";
    String Sql_Sle ="SELECT s.MaMay,m.TenMay ,s.TrangThai , s.NgayChoi,s.NgayKetThuc , s.GioBatDau , s.GioKetThuc , s.GiaTheoGio , s.TongTien "
            + "FROM SDMAY s JOIN MayTinh m ON s.MaMay = m.Id";
    @Override
    public void MoMay(SuDungMay SDM) {
        Object[] args = {
            SDM.getTrangThai(),
            SDM.getNgayChoi(),
            SDM.getNgayKetThuc(),
            SDM.getGioBatDau(),
            SDM.getGioKetThuc(),
            SDM.getTongTien(),
            SDM.getMaMay()
        };
        XJdbc.executeUpdate(Sql_MoMay, args);
    }

    @Override
    public void TatMay(SuDungMay SDM) {
    Object[] args = {
        SDM.getTrangThai(),
        SDM.getTongTien(),
        SDM.getMaMay()
    };
    XJdbc.executeUpdate(Sql_TatMay, args);
    }

    @Override
    public SuDungMay create(SuDungMay entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SuDungMay entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SuDungMay> findAll() {
    return XQuery.getBeanList(SuDungMay.class, Sql_Sle);
    }

    @Override
    public SuDungMay findByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
