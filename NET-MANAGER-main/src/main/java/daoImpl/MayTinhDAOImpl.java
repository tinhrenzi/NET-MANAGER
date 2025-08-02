/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.MayTinhDAO;
import entity.MayTinh;
import util.XJdbc;

import java.util.List;
import util.XQuery;

public class MayTinhDAOImpl implements MayTinhDAO {
    private final String Sql_Cre = "insert into MayTinh(Id,TenMay,TrangThai) values (?,?,?);";
    private final String Sql_Up = "Update MayTinh set TenMay =?, TrangThai=? where Id=?";
    private final String Sql_De ="Delete from MayTinh where Id=?";
    private final String Sql_All= "select Id,TenMay,TrangThai from MayTinh";

    @Override
    public MayTinh create(MayTinh entity) {
        Object[] args = {
            entity.getId(),
            entity.getTenMay(),
            entity.getTrangThai()
        };
        XJdbc.executeUpdate(Sql_Cre, args);
        return entity;
    }

    @Override
    public void update(MayTinh entity) {
        Object[] args = {           
            entity.getTenMay(),
            entity.getTrangThai(),
            entity.getId(),
        };
        XJdbc.executeUpdate(Sql_Up, args);
    }

   @Override
public void deleteByID(String id) {
    XJdbc.executeUpdate(Sql_De, id);  // 
}


    @Override
    public List<MayTinh> findAll() {
    return XQuery.getBeanList(MayTinh.class, Sql_All);
    }

    @Override
    public MayTinh findByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

