/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
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
public class HoaDon {
    private String maHoaDon;
    private String maMay;
    private String idUser;
    private Date ngayLap;
    private String trangThai;
    private String phuongThucThanhToan;
}
