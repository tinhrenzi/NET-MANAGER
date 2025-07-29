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
public interface MayTinhDAO{// Khóa chính là kiểu Nvarchar nên để String
    void insert(MayTinh mt);
    void update(MayTinh mt);
    void delete(String id);
    MayTinh selectById(String id);
    List<MayTinh> selectAll();

    // Mở máy = cập nhật trạng thái và thời gian bắt đầu
    void moMay(String id);

    // Tắt máy = reset trạng thái + thời gian
    void tatMay(String id);
}
