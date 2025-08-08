package daoImpl;

import dao.MenuDAO;
import entity.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class MenuDAOImpl implements MenuDAO {

    private final String INSERT_SQL = "INSERT INTO Menu(MaMay, MaMon, TenMon, GiaTien, NgayMua, SoLuong, TongTien) VALUES (?,?, ?, ?, ?, ?, ?)";
    private final String SELECT_ALL_SQL = "SELECT * FROM Menu";
    private final String SELECT_BY_MAY = "SELECT * FROM Menu WHERE MaMay = ?";
    private final String idmenu ="SELECT * FROM Menu WHERE Id = ?";

    @Override
    public Menu insert(Menu order) {
        Object[] args = {
            order.getMaMay(),
            order.getMaMon(),
            order.getTenMon(),
            order.getGiaTien(),
            order.getNgayMua(),
            order.getSoLuong(),
            order.getTongTien()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return order;
    }

    @Override
    public List<Menu> findAll() {
        return XQuery.getBeanList(Menu.class,SELECT_ALL_SQL);
    }

    @Override
    public List<Menu> findByMay(String maMay) {
        return XQuery.getBeanList(Menu.class, SELECT_BY_MAY, maMay);
    }

    @Override
    public List<Menu> findById(String MaMenu) {
    return XQuery.getBeanList(Menu.class, idmenu, MaMenu);   
    }

    @Override
    public boolean resetMenuByMay(int maMay) {
       String sql = "UPDATE Menu SET SoLuong = 0, TongTien = 0 WHERE MaMay = ?";
    try {
        XJdbc.executeUpdate(sql, maMay);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }

    @Override
    public void deleteByMaMay(int maMay) {
       String sql = "DELETE FROM Menu WHERE MaMay = ?";
        try {
            XJdbc.executeUpdate(sql, maMay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> findByMaMay(int maMay) {
         return XQuery.getBeanList(Menu.class, SELECT_BY_MAY, maMay);
    }
   
}
