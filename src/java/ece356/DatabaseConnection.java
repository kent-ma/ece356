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
    
    Statement stmt;
    Connection con;
    
    public DatabaseConnection() throws ClassNotFoundException, SQLException 
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection(url, user, pwd);
        stmt = (Statement) con.createStatement();
    }
    
    public ResultSet getTimeDiff() throws SQLException
    {
        return stmt.executeQuery("SELECT TIMEDIFF(DepartTime, ArrivalTime) from Visit;");
    }
    
    public ResultSet getUserName(String user, String pass) throws SQLException
    {
        return stmt.executeQuery("Select * from 4a1w.Login where Name = '" + user + "' and Password = '" + pass + "';");
    }
            
}
