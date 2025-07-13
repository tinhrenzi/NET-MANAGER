/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class Order {
    private String maDa;
    private String tenDA;
    private float giaDA;
    private String trangThaiDA;

    private String maDU;
    private String tenDU;
    private float giaDU;
    private String trangThaiDu;

    public Order() {}

    public Order(String maDa, String tenDA, float giaDA, String trangThaiDA,
                 String maDU, String tenDU, float giaDU, String trangThaiDu) {
        this.maDa = maDa;
        this.tenDA = tenDA;
        this.giaDA = giaDA;
        this.trangThaiDA = trangThaiDA;
        this.maDU = maDU;
        this.tenDU = tenDU;
        this.giaDU = giaDU;
        this.trangThaiDu = trangThaiDu;
    }

    public String getMaDa() {
        return maDa;
    }

    public void setMaDa(String maDa) {
        this.maDa = maDa;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public float getGiaDA() {
        return giaDA;
    }

    public void setGiaDA(float giaDA) {
        this.giaDA = giaDA;
    }

    public String getTrangThaiDA() {
        return trangThaiDA;
    }

    public void setTrangThaiDA(String trangThaiDA) {
        this.trangThaiDA = trangThaiDA;
    }

    public String getMaDU() {
        return maDU;
    }

    public void setMaDU(String maDU) {
        this.maDU = maDU;
    }

    public String getTenDU() {
        return tenDU;
    }

    public void setTenDU(String tenDU) {
        this.tenDU = tenDU;
    }

    public float getGiaDU() {
        return giaDU;
    }

    public void setGiaDU(float giaDU) {
        this.giaDU = giaDU;
    }

    public String getTrangThaiDu() {
        return trangThaiDu;
    }

    public void setTrangThaiDu(String trangThaiDu) {
        this.trangThaiDu = trangThaiDu;
    }
}

