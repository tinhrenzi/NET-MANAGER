/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class Order {
      private String maMay;
    private String tenMay;
    private String maMon;
    private String tenMon;
    private float gia;
    private int soLuong;
    private float tongTien;

    public Order() {
    }

    public Order(String maMay, String tenMay, String maMon, String tenMon, float gia, int soLuong, float tongTien) {
    this.maMay = maMay;
    this.tenMay = tenMay;
    this.maMon = maMon;
    this.tenMon = tenMon;
    this.gia = gia;
    this.soLuong = soLuong;
    this.tongTien = tongTien;
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getTenMay() {
        return tenMay;
    }

    public void setTenMay(String tenMay) {
        this.tenMay = tenMay;
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

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
    public int getSoLuong() {
    return soLuong;
    }

    public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
    }
    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

   
}

