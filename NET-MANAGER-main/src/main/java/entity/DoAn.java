/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
public class DoAn {
    private String maDoAn;
    private String tenDoAn;
    private float donGia;
    private int soLuong;
    private String trangThai;

    public DoAn() {}

    public DoAn(String maDoAn, String tenDoAn, float donGia, int soLuong, String trangThai) {
        this.maDoAn = maDoAn;
        this.tenDoAn = tenDoAn;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMaDoAn() { return maDoAn; }
    public void setMaDoAn(String maDoAn) { this.maDoAn = maDoAn; }

    public String getTenDoAn() { return tenDoAn; }
    public void setTenDoAn(String tenDoAn) { this.tenDoAn = tenDoAn; }

    public float getDonGia() { return donGia; }
    public void setDonGia(float donGia) { this.donGia = donGia; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}