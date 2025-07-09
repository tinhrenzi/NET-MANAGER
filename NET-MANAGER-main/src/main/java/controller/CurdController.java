/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

/**
 *
 * @author NITRO 5
 * @param <Entity>
 */
public interface CurdController<Entity> {
    void create();
    void update();
    void delete();
    
    void setForm(Entity entity); // Hiển thị thực thể lên form
    Entity getForm(); // Tạo thực thể từ dữ liệu form
}
