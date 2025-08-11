package daoImpl;

import dao.MenuDAO;
import entity.Menu;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class MenuDAOImpl implements MenuDAO {
    
    @Override
    public void UpSoluong(Menu entity) {
        String Sql = "update Menu set SoLuong=?, TongTien = GiaTien*? where Id = ?";
            Object[] args = {
            entity.getSoLuong(),
            entity.getSoLuong(),
            entity.getId(),
        };
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
            order.getTongTien()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return order;
    }
    @Override
    public List<Menu> FindByIdSD(int MaSDMay) {
    String SELECT_ALL_SQL = "SELECT * FROM Menu where MaSDMay =?";
    return XQuery.getBeanList(Menu.class,SELECT_ALL_SQL,MaSDMay);
    }

    @Override
    public List<Menu> findByMay(String maMay) {
    String SELECT_BY_MAY = "SELECT * FROM Menu WHERE MaSDMay = ?";
    return XQuery.getBeanList(Menu.class, SELECT_BY_MAY, maMay);
    }

    @Override
    public List<Menu> findById(String MaMenu) {
    String idmenu ="Select Id from Menu";
    return XQuery.getBeanList(Menu.class, idmenu, MaMenu);   
    }


}
