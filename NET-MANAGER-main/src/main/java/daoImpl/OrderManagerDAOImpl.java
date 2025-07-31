package daoImpl;

import entity.OrderManager;
import java.sql.*;
import java.util.List;
import util.XJdbc;
import util.XQuery;
import dao.orderManagerDAO;

public class OrderManagerDAOImpl implements orderManagerDAO {
    private final Connection conn;

    public OrderManagerDAOImpl() {
        conn = XJdbc.openConnection();
    }
    private final String INSERT_SQL = "INSERT INTO OrderManager(Id, Name, DonGia) VALUES (?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE OrderManager SET Name = ?, DonGia = ? WHERE Id = ?";
    private final String DELETE_SQL = "DELETE FROM OrderManager WHERE Id = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM OrderManager";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM OrderManager WHERE Id = ?";

    @Override
    public OrderManager create(OrderManager entity) {
        Object[] args = {
            entity.getId(),
            entity.getName(),
            entity.getDonGia()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return entity;
    }

    @Override
    public void update(OrderManager entity) {
        Object[] args = {
            entity.getName(),
            entity.getDonGia(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<OrderManager> findAll() {
        return XQuery.getBeanList(OrderManager.class, SELECT_ALL_SQL);
    }

    @Override
    public OrderManager findByID(String id) {
        return XQuery.getSingleBean(OrderManager.class, SELECT_BY_ID_SQL, id);
    }
}

