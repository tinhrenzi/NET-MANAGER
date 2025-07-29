/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class OrderManager {
    private String Id;
    private String Name;
    private float DonGia;
    public OrderManager() {}

    public OrderManager(String Id, String Name, float DonGia) {
        this.Id = Id;
        this.Name = Name;
        this.DonGia = DonGia;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGiaDoAn) {
        this.DonGia = DonGiaDoAn;
    }

    
}

