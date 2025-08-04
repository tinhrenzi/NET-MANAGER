/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.SuDungMay;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author NITRO 5
 */
public interface SDMayDAO extends CrudDAO<SuDungMay, String>{
    void MoMay(SuDungMay SDM);
    void TatMay(SuDungMay SDM);
}
