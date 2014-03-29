/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Backend;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import ece356.Members.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author devin
 */
public class DatabaseConnection 
{
    public static final String url = "jdbc:mysql://localhost:3306/";
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
    
    public Login selectLogin(String c) throws SQLException
    {
        ResultSet row = selectRows("Login", null, c);
        Login newLogin = new Login();
        
        row.next();
        newLogin.setLoginId(row.getInt("LoginID"));
        newLogin.setPassword(row.getString("Password"));
        newLogin.setName(row.getString("Name"));
        newLogin.setUserType(row.getInt("UserType"));
        
        return newLogin;
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
        newPatient.setPatientId(row.getInt("PatientID"));
        newPatient.setSin(row.getInt("SIN"));
        newPatient.setHealthCardNo(row.getString("HealthCardNo"));
        newPatient.setHealthStatus(row.getString("HealthStatus"));
        newPatient.setPhoneNum(row.getLong("Phone"));
        newPatient.setDefDoctorId(row.getInt("DefDoctorID"));
        newPatient.setAuditTime(row.getDate("AuditTime"));
        newPatient.setAuditById(row.getInt("AuditByID"));
        
        return newPatient;
    }
    
    public List<Visit> selectVisits(String c) throws SQLException
    {
        ResultSet row = selectRows("Visit", null, c);
        List<Visit> records = new LinkedList<>();
        
        while (row.next())
        {
            Visit newVisit = new Visit();

            newVisit.setApptID(row.getInt("ApptID"));
            newVisit.setArrivalTime(row.getDate("ArrivalTime"));
            newVisit.setDepartTime(row.getDate("DepartTime"));
            newVisit.setProcedure(row.getString("V_Procedure"));
            newVisit.setResult(row.getString("Result"));
            newVisit.setPrescription(row.getString("Prescription"));
            newVisit.setComment(row.getString("V_Comment"));
            newVisit.setAuditTime(row.getDate("AuditTime"));
            newVisit.setAuditByID(row.getInt("AuditByID"));
            
            records.add(newVisit);
        }
        return records;
    }
    
    public List<Appointment> selectAppointments(String c) throws SQLException
    {
        ResultSet row = selectRows("Appointment", null, c);
        List<Appointment> records = new LinkedList<>();
        
        while (row.next())
        {
            Appointment newVisit = new Appointment();

            newVisit.setApptId(row.getInt("ApptID"));
            newVisit.setPatientId(row.getInt("PatientID"));
            newVisit.setDoctorId(row.getInt("DoctorID"));
            newVisit.setRoomNumber(row.getInt("RoomNumber"));
            newVisit.setApptDate(row.getDate("ApptDate"));
            newVisit.setApptType(row.getString("ApptType"));
            newVisit.setAuditTime(row.getDate("AuditTime"));
            newVisit.setAuditById(row.getInt("AuditByID"));
            
            records.add(newVisit);
        }
        return records;
    }
    
    public List<String> selectPrescriptions(String c) throws SQLException
    {
        ResultSet row = selectRows("Visit v, Appointment a", "prescription", "v.ApptId = a.ApptId and " + c);
        List<String> records = new LinkedList<>();
        
        while (row.next())
            records.add(row.getString("prescription"));
        
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