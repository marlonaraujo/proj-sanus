/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Administrador
 */

public class ConverteData{ 

    public java.sql.Date converteEmData(String data){
        if(data == null || data.equals(""))
            return null;
                    
        java.util.Date dt = null;
        
        try{
           DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
           
           dt = (java.util.Date) df.parse(data);
                
           
        }catch(ParseException ex) {
           ex.printStackTrace();
        }
        return new java.sql.Date (dt.getTime());
    }
    public String converteDataTexto(Date data){
        
        String dataEmTexto = "";
        
        DateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        dataEmTexto = sd.format(data);
        
        return dataEmTexto;
    }
}