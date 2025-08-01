/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class Order {
    private String Id;
    private String MaMay;
    private String TenMay;
    private String MaMon;
    private String TenMon;
    private float Gia;
    private int SoLuong;
    private float TongTien;

    public Order() {}

    public Order( String MaMay, String TenMay, String MaMon, String TenMon, float Gia, int SoLuong, float TongTien) {
        this.MaMay = MaMay;
        this.TenMay = TenMay;
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.TongTien = TongTien;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMaMay() {
        return MaMay;
    }

    public void setMaMay(String MaMay) {
        this.MaMay = MaMay;
    }

    public String getTenMay() {
        return TenMay;
    }

    public void setTenMay(String TenMay) {
        this.TenMay = TenMay;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    

   
}

