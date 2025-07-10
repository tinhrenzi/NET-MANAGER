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
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getIdUser(),
                entity.getTenDangNhap(),
                entity.getNamSinh(),
                entity.getVaiTro(),
                entity.getEmail(),
                entity.getMatKhau(),
                entity.getNgayTaoUser(),
                entity.getSoDienThoai()
        );
        return entity;
    }

    @Override
    public void update(Users entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getIdUser(),
                entity.getTenDangNhap(),
                entity.getNamSinh(),
                entity.getVaiTro(),
                entity.getEmail(),
                entity.getMatKhau(),
                entity.getNgayTaoUser(),
                entity.getSoDienThoai()
        );
    }

    @Override
    public void deleteByID(String id) {
        XJdbc.executeQuery(id, DELETE_SQL);
    }

    @Override
    public List<Users> findAll() {
        return XQuery.getBeanList(Users.class, SELECT_ALL_SQL);
    }

    @Override
    public Users findByID(String id) {
        return XQuery.getSingleBean(Users.class, id, SELECT_BY_ID_SQL);
    }

}
