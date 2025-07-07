/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author VINH
 */
public class XDate {
    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_SHORT = "MM/dd/yyyy";
    
    private static final SimpleDateFormat formater = new SimpleDateFormat();
    
    public static Date now(){
        return new Date();
    }

    public static Date parse(String dateTime, String pattern){
        formater.applyLocalizedPattern(pattern);
        try {
            return formater.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static Date parse(String dateTime){
        return parse(dateTime, PATTERN_SHORT);
    }
    
    public static String format(Date dateTime, String pattern){
        if(dateTime == null){
            return "";
        }
        formater.applyPattern(pattern);
        return formater.format(dateTime);
    }

    public static String format(Date dateTime){
        return format(dateTime, PATTERN_SHORT);
    }
            
    public static void main(String[] args) {
        Date date = XDate.parse("Jan 21, 2024", "MMM dd, yyyy");
        String text = XDate.format(date, "dd-MMM-yyyy");
        System.out.println(text);
    }
}
