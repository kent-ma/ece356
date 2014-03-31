/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import ece356.Backend.DatabaseConnection;
import ece356.Backend.Utils;
import ece356.Members.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kent
 */
public class PatientServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            DatabaseConnection dbc = null;
            ServletContext context = getServletContext();
            int requestType = 0;

            if (request.getParameter("requestType") != null) {
                requestType = Integer.parseInt(request.getParameter("requestType"));
            }

            Login login = (Login) context.getAttribute(Utils.ATTR_CREDENTIALS);
            dbc = (DatabaseConnection) context.getAttribute(Utils.ATTR_DBC);

            if (requestType == 0) {
                doInit(request, response, dbc, login);
            } else if (requestType == 1) {
                String newAddr = request.getParameter("addr");
                String newPhone = request.getParameter("phonenum");

                if ((newAddr == null || newAddr.length() == 0) || (newPhone == null || newPhone.length() == 0)) {
                    doInit(request, response, dbc, login);
                }

                try {
                    Patient p = dbc.selectPatient("PatientID = " + login.getLoginId());
                    
                    if (!newAddr.equals(p.getAddress())) {
                        dbc.updateRows("Healthcard", "Address = " + newAddr, "HealthCardNo = " + p.getHealthCardNo());
                    }
                    if (!newPhone.equals(p.getPhoneNum())) {
                        dbc.updateRows("Patient", "Phone = " + newPhone, "PatientID = " + p.getPatientId());
                    }
                    doInit(request, response, dbc, login);

                } catch (SQLException e) {

                }
            }
        } catch (Exception e) {
            Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, e);
            Utils.showErrorPage(getServletContext(), e, request, response);
        }
    }

    public void doInit(HttpServletRequest request, HttpServletResponse response,
            DatabaseConnection dbc, Login credentials) throws ServletException, IOException {
        try {
            Patient p = dbc.selectPatient("PatientID = " + credentials.getLoginId());
            getServletContext().setAttribute("patient", p);
            List<Appointment> appts = dbc.selectAppointments("a.PatientID = " + credentials.getLoginId());
            getServletContext().setAttribute("appts", appts);
            List<Prescription> prescriptions = dbc.selectPrescriptions("a.PatientID = " + credentials.getLoginId());
            getServletContext().setAttribute("prescriptions", prescriptions);
        } catch (SQLException ex) {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/patient/patient.jsp";
        getServletContext().getRequestDispatcher(url).include(request, response);
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
