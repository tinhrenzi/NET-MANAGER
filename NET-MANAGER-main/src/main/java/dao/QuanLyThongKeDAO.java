package dao;


import entity.Order;
import entity.SuDungMay;
import entity.ThongKeDoanhThu;
import java.sql.Date;
import java.util.List;

public interface QuanLyThongKeDAO {
    List<ThongKeDoanhThu> thongKeTheoNgay(java.sql.Date ngay);
    List<SuDungMay> getLichSuMay(Date fromDate);
    List<Order> getLichSuBanHang(Date fromDate);
    
    List<ThongKeDoanhThu> getAll1();
        List<SuDungMay> getAll2();
            List<Order> getAll3();
}
