/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author FPT
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HoaDonChiTiet {
    private String maHoaDonChiTiet;
    private String maHoaDon;
    private String maDoAn;
    private String maDoUong;
    private String tenDoAn;
    private String tenDoUong;
    private int soLuong;
    private float donGia;
}
