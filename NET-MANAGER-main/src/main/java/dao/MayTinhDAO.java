/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.MayTinh;
import java.util.List;

/**
 *
 * @author VINH
 */
public interface MayTinhDAO extends CrudDAO<MayTinh, String>{
    void capNhatTrangThai(String maMay, String trangThai);
    String layTrangThai(String maMay);
    List<String> getTatCaMaMay();
    public void capNhatTrangThai(int maMay, String trangThai);
    List<MayTinh> finName(String name);

}
