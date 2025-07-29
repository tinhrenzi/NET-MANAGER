package controller;

import java.util.Date;
import java.util.List;
import entity.*;

public interface QuanLyThongKeController {
    List<OrderManager> getLichSuSuDung(Date tuNgay, Date denNgay, String maMay, String tenKhachHang);
    List<ThongKeDoanhThu> getDoanhThu(Date tuNgay, Date denNgay);
    List<HoaDonChiTiet> getLichSuBanHang(Date tuNgay, Date denNgay);
    
    double getTongDoanhThu(Date tuNgay, Date denNgay);
    int getTongGioSuDung(Date tuNgay, Date denNgay, String maMay);
}
