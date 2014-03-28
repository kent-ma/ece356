/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;
import ece356.Backend.DatabaseConnection;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.Timestamp;
import ece356.Members.Patient;
import ece356.Members.Visit;

/**
 *
 * @author Claire
 */
@WebServlet(name = "DoctorPatientServlet", urlPatterns = {"/DoctorPatientServlet"})
public class DoctorPatientServlet extends HttpServlet {

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
        
        String patientName = request.getParameter("patient_name");
        String patientID = request.getParameter("patient_id");
        
        String visitMonth = request.getParameter("start_month");
        String visitDay = request.getParameter("start_day");
        String visitYear = request.getParameter("end_year");
        
        
        String visitTime = "";
        String endTime = "";
        
        if (!visitYear.equals("") && !visitMonth.equals("") && !visitDay.equals("")) {
            
            visitTime = "'"+visitYear+"-"+visitMonth+"-"+visitDay+"'";
        }
        
        ArrayList<Visit> visitRecord = new ArrayList<Visit>();
        ArrayList<Patient> patients = new ArrayList<Patient>();
        
        try {
            // Get database connection.
            DatabaseConnection dbcon = new DatabaseConnection();
            ResultSet patient = dbcon.selectRows("Patient p", null, "p.Name =" +patientName+" and p.PatientID =" +patientID);

            // Retrieve patient record using patient id. 
            //patients.add(patientsResult);
            //gi
            // Retrieve records using ApptID.
            /*for (int a : apptID) {
                ResultSet visit;
                if (startTime.equals("") || endTime.equals("")) {
                    visit = dbcon.selectRows("Visit", null, "ApptID = "+a);
                } else {
                    visit = dbcon.selectRows("Visit", null, "ApptID = "+a+
                        " AND "+"ArrivalTime >= "+startTime+" AND "+"ArrivalTime <= "+endTime);
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

                    Patient p = new Patient(vApptID, vArrivalTime, vDepartTime, vProcedure, vResult, 
                                          vPrescription, vComment, vAuditTime, vAuditByID);

                    patients.add(p);
                }
            }
            
            // Display the visitation records on a table.
            request.getSession().setAttribute("patients", patients);
            getServletContext().getRequestDispatcher("/doctor_searchpatient.jsp").forward(request, response);*/
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

