/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author devin
 */
public class DatabaseConnection 
{
    public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String user = "user_dwbinnie";
    public static final String pwd = "user_dwbinnie";
    public static final String db = "4a1w";
    
    Statement stmt;
    Connection con;
    
    public DatabaseConnection() throws ClassNotFoundException, SQLException 
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection(url+db, user, pwd);
        stmt = (Statement) con.createStatement();
    }
    
    public ResultSet selectRows(String t, String r, String c) throws SQLException
    {
        String sql = "SELECT ";
        
        if (r == null)
            sql += "* from " + t;
        else
            sql += r + " from " + t;
        
        if (c == null)
            sql += ";";
        else
            sql += " where " + c + ";";
        
        return stmt.executeQuery(sql);
    }
    
    public boolean insertRows(String t, String cl, String v) throws SQLException
    {
        String sql = "INSERT INTO " + t + "(" + cl + ") VALUES(" + v + ");";
        return stmt.execute(sql);
    }
    
    public boolean updateRows(String t, String v, String c) throws SQLException
    {
        String sql = "UPDATE " + t + " SET " + v + " WHERE " + c;
        return stmt.execute(sql);
    }
    
    public void close() throws SQLException
    {
        con.close();
    }
            
}