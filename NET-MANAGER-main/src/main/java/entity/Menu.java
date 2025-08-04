/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;


public class Menu {
    private int maMay;
    private int maMon;
    private String tenMon;
    private double giaTien;
    private Date NgayMua;
    private int soLuong;
    private double tongTien;

    // Constructors
    public Menu() {
    }

    public Menu(int maMay, int maMon, String tenMon, double giaTien, Date NgayMua, int soLuong, double tongTien) {
        this.maMay = maMay;
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.giaTien = giaTien;
        this.NgayMua = NgayMua;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }



    // Getters and Setters
 


    public int getMaMay() {
        return maMay;
    }

    public void setMaMay(int maMay) {
        this.maMay = maMay;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
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

    public Date getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(Date NgayMua) {
        this.NgayMua = NgayMua;
    }

    
}
