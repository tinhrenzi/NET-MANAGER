/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class ThongKeDoanhThu {
    private String idUser;
    private String maHoaDon;
    private Date ngayThongKe;
    private float tongTien;
    private String ghiChu;

    public ThongKeDoanhThu() {}

    public ThongKeDoanhThu(String idUser, String maHoaDon, Date ngayThongKe, float tongTien, String ghiChu) {
        this.idUser = idUser;
        this.maHoaDon = maHoaDon;
        this.ngayThongKe = ngayThongKe;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayThongKe() {
        return ngayThongKe;
    }

    public void setNgayThongKe(Date ngayThongKe) {
        this.ngayThongKe = ngayThongKe;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
