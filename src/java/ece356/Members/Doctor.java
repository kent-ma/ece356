/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356.Members;

/**
 *
 * @author Hades
 */
public class Doctor {
    private int doctorId;
    private String name;
    private String department;
    private String specialty;
    
    public Doctor(int doctorId, String name, String department, String specialty){
        this.doctorId = doctorId;
        this.name = name;
        this.department = department;
        this.specialty = specialty;
    }
    
    public Doctor() {
        this.doctorId = 0;
        this.name = null;
        this.department = null;
        this.specialty = null;
    }
    
    public int getDoctorId(){return doctorId;}
    public String getName(){return name;}
    public String getDepartment(){return department;}
    public String getSpecialty(){return specialty;}
    
    public void setDoctorId(int doctorId){this.doctorId = doctorId;}
    public void setName(String name){this.name = name;}
    public void setDepartment(String department){this.department = department;}
    public void setSpecialty(String specialty){this.specialty = specialty;}
    
    
    
}
