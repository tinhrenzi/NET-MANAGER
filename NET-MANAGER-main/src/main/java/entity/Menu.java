/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;


public class Menu {
    int id;
    int MaSDMay;
    private String TenMay;
    private String maMon;
    private String tenMon;
    private double giaTien;
    private Date NgayMua;
    private int soLuong;
    private double tongTien;

    // Constructors
    public Menu() {}

    public Menu(int id, int MaSDMay, String TenMay, String maMon, String tenMon, double giaTien, Date NgayMua, int soLuong, double tongTien) {
        this.id = id;
        this.MaSDMay = MaSDMay;
        this.TenMay = TenMay;
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.giaTien = giaTien;
        this.NgayMua = NgayMua;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaSDMay() {
        return MaSDMay;
    }

    public void setMaSDMay(int MaSDMay) {
        this.MaSDMay = MaSDMay;
    }

    public String getTenMay() {
        return TenMay;
    }

    public void setTenMay(String TenMay) {
        this.TenMay = TenMay;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public Date getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(Date NgayMua) {
        this.NgayMua = NgayMua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    

}
