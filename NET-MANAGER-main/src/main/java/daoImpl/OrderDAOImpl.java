package daoImpl;

import dao.orderDAO;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements orderDAO {
    private final Connection conn;

    public OrderDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Order> getAllOrders() {
        return findAll(); // Gọi luôn findAll() cho tránh trùng code
    }

    @Override
    public void insertOrder(Order order) {
        create(order); // Gọi create() cho đúng nguyên tắc interface
    }

    @Override
    public void deleteOrder(String id) {
        deleteByID(id); // Gọi đúng method để tránh viết lại
    }

    @Override
    public void updateOrder(Order order) {
        update(order); // Sử dụng update chính
    }

    @Override
    public Order create(Order order) {
        String sql = "INSERT INTO MENU (id, MaDoAn, TenDoAn, DonGiaDoAn, SoLuongDoAn, MaDoUong, TenDoUong, DonGiaDoUong, SoLuongDoUong) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getId());
            ps.setString(2, order.getMaDoAn());
            ps.setString(3, order.getTenDoAn());
            ps.setFloat(4, order.getDonGiaDoAn());
            ps.setInt(5, order.getSoLuongDoAn());
            ps.setString(6, order.getMaDoUong());
            ps.setString(7, order.getTenDoUong());
            ps.setFloat(8, order.getDonGiaDoUong());
            ps.setInt(9, order.getSoLuongDoUong());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE MENU SET MaDoAn=?, TenDoAn=?, DonGiaDoAn=?, SoLuongDoAn=?, MaDoUong=?, TenDoUong=?, DonGiaDoUong=?, SoLuongDoUong=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getMaDoAn());
            ps.setString(2, order.getTenDoAn());
            ps.setFloat(3, order.getDonGiaDoAn());
            ps.setInt(4, order.getSoLuongDoAn());
            ps.setString(5, order.getMaDoUong());
            ps.setString(6, order.getTenDoUong());
            ps.setFloat(7, order.getDonGiaDoUong());
            ps.setInt(8, order.getSoLuongDoUong());
            ps.setString(9, order.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByID(String id) {
        String sql = "DELETE FROM MENU WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM MENU";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getString("id"));
                o.setMaDoAn(rs.getString("MaDoAn"));
                o.setTenDoAn(rs.getString("TenDoAn"));
                o.setDonGiaDoAn(rs.getFloat("DonGiaDoAn"));
                o.setSoLuongDoAn(rs.getInt("SoLuongDoAn"));
                o.setMaDoUong(rs.getString("MaDoUong"));
                o.setTenDoUong(rs.getString("TenDoUong"));
                o.setDonGiaDoUong(rs.getFloat("DonGiaDoUong"));
                o.setSoLuongDoUong(rs.getInt("SoLuongDoUong"));

                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Order findByID(String id) {
        String sql = "SELECT * FROM MENU WHERE id = ?";
        Order order = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setId(rs.getString("id"));
                    order.setMaDoAn(rs.getString("MaDoAn"));
                    order.setTenDoAn(rs.getString("TenDoAn"));
                    order.setDonGiaDoAn(rs.getFloat("DonGiaDoAn"));
                    order.setSoLuongDoAn(rs.getInt("SoLuongDoAn"));
                    order.setMaDoUong(rs.getString("MaDoUong"));
                    order.setTenDoUong(rs.getString("TenDoUong"));
                    order.setDonGiaDoUong(rs.getFloat("DonGiaDoUong"));
                    order.setSoLuongDoUong(rs.getInt("SoLuongDoUong"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}
