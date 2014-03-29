/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356.Members;

import java.sql.Timestamp;

/**
 *
 * @author kentma
 */
public class Prescription {
    private String prescription;
    private String doctor;
    private Timestamp date;

    public Prescription(String prescription, String doctor, Timestamp date) {
        this.prescription = prescription;
        this.doctor = doctor;
        this.date = date;
    }

    public Prescription() {
        prescription = null;
        doctor = null;
        date = null;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    
    
    
}
