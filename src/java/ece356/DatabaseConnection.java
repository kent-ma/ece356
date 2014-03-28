/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import ece356.Members.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    
    public Doctor selectDoctor(String c) throws SQLException
    {
        ResultSet row = selectRows("Doctor", null, c);
        Doctor newDoctor = new Doctor();
        
        row.next();
        newDoctor.setDoctorId(row.getInt("DoctorID"));
        newDoctor.setName(row.getString("Name"));
        newDoctor.setDepartment(row.getString("Department"));
        newDoctor.setSpecialty(row.getString("Specialty"));
        
        return newDoctor;
    }
    
    public Staff selectStaff(String c) throws SQLException
    {
        ResultSet row = selectRows("Staff", null, c);
        Staff newStaff = new Staff();
        
        row.next();
        newStaff.setStaffId(row.getInt("StaffID"));
        newStaff.setName(row.getString("Name"));
        newStaff.setDepartment(row.getString("Department"));
        
        return newStaff;
    }
    
    public Patient selectPatient(String c) throws SQLException
    {
        ResultSet row = selectRows("Patient p, HealthCard h", null, "p.HealthCardNo = h.HealthCardNo and " + c);
        Patient newPatient = new Patient();
        
        row.next();
        newPatient.setName(row.getString("Name"));
        newPatient.setAddress(row.getString("Address"));
        newPatient.setDob(row.getDate("DOB"));
        newPatient.setPatientId(row.getInt("DoctorID"));
        newPatient.setSin(row.getInt("SIN"));
        newPatient.setHealthCardNo(row.getString("HealthCardNo"));
        newPatient.setHealthStatus(row.getString("HealthStatus"));
        newPatient.setPhoneNum(row.getInt("Phone"));
        newPatient.setDefDoctorId(row.getInt("DefDoctorID"));
        newPatient.setAuditTime(row.getDate("AuditTime"));
        newPatient.setAuditById(row.getInt("AuditByID"));
        
        return newPatient;
    }
    
    public List<Visit> selectVisits(String c)
    {
        ResultSet row = selectRows("Visit", null, c);
        List<Visit> records;
        
        while (row.next())
        {
            Visit newVisit = new Visit();

            newStaff.setStaffId(row.getInt("StaffID"));
            newStaff.setName(row.getString("Name"));
            newStaff.setDepartment(row.getString("Department"));
        }
        return records;
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