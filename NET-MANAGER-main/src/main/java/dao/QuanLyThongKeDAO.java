package dao;

import java.util.List;

import entity.Menu;
import entity.SuDungMay;
import entity.ThongKeDoanhThu;

public interface QuanLyThongKeDAO {

    List<ThongKeDoanhThu> getAllThonKe();

    List<SuDungMay> getAllSDMay();

    List<Menu> getAllMenu();

    List<SuDungMay> getLichSuSuDungMay(java.sql.Date tuNgay, java.sql.Date denNgay);

    List<Menu> getLichSuMenu(java.sql.Date tuNgay, java.sql.Date denNgay);

    List<ThongKeDoanhThu> getthongKeTheoKhoangNgay(java.sql.Date tuNgay, java.sql.Date denNgay);

}
