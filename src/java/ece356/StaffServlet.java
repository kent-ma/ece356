/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import ece356.Backend.DatabaseConnection;
import ece356.Members.Login;
import ece356.Members.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author devin
 */
public class StaffServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get session info
        DatabaseConnection dbcon = null;
        Login credentials = null;
        int requestType = 0;
        
        try {
           dbcon = (DatabaseConnection)request.getAttribute("dbcon");
           credentials = (Login)request.getAttribute("credentials");
           requestType = (int)request.getAttribute("requestType");
        }
        catch (Exception ex)
        {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            String url = "/error.jsp";
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        // get the request to perform
        
        // first entry
        if (requestType == 0)
        {
            String url = "/staff/staff.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        else if (requestType == 1)
        {
            Patient patient = null;
            try {
                patient = dbcon.selectPatient("name = '" + (String)request.getAttribute("patientName") + "'");
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                String url = "/error.jsp";
                request.setAttribute("exception", ex);
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            String url = "/staff/staff_patient_info.jsp";
            request.setAttribute("record", patient);
            getServletContext().getRequestDispatcher(url).forward(request, response);
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
