package daoImpl;

import entity.Admin;
import util.XJdbc;
import util.XQuery;

import java.util.List;
import dao.AdminDAO;

public class AdminDAOImpl implements AdminDAO {

    private final String INSERT_SQL = "INSERT INTO Admin (Id, Ten, MatKhau, VaiTro, TrangThai, NamSinh, Email, SoDienThoai, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Admin SET Ten=?, MatKhau=?, VaiTro=?, TrangThai=?, NamSinh=?, Email=?, SoDienThoai=?, NgayTao=? WHERE Id=?";
    private final String DELETE_SQL = "DELETE FROM Admin WHERE Id=?";
    private final String SELECT_ALL_SQL = "select * from Admin";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Admin WHERE Id=?";
    private final String findByUsername = "SELECT * FROM Admin WHERE Ten=?";

    @Override
    public Admin create(Admin user) {
        Object[] args = {
            user.getId(),
            user.getTen(),
            user.getMatKhau(),
            user.getVaiTro(),
            user.isTrangThai(),
            user.getNamSinh(),
            user.getEmail(),
            user.getSoDienThoai(),
            new java.sql.Date(user.getNgayTao().getTime())
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return user;
    }

    @Override
    public void update(Admin user) {
        Object[] args = {
            user.getTen(),
            user.getMatKhau(),
            user.getVaiTro(),
            user.isTrangThai(),
            user.getNamSinh(),
            user.getEmail(),
            user.getSoDienThoai(),
            new java.sql.Date(user.getNgayTao().getTime()),
            user.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String idUser) {
        XJdbc.executeUpdate(DELETE_SQL, idUser);
    }

    @Override
    public List<Admin> findAll() {
        return XQuery.getBeanList(Admin.class, SELECT_ALL_SQL);
    }

    @Override
    public Admin findByID(String idUser) {
        return XQuery.getSingleBean(Admin.class, SELECT_BY_ID_SQL, idUser);
    }

    @Override
    public Admin findByUsername(String username) {
        return XQuery.getSingleBean(Admin.class, findByUsername, username);
    }

    // ✅ Thêm hàm cập nhật mật khẩu theo tên đăng nhập:
    @Override
    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE Admin SET MatKhau = ? WHERE Ten = ?";
        XJdbc.executeUpdate(sql, newPassword, username);
    }
}
