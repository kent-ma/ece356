/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Members;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Hades
 */
public class Patient {
    private String name;
    private String address;
    private Date dob;
    private int patientId;
    private int sin;
    private String healthCardNo;
    private String healthStatus;
    private int phoneNum;
    private int defDoctorId;
    private Date auditTime;
    private int auditById;
    
    public Patient(String name, String address, Date dob, int patientId, int sin, 
            String healthCardNo,String healthStatus, int phoneNum, int defDoctorId, 
            Date auditTime, int auditById){
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.patientId = patientId;
        this.sin = sin;
        this.healthCardNo = healthCardNo;
        this.healthStatus = healthStatus;
        this.phoneNum = phoneNum;
        this.defDoctorId = defDoctorId;
        this.auditTime = auditTime;
        this.auditById = auditById;
    }
    
    public Patient() {
        this.name = null;
        this.address = null;
        this.dob = null;
        this.patientId = 0;
        this.sin = 0;
        this.healthCardNo = null;
        this.healthStatus = null;
        this.phoneNum = 0;
        this.defDoctorId = 0;
        this.auditTime = null;
        this.auditById = 0;
    }
    
    public String getName(){return name;}
    public String getAddress(){return address;}
    public Date getDob(){return dob;}
    public int getPatientId(){return patientId;}
    public int getSin(){return sin;}
    public String getHealthCardNo(){return healthCardNo;}
    public String getHealthStatus(){return healthStatus;}
    public int getPhoneNum(){return phoneNum;}
    public int getDefDoctorId(){return defDoctorId;}
    public Date getAuditTime(){return auditTime;}
    public int getAuditById(){return auditById;}
    
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setDob(Date dob){this.dob = dob;}
    public void setPatientId(int patientId){this.patientId = patientId;}
    public void setSin(int sin){this.sin = sin;}
    public void setHealthCardNo(String healthCardNo){this.healthCardNo = healthCardNo;}
    public void setHealthStatus(String healthStatus){this.healthStatus = healthStatus;}
    public void setPhoneNum(int phoneNum){this.phoneNum = phoneNum;}
    public void setDefDoctorId(int defDoctorId){this.defDoctorId = defDoctorId;}
    public void setAuditTime(Date auditTime){this.auditTime = auditTime;}
    public void setAuditById(int auditById){this.auditById = auditById;}
}
