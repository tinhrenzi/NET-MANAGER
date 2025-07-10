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

public class Users {
    
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
