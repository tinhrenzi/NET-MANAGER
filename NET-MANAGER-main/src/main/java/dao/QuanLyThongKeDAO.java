package dao;


import entity.SuDungMay;
import entity.ThongKeDoanhThu;
import java.sql.Date;
import java.util.List;

public interface QuanLyThongKeDAO {
    List<ThongKeDoanhThu> thongKeTheoNgay(java.sql.Date ngay);
    List<ThongKeDoanhThu> getAll();
    List<SuDungMay> getLichSuMay(Date fromDate);
}
