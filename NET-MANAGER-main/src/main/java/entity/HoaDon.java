/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private String maMay;
    private String idUser;
    private Date ngayLap;
    private String trangThai;
    private String phuongThucThanhToan;

    public HoaDon() {}

    public HoaDon(String maHoaDon, String maMay, String idUser, Date ngayLap, String trangThai, String phuongThucThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maMay = maMay;
        this.idUser = idUser;
        this.ngayLap = ngayLap;
        this.trangThai = trangThai;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getMaMay() { return maMay; }
    public void setMaMay(String maMay) { this.maMay = maMay; }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getPhuongThucThanhToan() { return phuongThucThanhToan; }
    public void setPhuongThucThanhToan(String phuongThucThanhToan) { this.phuongThucThanhToan = phuongThucThanhToan; }
}

