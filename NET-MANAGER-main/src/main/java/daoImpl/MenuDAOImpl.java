package daoImpl;

import java.util.List;

import dao.MenuDAO;
import entity.Menu;
import util.XJdbc;
import util.XQuery;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public void UpSoluong(Menu entity) {
        String Sql = "update Menu set SoLuong=?, TongTien = GiaTien*? where Id = ?";
        Object[] args = {entity.getSoLuong(), entity.getSoLuong(), entity.getId(),};
        XJdbc.executeUpdate(Sql, args);
    }

    @Override
    public Menu Mua(Menu order) {
        String INSERT_SQL = "INSERT INTO Menu(MaSDMay,TenMay, MaMon, TenMon, GiaTien, NgayMua, SoLuong, TongTien) VALUES (?,?, ?, ?, ?, ?, ?,?)";
        Object[] args = {
            order.getMaSDMay(),
            order.getTenMay(),
            order.getMaMon(),
            order.getTenMon(),
            order.getGiaTien(),
            order.getNgayMua(),
            order.getSoLuong(),
            order.getTongTien()};
        XJdbc.executeUpdate(INSERT_SQL, args);
        return order;
    }

    @Override
    public Menu findById(int id) {
        String sql = "SELECT * FROM Menu WHERE Id = ?";
        List<Menu> list = XQuery.getBeanList(Menu.class, sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Menu> findByMay(String maSDMay) {
        String sql = "SELECT * FROM Menu WHERE MaSDMay = ?";
        return XQuery.getBeanList(Menu.class, sql, maSDMay);
    }

    @Override
    public List<Menu> findByIdSD(String maSDMay) {
        String sql = "SELECT * FROM Menu WHERE MaSDMay = ?";
        return XQuery.getBeanList(Menu.class, sql, maSDMay);
    }

    @Override
    public List<Menu> findAll() {
        String sql = "SELECT * FROM Menu";
        return XQuery.getBeanList(Menu.class, sql);
    }

}
