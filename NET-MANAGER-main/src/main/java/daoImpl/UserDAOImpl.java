/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;
import dao.UserDAO;
import entity.NguoiDung;
import util.XJdbc;
import util.XQuery;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final String INSERT_SQL = "INSERT INTO NguoiDung (IdUser, TenDangNhap, MatKhau, VaiTro, Enable, NamSinh, Email, SoDienThoai, NgayTaoUser) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE NguoiDung SET TenDangNhap=?, MatKhau=?, VaiTro=?, Enable=?, NamSinh=?, Email=?, SoDienThoai=?, NgayTaoUser=? WHERE IdUser=?";
    private final String DELETE_SQL = "DELETE FROM NguoiDung WHERE IdUser=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM NguoiDung";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM NguoiDung WHERE IdUser=?";

    @Override
    public NguoiDung create(NguoiDung user) {
        Object[] args = {
            user.getIdUser(),
            user.getTenDangNhap(),
            user.getMatKhau(),
            user.getVaiTro(),
            user.isEnable(),
            user.getNamSinh(),
            user.getEmail(),
            user.getSoDienThoai(),
            user.getNgayTaoUser()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return user;
    }

    @Override
    public void update(NguoiDung user) {
        Object[] args = {
            user.getTenDangNhap(),
            user.getMatKhau(),
            user.getVaiTro(),
            user.isEnable(),
            user.getNamSinh(),
            user.getEmail(),
            user.getSoDienThoai(),
            user.getNgayTaoUser(),
            user.getIdUser()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteById(String idUser) {
        XJdbc.executeUpdate(DELETE_SQL, idUser);
    }

    @Override
    public List<NguoiDung> findAll() {
        return XQuery.getBeanList(NguoiDung.class, SELECT_ALL_SQL);
    }

    @Override
    public NguoiDung findById(String idUser) {
        return XQuery.getSingleBean(NguoiDung.class, SELECT_BY_ID_SQL, idUser);
    }
}

