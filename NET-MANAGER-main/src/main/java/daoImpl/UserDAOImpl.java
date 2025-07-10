/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;
import dao.UsersDAO;
import entity.NguoiDung;
import entity.Users;
import util.XJdbc;
import util.XQuery;
import java.util.List;

public class UserDAOImpl implements UsersDAO {

    private final String INSERT_SQL = "INSERT INTO NguoiDung (IdUser, TenDangNhap, MatKhau, VaiTro, Enable, NamSinh, Email, SoDienThoai, NgayTaoUser) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE NguoiDung SET TenDangNhap=?, MatKhau=?, VaiTro=?, Enable=?, NamSinh=?, Email=?, SoDienThoai=?, NgayTaoUser=? WHERE IdUser=?";
    private final String DELETE_SQL = "DELETE FROM NguoiDung WHERE IdUser=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM NguoiDung";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM NguoiDung WHERE IdUser=?";

    @Override
    public Users create(Users entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Users entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Users> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Users findByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

