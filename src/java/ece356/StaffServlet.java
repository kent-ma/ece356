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
        
        DatabaseConnection dbCon = null;
        Login credentials = null;
        int requestType = 0;
        
        try
        {
            dbCon = (DatabaseConnection)getServletContext().getAttribute("dbcon");
            credentials = (Login)getServletContext().getAttribute("credentials");
            if (credentials.getUserType() != 1)
                throw new Exception("Bad user type.");
            if (request.getParameter("requestType") == null)
                requestType = (int)request.getAttribute("requestType");
            else
                requestType = Integer.parseInt(request.getParameter("requestType"));
        }
        catch (Exception ex)
        {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            String url = "/error.jsp";
            request.setAttribute("exception", ex);
            getServletContext().getRequestDispatcher(url).include(request, response);
        }
        
        if (requestType == 0)
        {
            String url = "/staff/staff.jsp";
            request.setAttribute("name", credentials.getName());
            getServletContext().getRequestDispatcher(url).include(request, response);
        }
        else if (requestType == 1)
        {
            Patient record = null;
            try
            {
                record = dbCon.selectPatient("name = '" + (String)request.getParameter("patientName") + "'");
                
                String url = "/staff/staff_patient_info.jsp";
                request.setAttribute("name", record.getName());
                request.setAttribute("hcn", record.getHealthCardNo());
                getServletContext().getRequestDispatcher(url).include(request, response);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                String url = "/error.jsp";
                request.setAttribute("exception", ex);
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
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
