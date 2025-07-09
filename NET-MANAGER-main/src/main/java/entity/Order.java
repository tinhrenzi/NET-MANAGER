/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import lombok.*;

/**
 *
 * @author NITRO 5
 */
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@Data 
public class Order {
   public String MaDa;
   public String TenDA;
   public double GiaDA;
   public String TrangThaiDA;
   
   public String MaDU;
   public String TenDU;
   public double GiaDU;
   public String TrangThaiDu;
}
