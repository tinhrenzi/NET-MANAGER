/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.*;

public class MayTinh {
    private String Id;
    private String Name;
    private Timestamp StartTime;
    private Timestamp NowTime;
    private String Status;

    public MayTinh() {}

    public MayTinh(String Id, String Name, Timestamp StartTime, Timestamp NowTime, String Status) {
        this.Id = Id;
        this.Name = Name;
        this.StartTime = StartTime;
        this.NowTime = NowTime;
        this.Status = Status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Timestamp getStartTime() {
        return StartTime;
    }

    public void setStartTime(Timestamp StartTime) {
        this.StartTime = StartTime;
    }

    public Timestamp getNowTime() {
        return NowTime;
    }

    public void setNowTime(Timestamp NowTime) {
        this.NowTime = NowTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}