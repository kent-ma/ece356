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
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author devin
 */
public class StaffServlet extends HttpServlet {
   
    public enum StaffRequest
    {
        STAFF_INIT,
        STAFF_VIEW_PATIENTS,
        STAFF_VIEW_APPOINTMENTS,
        STAFF_EDIT_PATIENT,
        STAFF_UPDATE_PATIENT,
        STAFF_ASSIGN_DOCTOR,
        STAFF_EDIT_APPOINTMENT,
        STAFF_UPDATE_APPOINTMENT,
        STAFF_NEW_PATIENT,
        STAFF_NEW_APPOINTMENT
    }
    
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
            if (request.getParameter("requestType") == null)
                requestType = (int)request.getAttribute("requestType");
            else
                requestType = Integer.parseInt(request.getParameter("requestType"));
        }
        catch (Exception ex)
        {
            // assume request is 0 and go to main page
        }
        
        try
        {
            dbCon = (DatabaseConnection)getServletContext().getAttribute("dbcon");
            credentials = (Login)getServletContext().getAttribute("credentials");
            if (credentials.getUserType() != 1)
                throw new Exception("Bad user type.");
            
        }
        catch (Exception ex)
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }
        
        if (requestType == StaffRequest.STAFF_INIT.ordinal())   // init
        {
            doInit(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_VIEW_PATIENTS.ordinal())  // view patients
        {
            doViewPatients(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_VIEW_APPOINTMENTS.ordinal())  // view appointments
        {
            doViewAppointments(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_EDIT_PATIENT.ordinal()) // edit patient
        {
            doEditPatient(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_UPDATE_PATIENT.ordinal()) // update patient
        {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String dob = request.getParameter("dob");
            String sin = request.getParameter("sin");
            String hcn = request.getParameter("hcn");
            String status = request.getParameter("status");
            String defDoc = request.getParameter("defdoc");
            String pId = request.getParameter("patientId");
            
            // for settings user names for new patients
            String[] subNames = name.split(" ");
            String userName = "";
            for (int i = 0; i < subNames.length - 1; i++)
            {
                userName += subNames[i].substring(0, 1).toLowerCase();
            }
            userName += subNames[subNames.length - 1].toLowerCase();
            
            try
            {
                // ensure the default doctor has access to the patient
                if (!dbCon.selectRows("DoctorPatient", null, "DoctorID = " + defDoc + " and PatientID = " + pId).next())
                    dbCon.insertRows("DoctorPatient", "DoctorID, PatientID", defDoc + ", " + pId);
                
                Login tempReset = dbCon.selectLogin("LoginID = " + pId);
                if (DigestUtils.sha256Hex(tempReset.getName()).equals(tempReset.getPassword()))
                    dbCon.updateRows("Login", "Name = '" + userName + "'", "LoginID = " + tempReset.getLoginId());
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                
                dbCon.updateRows("Patient p, HealthCard h", "h.Name = '" + name + "', h.Address = '" + address + "', p.Phone = " + phone + ", h.DOB = '" + dob + "', p.SIN = " + sin + ", h.HealthCardNo = " + hcn + ", p.HealthCardNo = " + hcn + ", p.DefDoctorID = " + defDoc + ", p.HealthStatus = '" + status + "', p.AuditTime = '" + dateFormat.format(date) + "', p.AuditByID = " + credentials.getLoginId(), "p.HealthCardNo = h.HealthCardNo and p.PatientID = " + pId);
            }
            catch (SQLException ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
            
            doViewPatients(request, response, dbCon, credentials);
            
        }
        
        else if (requestType == StaffRequest.STAFF_ASSIGN_DOCTOR.ordinal()) // assign patient to doctor
        {
            String pId = request.getParameter("patientID");
            String dId = request.getParameter("doctors");

            try 
            {
                if (!dbCon.selectRows("DoctorPatient", null, "PatientID = " + pId + " and DoctorID = " + dId).next())
                    dbCon.insertRows("DoctorPatient", "DoctorID, PatientID", dId + ", " + pId);
            } 
            catch (SQLException ex) 
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
            
            doViewPatients(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_EDIT_APPOINTMENT.ordinal()) // edit appointment
        {
            doEditAppointment(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_UPDATE_APPOINTMENT.ordinal()) // update appointment
        {
            String patient = request.getParameter("patient");
            String doctor = request.getParameter("doctor");
            String room = request.getParameter("room");
            String time = request.getParameter("time");
            String type = request.getParameter("type");
            String active = request.getParameter("active");
            String aId = request.getParameter("apptId");
            
            try
            {
                Patient p = dbCon.selectPatient("h.name = '" + patient + "'");
                Doctor d = dbCon.selectDoctor("DoctorID = '" + doctor + "'");
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                
                dbCon.updateRows("Appointment", "PatientID = " + p.getPatientId() + ", DoctorID = " + d.getDoctorId() + ", RoomNumber = " + room + ", ApptDate = '" + time + "', ApptType = '" + type + "', AuditTime = '" + dateFormat.format(date) + "', AuditByID = " + credentials.getLoginId() + ", Active = " + active, "ApptID = " + aId);
              
            }
            catch (SQLException ex)
            {
                Utils.showErrorPage(getServletContext(), ex, request, response);
            }
            
            doViewAppointments(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_NEW_PATIENT.ordinal()) // new patient
        {
            doNewPatient(request, response, dbCon, credentials);
        }
        
        else if (requestType == StaffRequest.STAFF_NEW_APPOINTMENT.ordinal()) // new appointment
        {
            doNewAppointment(request, response, dbCon, credentials);
        }
    }
    
    public void doInit(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        List<Doctor> record = new LinkedList<>();
        String staffName = "";
        try
        {
            // get a list of doctors for use in the servlet
            ResultSet result = dbCon.selectRows("Doctor", null, null);

            while (result.next())
            {
                Doctor newDoctor = new Doctor();

                newDoctor.setName(result.getString("Name"));
                newDoctor.setDoctorId(result.getInt("DoctorID"));

                record.add(newDoctor);
            }
            
            staffName = dbCon.selectStaff("StaffID = " + credentials.getLoginId()).getName();
        }
        catch (SQLException ex)
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/staff/staff.jsp";
        getServletContext().setAttribute("doctorlist", record);
        request.setAttribute("name", staffName);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }
    
    public void doViewPatients(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        String c = "";
        if (!request.getParameter("patientName").equals(""))
            c += " and Name = '" + request.getParameter("patientName") + "'";
        if (!request.getParameter("patientOHIP").equals(""))
            c += " and h.HealthCardNo = '" + request.getParameter("patientOHIP") + "'";

        List<Patient> record = null;
        try
        {
            record = dbCon.selectPatients(" and p.PatientID in (select PatientID from DoctorPatient where DoctorID in (select DoctorID from StaffDoctor where StaffID = " + credentials.getLoginId() + "))" + c);

            String url = "/staff/staff_patient_info.jsp";
            request.setAttribute("record", record);
            getServletContext().getRequestDispatcher(url).include(request, response);
        }
        catch (SQLException ex)
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }
    }
    
    public void doViewAppointments(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        String c = "a.Active = 1 and a.DoctorID = " + request.getParameter("doctors");
        if (!request.getParameter("apptTime").equals(""))
            c += " and ApptDate = '" + request.getParameter("apptTime") + "'";

        List<Appointment> record = null;
        try
        {
            record = dbCon.selectAppointments(c);

            String url = "/staff/staff_appt_info.jsp";
            request.setAttribute("record", record);
            getServletContext().getRequestDispatcher(url).include(request, response);
        }
        catch (SQLException ex)
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }
    }
    
    public void doEditPatient(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        String pId = request.getParameter("patientID");
        Patient p = null;

        try 
        {
            p = dbCon.selectPatient("p.HealthCardNo = h.HealthCardNo and p.PatientID = " + pId);
        } 
        catch (SQLException ex) 
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/staff/staff_edit_patient.jsp";
        request.setAttribute("patient", p);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }
    
    public void doEditAppointment(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        String aId = request.getParameter("apptID");
        Appointment a = null;

        try 
        {
            a = dbCon.selectAppointments("a.ApptId = " + aId).get(0);
        } 
        catch (SQLException ex) 
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/staff/staff_edit_appt.jsp";
        request.setAttribute("appointment", a);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }
    
    public void doNewPatient(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        Patient p = null;
        String password = "";
            
        try 
        {
            ResultSet newRows = dbCon.selectRows("Login", "MAX(LoginID)", null);
            newRows.next();
            p = new Patient();
            p.setPatientId(newRows.getInt("MAX(LoginID)") + 1);

            // get a random health card number for temporary use
            // will also be temporary password (user_HCN)
            int randomHCN = 1 + (int)(Math.random() * ((999999999 - 1) + 1));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            
            password = "user_" + randomHCN;

            dbCon.insertRows("Patient", "PatientID, HealthCardNo, AuditTime, AuditByID", p.getPatientId() + ", " + randomHCN + ", '" + dateFormat.format(date) + "', " + credentials.getLoginId());
            dbCon.insertRows("HealthCard", "HealthCardNo", "" + randomHCN);
            dbCon.insertRows("Login", "LoginID, Name, Password, UserType", p.getPatientId() + ", " + "'user_" + randomHCN + "', '" + DigestUtils.sha256Hex("user_" + randomHCN) + "', 0");
        } 
        catch (SQLException ex) 
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/staff/staff_edit_patient.jsp";
        request.setAttribute("patient", p);
        request.setAttribute("password", password);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }
    
    public void doNewAppointment(HttpServletRequest request, HttpServletResponse response, 
            DatabaseConnection dbCon, Login credentials) throws ServletException, IOException
    {
        Appointment a = null;
            
        try 
        {
            ResultSet newRows = dbCon.selectRows("Appointment", "MAX(ApptID)", null);
            newRows.next();
            a = new Appointment();
            a.setApptId(newRows.getInt("MAX(ApptID)") + 1);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();

            dbCon.insertRows("Appointment", "ApptID, AuditTime, AuditByID, Active", a.getApptId() + ", '" + dateFormat.format(date) + "', " + credentials.getLoginId() + ", 1");
        } 
        catch (SQLException ex) 
        {
            Utils.showErrorPage(getServletContext(), ex, request, response);
        }

        String url = "/staff/staff_edit_appt.jsp";
        request.setAttribute("appointment", a);
        getServletContext().getRequestDispatcher(url).include(request, response);
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
