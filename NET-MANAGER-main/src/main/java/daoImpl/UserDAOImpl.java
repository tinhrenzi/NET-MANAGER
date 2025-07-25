package daoImpl;

import dao.UsersDAO;
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
    private final String findByUsername = "SELECT * FROM NguoiDung WHERE TenDangNhap=?";

    @Override
    public Users create(Users user) {
        Object[] args = {
            user.getIdUser(),
            user.getTenDangNhap(),
            user.getMatKhau(),
            user.getVaiTro(),
            user.isEnable(),
            user.getNamSinh(),
            user.getEmail(),
            user.getSoDienThoai(),
            new java.sql.Date(user.getNgayTaoUser().getTime())
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return user;
    }

    @Override
    public void update(Users user) {
        Object[] args = {
            user.getTenDangNhap(),
            user.getMatKhau(),
            user.getVaiTro(),
            user.isEnable(),
            user.getNamSinh(),
            user.getEmail(),
            user.getSoDienThoai(),
            new java.sql.Date(user.getNgayTaoUser().getTime()),
            user.getIdUser()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String idUser) {
        XJdbc.executeUpdate(DELETE_SQL, idUser);
    }

    @Override
    public List<Users> findAll() {
        return XQuery.getBeanList(Users.class, SELECT_ALL_SQL);
    }

    @Override
    public Users findByID(String idUser) {
        return XQuery.getSingleBean(Users.class, SELECT_BY_ID_SQL, idUser);
    }

    @Override
    public Users findByUsername(String username) {
        return XQuery.getSingleBean(Users.class, findByUsername, username);
    }

    // ✅ Thêm hàm cập nhật mật khẩu theo tên đăng nhập:
    @Override
    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE NguoiDung SET MatKhau = ? WHERE TenDangNhap = ?";
        XJdbc.executeUpdate(sql, newPassword, username);
    }
}
