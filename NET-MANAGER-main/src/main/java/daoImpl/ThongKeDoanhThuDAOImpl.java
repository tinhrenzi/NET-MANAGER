/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;
import dao.ThongKeDoanhThuDAO;
import entity.ThongKeDoanhThu;
import util.XJdbc;
import util.XQuery;

import java.sql.Date;
import java.util.List;

public class ThongKeDoanhThuDAOImpl implements ThongKeDoanhThuDAO {

    private final String INSERT_SQL = "INSERT INTO ThongKeDoanhThu (IdUser, MaHoaDon, NgayThongKe, TongTien, GhiChu) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE ThongKeDoanhThu SET NgayThongKe=?, TongTien=?, GhiChu=? WHERE IdUser=? AND MaHoaDon=?";
    private final String DELETE_SQL = "DELETE FROM ThongKeDoanhThu WHERE IdUser=? AND MaHoaDon=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM ThongKeDoanhThu";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM ThongKeDoanhThu WHERE IdUser=? AND MaHoaDon=?";

    @Override
    public ThongKeDoanhThu create(ThongKeDoanhThu tk) {
        Object[] args = {
            tk.getIdUser(),
            tk.getMaHoaDon(),
            new Date(tk.getNgayThongKe().getTime()),
            tk.getTongTien(),
            tk.getGhiChu()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return tk;
    }

    @Override
    public void update(ThongKeDoanhThu tk) {
        Object[] args = {
            new Date(tk.getNgayThongKe().getTime()),
            tk.getTongTien(),
            tk.getGhiChu(),
            tk.getIdUser(),
            tk.getMaHoaDon()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String compositeKey) {
        // Tách IdUser và MaHoaDon bằng dấu #
        String[] parts = compositeKey.split("#");
        if (parts.length == 2) {
            XJdbc.executeUpdate(DELETE_SQL, parts[0], parts[1]);
        } else {
            throw new IllegalArgumentException("Composite key phải theo định dạng: idUser#maHoaDon");
        }
    }

    @Override
    public List<ThongKeDoanhThu> findAll() {
        return XQuery.getBeanList(ThongKeDoanhThu.class, SELECT_ALL_SQL);
    }

    @Override
    public ThongKeDoanhThu findByID(String compositeKey) {
        String[] parts = compositeKey.split("#");
        if (parts.length == 2) {
            return XQuery.getSingleBean(ThongKeDoanhThu.class, SELECT_BY_ID_SQL, parts[0], parts[1]);
        } else {
            throw new IllegalArgumentException("Composite key phải theo định dạng: idUser#maHoaDon");
        }
    }
}
