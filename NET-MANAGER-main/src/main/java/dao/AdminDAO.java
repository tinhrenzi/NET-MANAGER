/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Admin;

/**
 *
 * @author VINH
 */
public interface AdminDAO extends CrudDAO<Admin, String>{// Khóa chính là kiểu Nvarchar nên để String
    Admin findByUsername (String username);
    void updatePassword(String username, String newPassword);
}