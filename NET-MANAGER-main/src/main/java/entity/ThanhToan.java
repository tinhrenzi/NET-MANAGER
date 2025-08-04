/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.time.LocalDateTime;

public class ThanhToan {
    private int maMay;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private double tongGio;
    private double giaTheoGio;
    private double tienMay;
    private double tienMenu;
    private double tongTien;


    public  ThanhToan(){}

    public ThanhToan(int maMay, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, double tongGio, double giaTheoGio, double tienMay, double tienMenu, double tongTien) {
        this.maMay = maMay;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tongGio = tongGio;
        this.giaTheoGio = giaTheoGio;
        this.tienMay = tienMay;
        this.tienMenu = tienMenu;
        this.tongTien = tongTien;
    }


    public int getMaMay() {
        return maMay;
    }

    public void setMaMay(int maMay) {
        this.maMay = maMay;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getTongGio() {
        return tongGio;
    }

    public void setTongGio(double tongGio) {
        this.tongGio = tongGio;
    }

    public double getGiaTheoGio() {
        return giaTheoGio;
    }

    public void setGiaTheoGio(double giaTheoGio) {
        this.giaTheoGio = giaTheoGio;
    }

    public double getTienMay() {
        return tienMay;
    }

    public void setTienMay(double tienMay) {
        this.tienMay = tienMay;
    }

    public double getTienMenu() {
        return tienMenu;
    }

    public void setTienMenu(double tienMenu) {
        this.tienMenu = tienMenu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
}



