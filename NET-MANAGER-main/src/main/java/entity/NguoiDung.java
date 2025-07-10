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
public class NguoiDung {
    private String idUser;
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
    private boolean enable;
    private Date namSinh;
    private String email;
    private String soDienThoai;
    private Date ngayTaoUser;
    
}
