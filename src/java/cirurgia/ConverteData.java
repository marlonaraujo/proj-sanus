/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cirurgia;

/**
 *
 * @author Administrador
 */
import java.text.*; 
import java.util.Date;

public class ConverteData{ 

    public static String formatarData(String data) throws Exception{ 

       if (data == null || data.equals("")) 
            return null; 

       String dataF = null; 

       try{ 
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy"); 
        Date date = (java.util.Date)df.parse(data); 
        dataF = df. format (date) ; 
       }catch (ParseException e){ 
        throw e; 
       }
       return dataF; 
    }
    
    public static Date formatarData(Date data) throws Exception { 
        
        if (data == null) 
            return null; 
        
        Date date = null; 
        date = new Date(data.getTime()); 
        return date; 
    }

    public static Date strToDate(String data) throws Exception { 

        if (data == null) 
            return null; 

        Date dataF = null; 
        
        try{ 
            DateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy") ; 
            long timestamp = dateFormat.parse(data).getTime(); 
            dataF = new Date(timestamp); 
        }catch(ParseException e){ 
            throw e;
        }
      return dataF; 
    }
}