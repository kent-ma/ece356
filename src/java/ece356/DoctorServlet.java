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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            List<Patient> patients = new LinkedList<>();
            List<Doctor> doctors = new LinkedList<>();
            try
            {
                // Get a list of doctors.
                ResultSet rsDoctors = dbcon.selectRows("Doctor", null, null);
                while (rsDoctors.next()) {
                    Doctor d = new Doctor();
                    d.setName(rsDoctors.getString("Name"));
                    d.setDoctorId(rsDoctors.getInt("DoctorID"));
                    d.setDepartment(rsDoctors.getString("Department"));
                    d.setSpecialty(rsDoctors.getString("Specialty"));
                    
                    doctors.add(d);
                }
                
                // Get doctor's doctor id.
                // Should fix this in database.
                String doctorName = "";
                if (credentials.getName().equals("kjun")) {
                    doctorName = "Kim Jong-un";
                } else if (credentials.getName().equals("obladen")) {
                    doctorName = "Osama Bin Laden";
                }
                
                Doctor currentDoctor = dbcon.selectDoctor("Name = "+"'"+doctorName+"'");
                int doctorID = currentDoctor.getDoctorId();
                
                // Get patient IDs for the doctor.
                ResultSet rsPatientIDs = dbcon.selectRows("DoctorPatient", "PatientID", "DoctorID = "+"'"+doctorID+"'");
                ArrayList<Integer> patientIDs = new ArrayList<Integer>();
                while (rsPatientIDs.next()) {
                    patientIDs.add(rsPatientIDs.getInt("PatientID"));
                }
                
                // Get patients for the doctor.
                for (int p : patientIDs) {
                    Patient patient = dbcon.selectPatient("p.PatientID = "+"'"+p+"'");
                    if (patient != null)
                        patients.add(patient);
                }
                
                // Put patients and doctors in the request. 
                request.setAttribute("record", patients);
                request.setAttribute("doctorlist", doctors);
                
                request.setAttribute("name", credentials.getName());
                getServletContext().getRequestDispatcher("/doctor/doctor_grantaccess.jsp").forward(request, response);
            } catch (Exception ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
        }
        else if (requestType == 3) {
            //list all the patients
            //To doctor_viewpatient.jsp
            List<Patient> patients = new LinkedList<>();
            List<Doctor> doctors = new LinkedList<>();
            try
            {   
                // Get doctor's doctor id.
                // Should fix this in database.
                String doctorName = "";
                if (credentials.getName().equals("kjun")) {
                    doctorName = "Kim Jong-un";
                } else if (credentials.getName().equals("obladen")) {
                    doctorName = "Osama Bin Laden";
                }
                
                Doctor currentDoctor = dbcon.selectDoctor("Name = "+"'"+doctorName+"'");
                int doctorID = currentDoctor.getDoctorId();
                
                // Get patient IDs for the doctor.
                ResultSet rsPatientIDs = dbcon.selectRows("DoctorPatient", "PatientID", "DoctorID = "+"'"+doctorID+"'");
                ArrayList<Integer> patientIDs = new ArrayList<Integer>();
                while (rsPatientIDs.next()) {
                    patientIDs.add(rsPatientIDs.getInt("PatientID"));
                }
                
                // Get patients for the doctor.
                for (int p : patientIDs) {
                    Patient patient = dbcon.selectPatient("p.PatientID = "+"'"+p+"'");
                    if (patient != null)
                        patients.add(patient);
                }
                
                request.setAttribute("record", patients);
                request.setAttribute("name", credentials.getName());
                getServletContext().getRequestDispatcher("/doctor/doctor_viewpatient.jsp").forward(request, response);
            } catch (Exception ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
        } else if (requestType == 4) {
            // From doctor_searchpatient.jsp
            grantAccess(request, response, dbcon, credentials);
        } else if (requestType == 5) {
            // From doctor_addvisitrecord.jsp
            addVisitsRecord(request, response, dbcon, credentials);
        } else if (requestType == 6) {
            // From doctor_viewpatient.jsp
            // Return a list of records for that patient.
            String patientID = request.getParameter("patientID");
            searchVisitRecord(patientID, request, response, dbcon);
        } else if (requestType == 7) {
            getServletContext().getRequestDispatcher("/doctor/doctor_searchpatient.jsp").forward(request, response);
        } else if (requestType == 8) {
            // From doctor_searchpatient.jsp
            String patientName = request.getParameter("patient_name");
            String visitDate = request.getParameter("patient_visitdate");
            String diagnosis = request.getParameter("diagnosis");
            String commentKeyword = request.getParameter("comment_keyword");
            String prescriptions = request.getParameter("prescriptions");
            String surgery = request.getParameter("sugery");
            
            searchVisitsWithCriteria(patientName, visitDate, diagnosis, commentKeyword, prescriptions, surgery, request, response, dbcon);
        } else if (requestType == 9) {
            getServletContext().getRequestDispatcher("/doctor/doctor.jsp").forward(request, response);
        }
    }     
    
     public void searchVisitsWithCriteria(String patientName, String visitDate, 
             String diagnosis, String commentKeyword, String prescriptions, 
             String surgery, HttpServletRequest request, 
             HttpServletResponse response, DatabaseConnection dbcon) 
             throws ServletException, IOException {
         
         List<Appointment> appts = null;
         List<Visit> visits = new LinkedList<Visit>();
         String sql = "SELECT * FROM Visit WHERE ";
         String sqlApptID = "(ApptID = "; 
         String sqlTime = "(ArrivalTime >= "+"'"+visitDate+" 00:00:00'"+" AND ArrivalTime <= "+"'"+visitDate+" 23:59:59')"; // visitDate
         String sqlProcedure = "(V_Procedure LIKE '%"+diagnosis+"%' OR V_Procedure LIKE '%"+surgery+"%')"; // diagnosis, surgery
         String sqlResult = "(Result LIKE '%"+diagnosis+"%' OR Result LIKE '%"+surgery+"%')"; // diagnosis, surgery
         String sqlPrescription = "(Prescription LIKE "+"'%"+prescriptions+"%')"; // prescriptions
         String sqlComment = "(V_Comment LIKE "+"'%"+commentKeyword+"%')"; //commentKeyword
         
        try {
            // Get patient ids using patient name. 
            Patient p = dbcon.selectPatient("h.Name = "+"'"+patientName+"'");
            int patientID = p.getPatientId();
            
            // Get appt ids using patient ids. 
            appts = dbcon.selectAppointments("p.PatientID = "+"'"+patientID+"'");
            int[] apptIDs = new int[appts.size()];
            for (int i = 0; i < appts.size(); i++) {
                apptIDs[i] = appts.get(i).getApptId();
            }
            
            for (int i = 0; i < apptIDs.length; i++) {
                sqlApptID = sqlApptID+"'"+apptIDs[i]+"'";
                if (i != (apptIDs.length - 1)) {
                    sqlApptID = sqlApptID+" OR ApptID = ";
                } else {
                    sqlApptID = sqlApptID+")";
                }
            }
            
            // Format final query. 
            sql = sqlApptID+" OR "+sqlTime+" OR "+sqlProcedure+" OR "+sqlResult+" OR "+sqlPrescription+" OR "+sqlComment+";";
            ResultSet rsVisits = dbcon.selectRows("Visit", null, sql);
            
            while (rsVisits.next()) {
                // TODO
                Visit newVisit = new Visit();

                newVisit.setApptID(rsVisits.getInt("ApptID"));
                newVisit.setArrivalTime(rsVisits.getDate("ArrivalTime"));
                newVisit.setDepartTime(rsVisits.getDate("DepartTime"));
                newVisit.setProcedure(rsVisits.getString("V_Procedure"));
                newVisit.setResult(rsVisits.getString("Result"));
                newVisit.setPrescription(rsVisits.getString("Prescription"));
                newVisit.setComment(rsVisits.getString("V_Comment"));
                newVisit.setAuditTime(rsVisits.getDate("AuditTime"));
                newVisit.setAuditByID(rsVisits.getInt("AuditByID"));

                visits.add(newVisit);
            }
            
            request.setAttribute("visits", visits);
            getServletContext().getRequestDispatcher("/doctor/doctor_recordtable.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
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
    public void searchVisitRecord(String patientID, HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon)
               throws ServletException, IOException {  
        
        List<Appointment> appts = null;
        List<Visit> visits = null;
        
        try {
            // Retrieve appointments by patient id. 
            appts = dbcon.selectAppointments("a.PatientID = '"+patientID+"'");
            
            // Retrieve records using ApptID.
            for (Appointment a : appts) {
                //ResultSet visit;
                visits = dbcon.selectVisits("ApptID = '"+a.getApptId()+"'");
            }
            
            // Display the visitation records on a table.
            request.setAttribute("visits", visits);
            getServletContext().getRequestDispatcher("/doctor/doctor_recordtable.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
  //grant access to another doctor 
    public void grantAccess(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon, Login credentials)
            throws ServletException, IOException {     
        
        String patientID = request.getParameter("patientID");
        String docID = request.getParameter("docID");
        
        String value = "'"+docID+"'" + "," + "'"+patientID+"'";
        String access = "granting access";
        try {
            //probably have to do a check for the access right?yes
            boolean docPatRelationExists = dbcon.checkExists("DoctorPatient", "*", "DoctorID = "+"'"+docID+"'"+" AND "+"PatientID = "+"'"+patientID+"'");
            
            if (!docPatRelationExists) {
                boolean grantSuccess = dbcon.insertRows("DoctorPatient", "DoctorID, PatientID", value); 
                if(grantSuccess == true)
                {
                    access = "access granted!";
                    //sucessfully granted!
                } else {   
                    //didn't grant
                    access = "request deny!";
                }
            }
                    
            request.setAttribute("access", access);
            getServletContext().getRequestDispatcher("/doctor/doctor.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("exception", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
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