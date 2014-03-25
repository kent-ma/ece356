/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.Timestamp;
import ece356.Members.Visit;

/**
 *
 * @author cynthiachoi
 */
@WebServlet(name = "FinancialPatientServlet", urlPatterns = {"/FinancialPatientServlet"})
public class FinancialPatientServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
            DatabaseConnection dbcon = new DatabaseConnection();
            
            // Retrieve appointments by patient id. 
            ResultSet appt = dbcon.selectRows("Appointment", "ApptID", "PatientID = "+patientID);
            while (appt.next()) {
                apptID.add(appt.getInt("ApptID"));
            }
            
            // Retrieve records using ApptID.
            for (int a : apptID) {
                ResultSet visit;
                if (startTime.equals("") || endTime.equals("")) {
                    visit = dbcon.selectRows("Visit", null, "ApptID = "+a);
                } else {
                    visit = dbcon.selectRows("Visit", null, "ApptID = "+a+
                        " AND "+"ArrivalTime >= "+startTime+" AND "+"ArrivalTime <= "+endTime);
                }
                visit.next();
                
                // Create a Visit object and store the object into a list.
                int vApptID = visit.getInt("ApptID");
                Timestamp vArrivalTime = visit.getTimestamp("ArrivalTime");
                Timestamp vDepartTime = visit.getTimestamp("DepartTime");
                String vProcedure = visit.getString("Procedure");
                String vResult = visit.getString("Result"); 
                String vPrescription = visit.getString("Prescription");
                String vComment = visit.getString("Comment");
                Timestamp vAuditTime = visit.getTimestamp("AuditTime");
                int vAuditByID = visit.getInt("AuditByID");
                
                Visit v = new Visit(vApptID, vArrivalTime, vDepartTime, vProcedure, vResult, 
                                      vPrescription, vComment, vAuditTime, vAuditByID);
                
                visits.add(v);
            }
            
            // Display the visitation records on a table.
            request.getSession().setAttribute("visits", visits);
            getServletContext().getRequestDispatcher("/financial_table.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
