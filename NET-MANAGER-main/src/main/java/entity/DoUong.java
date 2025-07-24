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

    public DoUong() {
    }

    public DoUong(String maDoUong, String tenDoUong, float donGia, int soLuong) {
        this.maDoUong = maDoUong;
        this.tenDoUong = tenDoUong;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public String getMaDoUong() {
        return maDoUong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public float getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaDoUong(String maDoUong) {
        this.maDoUong = maDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}

