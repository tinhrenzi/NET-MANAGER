/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import lombok.*;
/**
 *
 * @author VINH
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoAn {
    String maDoan ;
    String tenDoAn ;
    float donGia;
    int soLuong;
    boolean trangThai;
}
