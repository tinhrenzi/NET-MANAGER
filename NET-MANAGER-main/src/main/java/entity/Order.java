/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class Order {
    private String id;
    private String MaDoAn;
    private String TenDoAn;
    private Float DonGiaDoAn;
    private int soLuongDoAn;

    private String MaDoUong;
    private String tenDoUong;
    private Float donGiaDoUong;
    private int soLuongDoUong;

    public Order() {}

    public Order(String id, String MaDoAn, String TenDoAn, Float DonGiaDoAn, int soLuongDoAn, String MaDoUong, String tenDoUong, Float donGiaDoUong, int soLuongDoUong) {
        this.id = id;
        this.MaDoAn = MaDoAn;
        this.TenDoAn = TenDoAn;
        this.DonGiaDoAn = DonGiaDoAn;
        this.soLuongDoAn = soLuongDoAn;
        this.MaDoUong = MaDoUong;
        this.tenDoUong = tenDoUong;
        this.donGiaDoUong = donGiaDoUong;
        this.soLuongDoUong = soLuongDoUong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDoAn() {
        return MaDoAn;
    }

    public void setMaDoAn(String MaDoAn) {
        this.MaDoAn = MaDoAn;
    }

    public String getTenDoAn() {
        return TenDoAn;
    }

    public void setTenDoAn(String TenDoAn) {
        this.TenDoAn = TenDoAn;
    }

    public Float getDonGiaDoAn() {
        return DonGiaDoAn;
    }

    public void setDonGiaDoAn(Float DonGiaDoAn) {
        this.DonGiaDoAn = DonGiaDoAn;
    }

    public int getSoLuongDoAn() {
        return soLuongDoAn;
    }

    public void setSoLuongDoAn(int soLuongDoAn) {
        this.soLuongDoAn = soLuongDoAn;
    }

    public String getMaDoUong() {
        return MaDoUong;
    }

    public void setMaDoUong(String MaDoUong) {
        this.MaDoUong = MaDoUong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public Float getDonGiaDoUong() {
        return donGiaDoUong;
    }

    public void setDonGiaDoUong(Float donGiaDoUong) {
        this.donGiaDoUong = donGiaDoUong;
    }

    public int getSoLuongDoUong() {
        return soLuongDoUong;
    }

    public void setSoLuongDoUong(int soLuongDoUong) {
        this.soLuongDoUong = soLuongDoUong;
    }
    
    
    
}

