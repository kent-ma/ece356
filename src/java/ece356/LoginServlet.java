/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;

import ece356.Backend.DatabaseConnection;
import ece356.Members.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hades
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = "";
        
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String url;
        
        try {
            DatabaseConnection dbcon = new DatabaseConnection();
            Login credentials = dbcon.selectLogin("name = '" + name + "' AND " + "password = '" + password + "'");
            url = "error.jsp";
            
            switch(credentials.getUserType()){
                case 0:
                    url = "/PatientServlet";
                    break;
                case 1:
                    url = "/StaffServlet";
                    break;
                case 2:
                    url = "/DoctorServlet";
                    break;
                case 3:
                    url = "/LegalServlet";
                    break;
                case 4:
                    url = "/FinancialServlet";
                    break;
                default:
                    //throw error
                    break;
            }
            
            // these should be persistent
            request.setAttribute("dbcon", dbcon);
            request.setAttribute("credentials", credentials);
            request.setAttribute("requestType", 0);
            
            getServletContext().getRequestDispatcher(url).include(request, response);
            //getServletContext().getRequestDispatcher(url).forward(request, response);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            url = "/error.jsp";
            request.setAttribute("exception", ex);
            
        } catch (SQLException ex) {
            url = "/error.jsp";
            request.setAttribute("exception", ex);
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
