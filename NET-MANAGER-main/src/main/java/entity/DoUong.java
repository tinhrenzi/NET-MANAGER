/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class DoUong {
    private String maDoUong;
    private String tenDoUong;
    private float donGia;
    private int soLuong;
    private String trangThai;

    public DoUong() {}

    public DoUong(String maDoUong, String tenDoUong, float donGia, int soLuong, String trangThai) {
        this.maDoUong = maDoUong;
        this.tenDoUong = tenDoUong;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMaDoUong() { return maDoUong; }
    public void setMaDoUong(String maDoUong) { this.maDoUong = maDoUong; }

    public String getTenDoUong() { return tenDoUong; }
    public void setTenDoUong(String tenDoUong) { this.tenDoUong = tenDoUong; }

    public float getDonGia() { return donGia; }
    public void setDonGia(float donGia) { this.donGia = donGia; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}

