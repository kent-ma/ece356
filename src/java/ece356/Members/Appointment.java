/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Members;

import java.util.Date;

/**
 *
 * @author devin
 */
public class Appointment {
    
    private int apptId;
    private int doctorId;
    private int patientId;
    private int roomNumber;
    private Date apptDate;
    private String apptType;
    private Date auditTime;
    private int auditById;
    
    public Appointment(int apptId, int doctorId, int patientId, int roomNumber, 
            Date apptDate, String apptType, Date auditTime, int auditById)
    {
        this.apptId = apptId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.roomNumber = roomNumber;
        this.apptDate = apptDate;
        this.apptType = apptType;
        this.auditTime = auditTime;
        this.auditById = auditById;
    }
    
    public Appointment()
    {
        this.apptId = 0;
        this.doctorId = 0;
        this.patientId = 0;
        this.roomNumber = 0;
        this.apptDate = null;
        this.apptType = null;
        this.auditTime = null;
        this.auditById = 0;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
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

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    
    
    
}
