package daoImpl;

import dao.OrderDAO;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class OrderDAOImpl implements OrderDAO {

    private final String INSERT_SQL = "INSERT INTO OrderNet(MaMay, TenMay, MaMon, TenMon, Gia, SoLuong, TongTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String SELECT_ALL_SQL = "SELECT * FROM OrderNet";
    private final String SELECT_BY_MAY = "SELECT * FROM OrderNet WHERE MaMay = ?";

    // Insert chi tiết món (đầy đủ thông tin)
    @Override
    public Order insert(Order order) {
        Object[] args = {
    order.getMaMay(),
    order.getTenMay(),
    order.getMaMon(),
    order.getTenMon(),
    order.getGia(),
    order.getSoLuong(),
    order.getTongTien()
    };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return order;
    }

    // Insert đơn giản chỉ có máy và tổng tiền (dùng cho btn_mua)
    

    @Override
    public List<Order> findAll() {
        return XQuery.getBeanList(Order.class, SELECT_ALL_SQL);
    }

    @Override
    public List<Order> findByMay(String maMay) {
        return XQuery.getBeanList(Order.class, SELECT_BY_MAY, maMay);
    }
}
