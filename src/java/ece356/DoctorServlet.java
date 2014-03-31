/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import ece356.Backend.DatabaseConnection;
import ece356.Backend.Utils;
import ece356.Members.Appointment;
import ece356.Members.Doctor;
import ece356.Members.Login;
import ece356.Members.Patient;
import ece356.Members.Visit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author devin
 */
public class DoctorServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DatabaseConnection dbcon = null;
        Login credentials = null;
        int requestType = 0;
        
        try {
            dbcon = (DatabaseConnection)getServletContext().getAttribute("dbcon");
            credentials = (Login)getServletContext().getAttribute("credentials");
            if (credentials.getUserType() != 2)
                throw new Exception("Bad user type.");
        } catch (Exception ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        if (request.getParameter("requestType") != null) {
            requestType = Integer.parseInt(request.getParameter("requestType"));
        }   
        if (requestType == 0) {
            getServletContext().getRequestDispatcher("/doctor/doctor.jsp").forward(request, response);
        } else if (requestType == 1) {
            // To doctor_addvisitrecord
            getServletContext().getRequestDispatcher("/doctor/doctor_addvisitrecord.jsp").forward(request, response);
        } else if (requestType == 2) {  
            //Doctor can grant access to another doctor
            // To doctor_grantaccess.jsp
            List<Doctor> record = new LinkedList<>();
            List<Patient> recordPatient = null;
            try
            {   
                //recordPatient = dbcon.selectPatients("");   
                recordPatient = dbcon.selectPatients("and p.PatientID in (select PatientID from DoctorPatient where DoctorID in (select DoctorID from StaffDoctor where StaffID = " + credentials.getLoginId() + "))");
                String url = "/doctor/doctor_grantaccess.jsp";
                request.setAttribute("record", recordPatient);
                
                ResultSet result = dbcon.selectRows("Doctor", null, null);
                while (result.next())
                {
                    Doctor newDoctor = new Doctor();
                    newDoctor.setName(result.getString("Name"));
                    newDoctor.setDoctorId(result.getInt("DoctorID"));
                    record.add(newDoctor);
                }     
            }
            catch (SQLException ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }

            getServletContext().setAttribute("doctorlist", record);
            request.setAttribute("name", credentials.getName());
            getServletContext().getRequestDispatcher("/doctor/doctor_grantaccess.jsp").forward(request, response);
        }
        else if (requestType == 3) {
            //list all the patients
            //To doctor_viewpatient.jsp
            List<Patient> recordPatient = null;
            try
            {   
                recordPatient = dbcon.selectPatients("");
                //recordPatient = dbcon.selectPatients("and p.PatientID in (select PatientID from DoctorPatient where DoctorID in (select DoctorID from StaffDoctor where StaffID = " + credentials.getLoginId() + "))"");
                request.setAttribute("record", recordPatient);
            }
            catch (SQLException ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
            request.setAttribute("name", credentials.getName());
            getServletContext().getRequestDispatcher("/doctor/doctor_viewpatient.jsp").forward(request, response);
        }  
        else if (requestType == 4) {
            // From doctor_searchpatient.jsp
            grantAccess(request, response, dbcon, credentials);
        } else if (requestType == 5) {
            // From doctor_addvisitrecord.jsp
            addVisitsRecord(request, response, dbcon, credentials);
        } else if (requestType == 6) {
            //search patient based on critaria
            doSearchPatients(request, response, dbcon, credentials);
        }
        else if (requestType == 7) {
            // return all visit record of specific patient
            getServletContext().getRequestDispatcher("/doctor/doctor_viewvisitrecord.jsp").forward(request, response);
            searchVisitRecord(request, response, dbcon, credentials);
        } 
        
    }     
    
     public void doSearchPatients(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
        {
            String c = "";
            if (!request.getParameter("patientName").equals(""))
            c += " and Name = '" + request.getParameter("patientName") + "'";
            if (!request.getParameter("patientOHIP").equals(""))
            c += " and h.HealthCardNo = '" + request.getParameter("patientOHIP") + "'";
            
            //last visit ??
            if (!request.getParameter("patientName").equals(""))
            c += " and Name = '" + request.getParameter("patientName") + "'";
            
            List<Patient> record = null;
            try
            {
                record = dbCon.selectPatients(" and p.PatientID in (select PatientID from DoctorPatient where DoctorID in (select DoctorID from StaffDoctor where StaffID = " + credentials.getLoginId() + "))" + c);

                String url = "/doctor/searchpatient.jsp";
                request.setAttribute("record", record);
                getServletContext().getRequestDispatcher(url).include(request, response);
            }
            catch (SQLException ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
    }
    
    public void doInit(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        List<Patient> record = new LinkedList<>();
        String docName = "";
        try
        {
            // get a list of patients under the doctor for use in the servlet
            ResultSet result = dbCon.selectRows("Patient", null, null);
            while (result.next())
            {
                Patient newPatient = new Patient();
                newPatient.setName(result.getString("Name"));
                newPatient.setPatientId(result.getInt("DoctorID"));
                record.add(newPatient);
            }
            docName = dbCon.selectStaff("DocotorID = " + credentials.getLoginId()).getName();
        }
        catch (SQLException ex)
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/doctor/doctor_viewpatient.jsp";
        getServletContext().setAttribute("patientlist", record);
        request.setAttribute("name", docName);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }
    
 private void addVisitsRecord(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon, Login credentials) 
           throws ServletException, IOException {
            Visit v = null;   
             try 
             {
                //the set ID part might be pretty messed up....
                ResultSet newRows = dbcon.selectRows("Visit", "MAX(ApptID)", null);
                newRows.next();
                v = new Visit();
                v.setApptID(newRows.getInt("MAX(ApptID)") + 1);
                v.setProcedure(request.getParameter("visit_procedure"));
                v.setResult(request.getParameter("visit_result"));
                v.setPrescription(request.getParameter("visit_prescription"));
                v.setComment(request.getParameter("visit_comment"));
                
                String arrivalTime = "'"+request.getParameter("visit_arrival")+"'";
                String departTime = "'"+request.getParameter("visit_departure")+"'";
                //TODO deal with arrival time and departure time.... 
                //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //java.util.Date date = new java.util.Date();
                //Date arrival = dateFormat.parse(request.getParameter("visit_arrival"));
                //v.setArrivalTime(date);

                java.util.Date date= new java.util.Date(); 
                Timestamp currentTime = new Timestamp(date.getTime());
                String currentTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
                currentTimeString = "'"+currentTimeString+"'";
                
                dbcon.insertRows("Visit", 
                    "ApptID,ArrivalTime,DepartTime,V_Procedure,Result,Prescription,V_Comment,AuditTime,AuditByID",
                    "'"+v.getApptID()+"'" +"," + arrivalTime+","+ departTime+","+"'"+v.getProcedure()+"'"+","+"'"+v.getResult()+"'"+","+"'"+v.getPrescription()+"'"+","+"'"+v.getComment()+"'"+","+currentTimeString+","+"-1");
            
                getServletContext().getRequestDispatcher("/doctor/doctor.jsp").forward(request, response);
             } 
            catch (SQLException ex) 
            {
                request.setAttribute("exception", ex);
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
 
 //search visitation record from the selected patient
 public void searchVisitRecord(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon, Login credentials)
            throws ServletException, IOException {  
            String c = request.getParameter("patientID");
            List<Visit> record = null;
            try
            {
                record = dbcon.selectVisitsFromPatient(c);
                String url = "/doctor/doctor_searchpatient.jsp";
                request.setAttribute("record", record);
                getServletContext().getRequestDispatcher(url).include(request, response);
            }
            catch (Exception ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
 }
  //grant access to another doctor 
    public void grantAccess(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon, Login credentials)
            throws ServletException, IOException {     
        
        String patientID = request.getParameter("patientID");
        String docID = request.getParameter("docID");
        
        String value = docID + "," + patientID;
        String access = "granting access";
        try {
                    //probably have to do a check for the access right?
                boolean grantSuccess = dbcon.insertRows("DoctorPatient", "DoctorID, PatientID", value); 
                    if(grantSuccess == true)
                    {
                        access = "access granted!";
                        //sucessfully granted!
                    }
                    else
                    {   
                        //didn't grant
                        access = "request deny!";
                    }
                  
        } catch (Exception e) {
            request.setAttribute("exception", e);
            
        }
        request.setAttribute("access", access);
        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}