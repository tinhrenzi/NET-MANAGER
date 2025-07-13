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

    public MayTinh() {}

    public MayTinh(String maMayTinh, String tenMay, String trangThai, String viTri) {
        this.maMayTinh = maMayTinh;
        this.tenMay = tenMay;
        this.trangThai = trangThai;
        this.viTri = viTri;
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
}