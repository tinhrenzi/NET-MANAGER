/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class ThongKeDoanhThu {
    private int Id;
    private String MaThanhToan;
    private String MaMenu;
    private Date NgayChoi;
    private double TongTienMon;
    private double TongTienMay;
    public ThongKeDoanhThu(){}

    public ThongKeDoanhThu(int Id, String MaThanhToan, String MaMenu, Date NgayChoi, double TongTienMon, double TongTienMay) {
        this.Id = Id;
        this.MaThanhToan = MaThanhToan;
        this.MaMenu = MaMenu;
        this.NgayChoi = NgayChoi;
        this.TongTienMon = TongTienMon;
        this.TongTienMay = TongTienMay;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getMaThanhToan() {
        return MaThanhToan;
    }

    public void setMaThanhToan(String MaThanhToan) {
        this.MaThanhToan = MaThanhToan;
    }

    public String getMaMenu() {
        return MaMenu;
    }

    public void setMaMenu(String MaMenu) {
        this.MaMenu = MaMenu;
    }

    public Date getNgayChoi() {
        return NgayChoi;
    }

    public void setNgayChoi(Date NgayChoi) {
        this.NgayChoi = NgayChoi;
    }

    public double getTongTienMon() {
        return TongTienMon;
    }

    public void setTongTienMon(double TongTienMon) {
        this.TongTienMon = TongTienMon;
    }

    public double getTongTienMay() {
        return TongTienMay;
    }

    public void setTongTienMay(double TongTienMay) {
        this.TongTienMay = TongTienMay;
    }
    
}
