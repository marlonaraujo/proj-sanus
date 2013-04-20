/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author emilianoeloi
 */
public class GenericDAO {
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private ArrayList<Object> parametros = new ArrayList<Object>(); 
    private String sql = null;
    
    public void setSql(String sql){
        this.sql = sql;
    }
    
    public void setParametro(String s){
        this.parametros.add(s);
    }
    public void setParametro(int i){
        this.parametros.add(i);
    }
    public void setParametro(Date d){
        this.parametros.add(d);
    }
    
    private Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    
    public void fecharConexao() throws SQLException{
        if(this.stmt != null) this.stmt.close();
        if(this.con != null) this.con.close();  
        this.parametros = new ArrayList<Object>();
    }
    
    public ResultSet getResultSet(){
        return this.rs;
    }
    
    public GenericDAO(){

    }
    
    private void carregarParametros() throws SQLException{
        if(this.parametros.size() == 0)
            return;
        int i = 1;
        for(Object o : this.parametros){
            if(o instanceof String){
                this.stmt.setString(i, (String)o);
            }else if(o instanceof Integer){
                this.stmt.setInt(i, (Integer)(o));
            }else if (o instanceof Date){
                this.stmt.setDate(i, (Date)o);
            }else if(o == null){
                this.stmt.setString(i, null);
            }
            i++;
        }        
    }
    
    private void executarDQL() throws SQLException{
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(this.sql);
            this.carregarParametros();
            this.rs = this.stmt.executeQuery();
        }catch(SQLException e){
            throw new GenericDAOException(e);
        }
    }
    
    private void executarDML() throws SQLException{
        try{
            this.con = getConexao();
            this.stmt = con.prepareStatement(this.sql);
            this.carregarParametros();
            this.stmt.executeUpdate();
        }catch(SQLException e){
            throw new GenericDAOException(e);
        }finally{
           if(this.stmt != null) this.stmt.close();
           if(this.con != null) this.con.close();
           this.parametros = new ArrayList<Object>();
        }
    }
    
    public void executarUpdate(){
        try{
            this.executarDML();
        }catch(SQLException e){
            throw new GenericDAOException(e);
        }
    }
    
    public void executarDelete(){
        try{
            this.executarDML();
        }catch(SQLException e){
            throw new GenericDAOException(e);
        }
    }
    
    public void executarInsert(){
        try{
            this.executarDML();
        }catch(SQLException e){
            throw new GenericDAOException(e);
        }
    }
    
    public void executarSelect(){
        try{
            this.executarDQL();
        }catch(SQLException e){
            throw new GenericDAOException(e);
        }
    }
    
}
