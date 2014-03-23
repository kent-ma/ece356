/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Members;

import java.sql.Timestamp;

/**
 *
 * @author cynthiachoi
 */
public class Visit {
    private int apptID;
    private Timestamp arrivalTime;
    private Timestamp departTime;
    private String procedure;
    private String result; 
    private String prescription;
    private String comment;
    private Timestamp auditTime;
    private int auditByID;
    
    public Visit (int apptID, Timestamp arrivalTime,Timestamp departTime, 
                    String procedure, String result, String prescription, 
                    String comment, Timestamp auditTime, int auditByID) {
        
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
    
    public int getApptID() {
        return apptID;
    }
    
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }
    
    public Timestamp getDepartTime() {
        return departTime;
    }
    
    public String getProcedure() {
        return procedure;
    }
    
    public String getResult() {
        return result;
    }
    
    public String getPrescription() {
        return prescription;
    }
    
    public String getComment() {
        return comment;
    }
    
    public Timestamp getAuditTime() {
        return auditTime;
    }
    
    public int getAuditByID() {
        return auditByID;
    }
}
