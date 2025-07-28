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


    private String MaDoUong;
    private String tenDoUong;
    private Float donGiaDoUong;


    public Order() {}

    public Order(String MaDoAn, String TenDoAn, Float DonGiaDoAn) {
        this.MaDoAn = MaDoAn;
        this.TenDoAn = TenDoAn;
        this.DonGiaDoAn = DonGiaDoAn;
    }

    public Order(String id, String MaDoAn, String TenDoAn, Float DonGiaDoAn, String MaDoUong, String tenDoUong, Float donGiaDoUong ) {
        this.id = id;
        this.MaDoAn = MaDoAn;
        this.TenDoAn = TenDoAn;
        this.DonGiaDoAn = DonGiaDoAn;

        this.MaDoUong = MaDoUong;
        this.tenDoUong = tenDoUong;
        this.donGiaDoUong = donGiaDoUong;

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
}

