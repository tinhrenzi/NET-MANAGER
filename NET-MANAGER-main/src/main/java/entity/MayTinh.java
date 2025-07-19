/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class MayTinh {
    private String maMayTinh;
    private String tenMay;
    private String trangThai;
    private String viTri;
    private int thoiGian;

    public MayTinh() {}

    public MayTinh(String maMayTinh, String tenMay, String trangThai, String viTri, int thoiGian) {
        this.maMayTinh = maMayTinh;
        this.tenMay = tenMay;
        this.trangThai = trangThai;
        this.viTri = viTri;
        this.thoiGian = thoiGian;
    }

    public String getMaMayTinh() {
        return maMayTinh;
    }

    public void setMaMayTinh(String maMayTinh) {
        this.maMayTinh = maMayTinh;
    }

    public String getTenMay() {
        return tenMay;
    }

    public void setTenMay(String tenMay) {
        this.tenMay = tenMay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

}