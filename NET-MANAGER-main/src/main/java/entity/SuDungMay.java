/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author NITRO 5
 */
public class SuDungMay {
    private String Id;
    private String maMay;
    private Date ngaySuDung;
    private Time gioBatDau;
    private Time gioKetThuc;
    private float thoiGianChoi;
    private float giaTienTheoGio;
    private float tongTien;
    public SuDungMay(){}

    public SuDungMay(String Id, String maMay, Date ngaySuDung, Time gioBatDau, Time gioKetThuc, float thoiGianChoi, float giaTienTheoGio, float tongTien) {
        this.Id = Id;
        this.maMay = maMay;
        this.ngaySuDung = ngaySuDung;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.thoiGianChoi = thoiGianChoi;
        this.giaTienTheoGio = giaTienTheoGio;
        this.tongTien = tongTien;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public float getThoiGianChoi() {
        return thoiGianChoi;
    }

    public void setThoiGianChoi(float thoiGianChoi) {
        this.thoiGianChoi = thoiGianChoi;
    }



    public float getGiaTienTheoGio() {
        return giaTienTheoGio;
    }

    public void setGiaTienTheoGio(float giaTienTheoGio) {
        this.giaTienTheoGio = giaTienTheoGio;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    
}
