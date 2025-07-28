package daoImpl;

import dao.orderDAO;
import entity.DoAn;
import entity.DoUong;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.XJdbc;

public class OrderDAOImpl implements orderDAO {
    private final Connection conn;

    public OrderDAOImpl() {
        conn = XJdbc.openConnection();
    }

    @Override
    public List<DoAn> getAllDoAn() {
    List<DoAn> list = new ArrayList<>();   
    String DoAn ="select MaDoAn,TenDoAn,DonGia from DoAn";
        try(Connection conn = XJdbc.openConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(DoAn)) {
          while (rs.next()) {                
                DoAn da = new DoAn(
                  rs.getString(1),
                  rs.getString(2),
                  rs.getFloat(3)
                );
              list.add(da);
            }
        } catch (Exception e) {
        }
        return list;
    
    }

    @Override
    public List<DoUong> getAllNuocUong() {
    List<DoUong> list = new ArrayList<>();
    String DoUong ="select MaDoUong,TenDoUong,DonGia from DoUong";
        try(Connection conn = XJdbc.openConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(DoUong)) {
          while (rs.next()) {                
                DoUong du = new DoUong(
                  rs.getString(1),
                  rs.getString(2),
                  rs.getFloat(3)
                );
              list.add(du);

            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public void themMon(String MaMay, Order mon) {
        try {
            String sql = "insert into OrderNet(MaMay,MaMon,TenMon,Gia) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, MaMay);
            ps.setString(2, mon.getMaDoAn());
            ps.setString(3, mon.getTenDoAn());
            ps.setFloat(4, mon.getDonGiaDoAn());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}

