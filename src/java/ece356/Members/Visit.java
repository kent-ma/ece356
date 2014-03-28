/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Members;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author cynthiachoi
 */
public class Visit {
    private int apptID;
    private Date arrivalTime;
    private Date departTime;
    private String procedure;
    private String result; 
    private String prescription;
    private String comment;
    private Date auditTime;
    private int auditByID;
    
    public Visit (int apptID, Date arrivalTime, Date departTime, 
                    String procedure, String result, String prescription, 
                    String comment, Date auditTime, int auditByID) {
        
        this.apptID = apptID;
        this.arrivalTime = arrivalTime;
        this.departTime = departTime;
        this.procedure = procedure;
        this.result = result;
        this.prescription = prescription;
        this.comment = comment;
        this.auditTime = auditTime;
        this.auditByID = auditByID;
    }
    
    public Visit () {
        this.apptID = 0;
        this.arrivalTime = null;
        this.departTime = null;
        this.procedure = null;
        this.result = null;
        this.prescription = null;
        this.comment = null;
        this.auditTime = null;
        this.auditByID = 0;
    }
    
    public int getApptID() {return apptID;}
    public Date getArrivalTime() {return arrivalTime;}
    public Date getDepartTime() {return departTime;}
    public String getProcedure() {return procedure;}
    public String getResult() {return result;}
    public String getPrescription() {return prescription;}
    public String getComment() {return comment;}
    public Date getAuditTime() {return auditTime;}
    public int getAuditByID() {return auditByID;}
    
    public void setApptID(int apptId) {this.apptID = apptID;}
    public void setArrivalTime(Date arrivalTime) {this.arrivalTime = arrivalTime;}
    public void setDepartTime(Date departTime) {this.departTime = departTime;}
    public void setProcedure(String procedure) {this.procedure = procedure;}
    public void setResult(String result) {this.result = result;}
    public void setPrescription(String prescription) {this.prescription = prescription;}
    public void setComment(String comment) {this.comment = comment;}
    public void setAuditTime(Date auditTime) {this.auditTime = auditTime;}
    public void setAuditByID(int auditByID) {this.auditByID = auditByID;}
}
