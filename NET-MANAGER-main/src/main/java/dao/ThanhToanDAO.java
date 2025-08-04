/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.ThanhToan;

/**
 *
 * @author VINH
 */
public interface ThanhToanDAO{// Khóa chính là kiểu Nvarchar nên để String
    boolean insert(ThanhToan tt);
}
