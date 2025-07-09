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
public class MayTinh {
    private String maMayTinh;
    private String tenMay;
    private String trangThai;
    private String viTri;
}
