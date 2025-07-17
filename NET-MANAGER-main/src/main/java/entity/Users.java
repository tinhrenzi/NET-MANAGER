/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class Users {
    private String idUser;
    private String tenDangNhap;
    private String matKhau;
    private int vaiTro;
    private boolean enable;
    private int namSinh;
    private String email;
    private String soDienThoai;
    private Date ngayTaoUser;

    public Users() {}

    public Users(String idUser, String tenDangNhap, String matKhau, int vaiTro, boolean enable, int namSinh, String email, String soDienThoai, Date ngayTaoUser) {
        this.idUser = idUser;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.enable = enable;
        this.namSinh = namSinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.ngayTaoUser = ngayTaoUser;
    }


    

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public int getVaiTro() { return vaiTro; }
    public void setVaiTro(int vaiTro) { this.vaiTro = vaiTro; }

    public boolean isEnable() { return enable; }
    public void setEnable(boolean enable) { this.enable = enable; }

    public int getNamSinh() { return namSinh; }
    public void setNamSinh(int namSinh) { this.namSinh = namSinh; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public Date getNgayTaoUser() { return ngayTaoUser; }
    public void setNgayTaoUser(Date ngayTaoUser) { this.ngayTaoUser = ngayTaoUser; }
}
