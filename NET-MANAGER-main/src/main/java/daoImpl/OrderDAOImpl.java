package daoImpl;

import dao.orderDAO;
import entity.OrderManager;
import java.sql.*;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class OrderDAOImpl implements orderDAO {
    private final Connection conn;

    public OrderDAOImpl() {
        conn = XJdbc.openConnection();
    }
    private final String INSERT_SQL = "insert into OrderManager(Id,Name,DonGia) values(?,?,?)";
    private final String UPDATE_SQL = "UPDATE OrderManager SET Name=?, DonGia=? WHERE Id=?";
    private final String DELETE_SQL = "DELETE FROM OrderManager WHERE Id=?";
    private final String SELECT_ALL_SQL = "Select Id,Name,DonGia from OrderManager";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM OrderManager WHERE Id=?";
    private final String findByUsername = "SELECT * FROM OrderManager WHERE Name=?";
    
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
            entity.getId(),
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
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

