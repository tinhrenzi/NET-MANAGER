/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author VINH
 * @param <T>
 * @param <ID>
 */
public interface crudDAO<T, ID> {
    T create (T entity);
    void update(T entity);
    void deleteByID(ID id);
    List<T> findAll();
    T findByID(ID id);
}
