/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Users;

/**
 *
 * @author VINH
 */
public interface UsersDAO extends crudDAO<Users, String>{// Khóa chính là kiểu Nvarchar nên để String
    Users findByUsername (String username);
}
