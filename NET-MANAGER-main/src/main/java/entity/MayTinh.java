/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.*;

public class MayTinh {
    private String Id;
    private String TenMay;
    private String TrangThai;
    private float GiaTheoGio;

    public MayTinh() {}

    public MayTinh(String Id, String TenMay, String TrangThai, float GiaTheoGio) {
        this.Id = Id;
        this.TenMay = TenMay;
        this.TrangThai = TrangThai;
        this.GiaTheoGio = GiaTheoGio;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTenMay() {
        return TenMay;
    }

    public void setTenMay(String TenMay) {
        this.TenMay = TenMay;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public float getGiaTheoGio() {
        return GiaTheoGio;
    }

    public void setGiaTheoGio(float GiaTheoGio) {
        this.GiaTheoGio = GiaTheoGio;
    }

}