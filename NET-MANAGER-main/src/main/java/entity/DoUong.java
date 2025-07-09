/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.*;
/**
 *
 * @author FPT
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoUong {
    private String maDoUong;
    private String tenDoUong;
    private float donGia;
    private int soLuong;
    private String trangThai; 
}
