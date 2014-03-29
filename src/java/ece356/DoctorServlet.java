/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import ece356.Backend.DatabaseConnection;
import ece356.Members.Login;
import ece356.Members.Patient;
import ece356.Members.Visit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            // To doctor.jsp
            getServletContext().getRequestDispatcher("/doctor/doctor.jsp").forward(request, response);
        } else if (requestType == 1) {
            // To doctor_addvisitrecord
            getServletContext().getRequestDispatcher("/doctor/doctor_addvisitrecord.jsp").forward(request, response);
        } else if (requestType == 2) {
            // To doctor_grantaccess.jsp
            getServletContext().getRequestDispatcher("/doctor/doctor_grantaccess.jsp").forward(request, response);
        }else if (requestType == 3) {
            // To doctor_searchpatient.jsp
            getServletContext().getRequestDispatcher("/doctor/doctor_searchpatient.jsp").forward(request, response);
        }  
        else if (requestType == 4) {
            // From doctor_searchpatient.jsp
            searchPatient(request, response, dbcon);
        } else if (requestType == 5) {
            // From doctor_addvisitrecord.jsp
            addVisitRecord(request, response, dbcon);
        } else if (requestType == 6) {
            // From financial_table.jsp
            getServletContext().getRequestDispatcher("/doctor/doctor.jsp").forward(request, response);
        }
        else if (requestType == 7) {
            // From doctor_addvisitrecord.jsp
            grantAccess(request, response, dbcon);
        } 
        
    }     
    
    protected void  addVisitRecord(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon)
            throws ServletException, IOException {
            
    }
    
    
 private void searchVisitsRecord(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon) 
            throws ServletException, IOException {
        
        String startYear = request.getParameter("start_year");
        String startMonth = request.getParameter("start_month");
        String startDay = request.getParameter("start_day");
        String endYear = request.getParameter("end_year");
        String endMonth = request.getParameter("end_month");
        String endDay = request.getParameter("end_day");
        String patientID = request.getParameter("patient_id");
        
        String startTime = "";
        String endTime = "";
        
        if (!startYear.equals("") && !startMonth.equals("") && !startDay.equals("") &&
                !endYear.equals("") && !endMonth.equals("") && !endDay.equals("")) {
            
            startTime = "'"+startYear+"-"+startMonth+"-"+startDay+"'";
            endTime = "'"+endYear+"-"+endMonth+"-"+endDay+"'";
        }
        
        ArrayList<Integer> apptID = new ArrayList<Integer>();
        ArrayList<Visit> visits = new ArrayList<Visit>();
        
        try {
            // Get database connection.
            //DatabaseConnection dbcon = new DatabaseConnection();
            
            // Retrieve appointments by patient id. 
            ResultSet appt = dbcon.selectRows("Appointment", "ApptID", "PatientID = '"+patientID+"'");
            while (appt.next()) {
                apptID.add(appt.getInt("ApptID"));
            }
            
            // Retrieve records using ApptID.
            for (int a : apptID) {
                ResultSet visit;
                if (startTime.equals("") || endTime.equals("")) {
                    visit = dbcon.selectRows("Visit", null, "ApptID = '"+a+"'");
                } else {
                    visit = dbcon.selectRows("Visit", null, "ApptID = '"+a+
                        "' AND "+"ArrivalTime >= "+startTime+" AND "+"ArrivalTime <= "+endTime);
                }
                
                if (visit.next()) {
                    // Create a Visit object and store the object into a list.
                    int vApptID = visit.getInt("ApptID");
                    Timestamp vArrivalTime = visit.getTimestamp("ArrivalTime");
                    Timestamp vDepartTime = visit.getTimestamp("DepartTime");
                    String vProcedure = visit.getString("V_Procedure");
                    String vResult = visit.getString("Result"); 
                    String vPrescription = visit.getString("Prescription");
                    String vComment = visit.getString("V_Comment");
                    Timestamp vAuditTime = visit.getTimestamp("AuditTime");
                    int vAuditByID = visit.getInt("AuditByID");

                    Visit v = new Visit(vApptID, vArrivalTime, vDepartTime, vProcedure, vResult, 
                                          vPrescription, vComment, vAuditTime, vAuditByID);

                    visits.add(v);
                }
            }
            
            // Display the visitation records on a table.
            request.setAttribute("visits", visits);
            getServletContext().getRequestDispatcher("/financial/financial_table.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    
    protected void  searchPatient(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon)
            throws ServletException, IOException {
        
    
    }
    
    //grant access to another doctor 
    protected void grantAccess(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon)
            throws ServletException, IOException {
        
        String patientID = request.getParameter("patientID");
        String docID = request.getParameter("doctorID");
        String value = docID + "," + patientID;
        try {
                    //probably have to do a check for the access right?
                    boolean grantSuccess = dbcon.insertRows("DoctorPatient", "DoctorID, PatientID","");
                    //TODO
                    //if return 1 direct to success page?
                    //if return 0, access deny page?
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }
        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
       // response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
        /*try {
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FinancialServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinancialServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        } finally {            
            out.close();
        }*/
    

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