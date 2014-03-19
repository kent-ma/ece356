/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Members;

import java.sql.Timestamp;

/**
 *
 * @author Hades
 */
public class Patient {
    private int patientId;
    private int sin;
    private String healthCardNo;
    private String healthStatus;
    private int phoneNum;
    private int defDoctorId;
    private Timestamp auditTime;
    private int auditById;
    
    public Patient(int patientId, int sin, String healthCardNo,String healthStatus, 
            int phoneNum, int defDoctorId, Timestamp auditTime, int auditById){
        this.patientId = patientId;
        this.sin = sin;
        this.healthCardNo = healthCardNo;
        this.healthStatus = healthStatus;
        this.phoneNum = phoneNum;
        this.defDoctorId = defDoctorId;
        this.auditTime = auditTime;
        this.auditById = auditById;
    }
    
    public int getPatientId(){return patientId;}
    public int getSin(){return sin;}
    public String getHealthCardNo(){return healthCardNo;}
    public String getHealthStatus(){return healthStatus;}
    public int getPhoneNum(){return phoneNum;}
    public int getDefDoctorId(){return defDoctorId;}
    public Timestamp getAuditTime(){return auditTime;}
    public int getAuditById(){return auditById;}
    
    public void setPatientId(int patientId){this.patientId = patientId;}
    public void setSin(int sin){this.patientId = patientId;}
    public void setHealthCardNo(String healthCardNo){this.patientId = patientId;}
    public void setHealthStatus(int healthCardStatus){this.patientId = patientId;}
    public void setPhoneNum(int phoneNum){this.patientId = patientId;}
    public void setDefDoctorId(int defDoctorId){this.defDoctorId = defDoctorId;}
    public void setAuditTime(Timestamp auditTime){this.auditTime = auditTime;}
    public void setAuditById(int auditById){this.auditById = auditById;}
}
