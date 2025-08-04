/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class MonAn {
      private int Id;
    private String TenMon;
    private double GiaTien;

    public MonAn() {}

    public MonAn(int Id, String TenMon, double GiaTien) {
        this.Id = Id;
        this.TenMon = TenMon;
        this.GiaTien = GiaTien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public double getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(double GiaTien) {
        this.GiaTien = GiaTien;
    }
    @Override
    public String toString() {
        return this.TenMon;
    }   
   
    
}

