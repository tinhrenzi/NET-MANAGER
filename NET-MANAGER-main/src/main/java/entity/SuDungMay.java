/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author NITRO 5
 */
public class SuDungMay {
    private int Id;
    private int maMay;
    private String TenMay;
    private String TrangThai;
    private Date NgayChoi;
    private Date NgayKetThuc;
    private Time GioBatDau;
    private Time GioKetThuc;
    private float thoiGianChoi;
    private float GiaTheoGio;
    private double TongTien;
    public SuDungMay(){}

    public SuDungMay(int Id, int maMay, String TenMay, String TrangThai, Date NgayChoi, Date NgayKetThuc, Time GioBatDau, Time GioKetThuc, float thoiGianChoi, float GiaTheoGio, double TongTien) {
        this.Id = Id;
        this.maMay = maMay;
        this.TenMay = TenMay;
        this.TrangThai = TrangThai;
        this.NgayChoi = NgayChoi;
        this.NgayKetThuc = NgayKetThuc;
        this.GioBatDau = GioBatDau;
        this.GioKetThuc = GioKetThuc;
        this.thoiGianChoi = thoiGianChoi;
        this.GiaTheoGio = GiaTheoGio;
        this.TongTien = TongTien;
    }
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenMay() {
        return TenMay;
    }

    public void setTenMay(String TenMay) {
        this.TenMay = TenMay;
    }

    public int getMaMay() {
        return maMay;
    }

    public void setMaMay(int maMay) {
        this.maMay = maMay;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayChoi() {
        return NgayChoi;
    }

    public void setNgayChoi(Date NgayChoi) {
        this.NgayChoi = NgayChoi;
    }

    public Time getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(Time GioBatDau) {
        this.GioBatDau = GioBatDau;
    }

    public Time getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(Time GioKetThuc) {
        this.GioKetThuc = GioKetThuc;
    }

    public float getThoiGianChoi() {
        return thoiGianChoi;
    }

    public void setThoiGianChoi(float thoiGianChoi) {
        this.thoiGianChoi = thoiGianChoi;
    }

    public float getGiaTheoGio() {
        return GiaTheoGio;
    }

    public void setGiaTheoGio(float GiaTheoGio) {
        this.GiaTheoGio = GiaTheoGio;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }
    
}

