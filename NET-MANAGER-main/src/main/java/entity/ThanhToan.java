/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.time.LocalDateTime;

public class ThanhToan {
    private int MaSDMay;
    private String TenMay;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private double tongGio;
    private double giaTheoGio;
    private LocalDateTime NgayThanhToan;
    private double tienMay;
    private double tienMenu;
    private double tongTien;


    public  ThanhToan(){}

    public ThanhToan(int MaSDMay, String TenMay, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, double tongGio, double giaTheoGio, double tienMay, double tienMenu, double tongTien) {
        this.MaSDMay = MaSDMay;
        this.TenMay = TenMay;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tongGio = tongGio;
        this.giaTheoGio = giaTheoGio;
        this.tienMay = tienMay;
        this.tienMenu = tienMenu;
        this.tongTien = tongTien;
    }

    public String getTenMay() {
        return TenMay;
    }

    public void setTenMay(String TenMay) {
        this.TenMay = TenMay;
    }
    public int getMaSDMay() {
        return MaSDMay;
    }

    public void setMaSDMay(int MaSDMay) {
        this.MaSDMay = MaSDMay;
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

    public LocalDateTime getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
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



