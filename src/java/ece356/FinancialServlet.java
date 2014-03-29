/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import ece356.Backend.DatabaseConnection;
import ece356.Members.Appointment;
import ece356.Members.Login;
import ece356.Members.Visit;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author devin
 */
public class FinancialServlet extends HttpServlet {

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
            if (credentials.getUserType() != 4)
                throw new Exception("Bad user type.");
            
            request.setAttribute("name", credentials.getName());
        } catch (Exception ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        if (request.getParameter("requestType") != null) {
            requestType = Integer.parseInt(request.getParameter("requestType"));
        }
        
        if (requestType == 0) {
            // To financial.jsp
            getServletContext().getRequestDispatcher("/financial/financial.jsp").forward(request, response);
        } else if (requestType == 1) {
            // To financial_doctor.jsp
            getServletContext().getRequestDispatcher("/financial/financial_doctor.jsp").forward(request, response);
        } else if (requestType == 2) {
            // To financial_patient.jsp
            getServletContext().getRequestDispatcher("/financial/financial_patient.jsp").forward(request, response);
        } else if (requestType == 3) {
            // From financial_doctor.jsp
            searchVisitsByDoctorID(request, response, dbcon);
        } else if (requestType == 4) {
            // From financial_patient.jsp
            searchVisitsByPatientID(request, response, dbcon);
        } else if (requestType == 5) {
            // From financial_table.jsp
            getServletContext().getRequestDispatcher("/financial/financial.jsp").forward(request, response);
        }
    }

    private void searchVisitsByDoctorID(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon) 
            throws ServletException, IOException{
        
        String doctorID = request.getParameter("doctor_id");
        
        String startDateString = request.getParameter("start_date");
        String[] startDateArray = startDateString.split("/");
        String startYear = startDateArray[2];
        String startMonth = startDateArray[0];
        String startDay = startDateArray[1];
        
        String endDateString = request.getParameter("end_date");
        String[] endDateArray = endDateString.split("/");
        String endYear = endDateArray[2];
        String endMonth = endDateArray[0];
        String endDay = endDateArray[1];
        
        String startTime = "";
        String endTime = "";
        
        if (!startYear.equals("") && !startMonth.equals("") && !startDay.equals("") &&
                !endYear.equals("") && !endMonth.equals("") && !endDay.equals("")) {
            
            startTime = "'"+startYear+"-"+startMonth+"-"+startDay+"'";
            endTime = "'"+endYear+"-"+endMonth+"-"+endDay+"'";
        }
        
        List<Appointment> appts = null;
        List<Visit> visits = null;
        
        try {
            // Retrieve appointments by patient id. 
            appts = dbcon.selectAppointments("a.DoctorID = '"+doctorID+"'");
            
            // Retrieve records using ApptID.
            for (Appointment a : appts) {
                //ResultSet visit;
                if (startTime.equals("") || endTime.equals("")) {
                    visits = dbcon.selectVisits("ApptID = '"+a.getApptId()+"'");
                } else {
                    visits = dbcon.selectVisits("ApptID = '"+a.getApptId()+
                        "' AND "+"ArrivalTime >= "+startTime+" AND "+"ArrivalTime <= "+endTime);
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
    
    private void searchVisitsByPatientID(HttpServletRequest request, HttpServletResponse response, DatabaseConnection dbcon) 
            throws ServletException, IOException {
        
        String patientID = request.getParameter("patient_id");
        
        String startDateString = request.getParameter("start_date");
        String[] startDateArray = startDateString.split("/");
        String startYear = startDateArray[2];
        String startMonth = startDateArray[0];
        String startDay = startDateArray[1];
        
        String endDateString = request.getParameter("end_date");
        String[] endDateArray = endDateString.split("/");
        String endYear = endDateArray[2];
        String endMonth = endDateArray[0];
        String endDay = endDateArray[1];
        
        String startTime = "";
        String endTime = "";
        
        if (!startYear.equals("") && !startMonth.equals("") && !startDay.equals("") &&
                !endYear.equals("") && !endMonth.equals("") && !endDay.equals("")) {
            
            startTime = "'"+startYear+"-"+startMonth+"-"+startDay+"'";
            endTime = "'"+endYear+"-"+endMonth+"-"+endDay+"'";
        }
        
        List<Appointment> appts = null;
        List<Visit> visits = null;
        
        try {
            // Retrieve appointments by patient id. 
            appts = dbcon.selectAppointments("a.PatientID = '"+patientID+"'");
            
            // Retrieve records using ApptID.
            for (Appointment a : appts) {
                //ResultSet visit;
                if (startTime.equals("") || endTime.equals("")) {
                    visits = dbcon.selectVisits("ApptID = '"+a.getApptId()+"'");
                } else {
                    visits = dbcon.selectVisits("ApptID = '"+a.getApptId()+
                        "' AND "+"ArrivalTime >= "+startTime+" AND "+"ArrivalTime <= "+endTime);
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
