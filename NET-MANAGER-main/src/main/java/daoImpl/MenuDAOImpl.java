package daoImpl;

import dao.MenuDAO;
import entity.Menu;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class MenuDAOImpl implements MenuDAO {

    private final String INSERT_SQL = "INSERT INTO Menu(MaMay, MaMon, TenMon, GiaTien, NgayMua, SoLuong, TongTien) VALUES (?,?, ?, ?, ?, ?, ?)";
    private final String SELECT_ALL_SQL = "SELECT * FROM Menu";
    private final String SELECT_BY_MAY = "SELECT * FROM Menu WHERE MaMay = ?";
    private final String idmenu ="Select Id from Menu";

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

   
}
