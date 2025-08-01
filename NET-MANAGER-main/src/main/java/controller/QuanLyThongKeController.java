package controller;

import dao.QuanLyThongKeDAO;
import daoImpl.QuanLyThongKeDaoImpl;
import java.util.Date;
import java.util.List;
import entity.*;

public interface QuanLyThongKeController extends QuanLyThongKeDAO{
    List<ThongKeDoanhThu> layTatCa();
    List<ThongKeDoanhThu> thongKeTheoNgay(java.sql.Date ngay);
    List<SuDungMay> getLichSuMay(java.sql.Date ngay);
}
