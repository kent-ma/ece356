/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Members;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author devin
 */
public class Appointment {
    
    private int apptId;
    private String doctorName;
    private String patientName;
    private int roomNumber;
    private Timestamp apptDate;
    private String apptType;
    private Timestamp auditTime;
    private int auditById;
    private int active;
    
    public Appointment(int apptId, String doctorName, String patientName, int roomNumber, 
            Timestamp apptDate, String apptType, Timestamp auditTime, int auditById, int active)
    {
        this.apptId = apptId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.roomNumber = roomNumber;
        this.apptDate = apptDate;
        this.apptType = apptType;
        this.auditTime = auditTime;
        this.auditById = auditById;
        this.active = active;
    }
    
    public Appointment()
    {
        this.apptId = 0;
        this.doctorName = null;
        this.patientName = null;
        this.roomNumber = 0;
        this.apptDate = null;
        this.apptType = null;
        this.auditTime = null;
        this.auditById = 0;
        this.active = 0;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Timestamp apptDate) {
        this.apptDate = apptDate;
    }

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public int getAuditById() {
        return auditById;
    }

    public void setAuditById(int auditById) {
        this.auditById = auditById;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    
    
    
}
