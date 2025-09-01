/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class Admin {
    private String Id;
    private String Ten;
    private String MatKhau;
    private int VaiTro;
    private boolean TrangThai;
    private int NamSinh;
    private String Email;
    private String SoDienThoai;
    private Date NgayTao;
    private String Anh;

    public Admin() {}

    public Admin(String Id, String Ten, String MatKhau, int VaiTro, boolean TrangThai, int NamSinh, String Email, String SoDienThoai, Date NgayTao, String Anh) {
        this.Id = Id;
        this.Ten = Ten;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.TrangThai = TrangThai;
        this.NamSinh = NamSinh;
        this.Email = Email;
        this.SoDienThoai = SoDienThoai;
        this.NgayTao = NgayTao;
        this.Anh = Anh;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public String getTen() { return Ten; }
    public void setTen(String Ten) { this.Ten = Ten; }

    public String getMatKhau() { return MatKhau; }
    public void setMatKhau(String MatKhau) { this.MatKhau = MatKhau; }

    public int getVaiTro() { return VaiTro; }
    public void setVaiTro(int VaiTro) { this.VaiTro = VaiTro; }

    public boolean isTrangThai() { return TrangThai; }
    public void setTrangThai(boolean TrangThai) { this.TrangThai = TrangThai; }

    public int getNamSinh() { return NamSinh; }
    public void setNamSinh(int NamSinh) { this.NamSinh = NamSinh; }

    public String getEmail() { return Email; }
    public void setEmail(String Email) { this.Email = Email; }

    public String getSoDienThoai() { return SoDienThoai; }
    public void setSoDienThoai(String SoDienThoai) { this.SoDienThoai = SoDienThoai; }

    public Date getNgayTao() { return NgayTao; }
    public void setNgayTao(Date NgayTao) { this.NgayTao = NgayTao; }
}
