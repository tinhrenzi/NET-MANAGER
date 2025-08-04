/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class ThongKeDoanhThu {
    private int Id;
    private Date NgayBT;
    private Date DenNgay;
    private Date NgayChoi;
    private double TongTienMon;
    private double TongTienMay;
    private double TongDoanhThu;
    public ThongKeDoanhThu(){}

    public ThongKeDoanhThu(int Id, Date NgayBT, Date DenNgay, Date NgayChoi, double TongTienMon, double TongTienMay, double TongDoanhThu) {
        this.Id = Id;
        this.NgayBT = NgayBT;
        this.DenNgay = DenNgay;
        this.NgayChoi = NgayChoi;
        this.TongTienMon = TongTienMon;
        this.TongTienMay = TongTienMay;
        this.TongDoanhThu = TongDoanhThu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getNgayBT() {
        return NgayBT;
    }

    public void setNgayBT(Date NgayBT) {
        this.NgayBT = NgayBT;
    }

    public Date getDenNgay() {
        return DenNgay;
    }

    public void setDenNgay(Date DenNgay) {
        this.DenNgay = DenNgay;
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

    public double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(double TongDoanhThu) {
        this.TongDoanhThu = TongDoanhThu;
    }

   
    
}
