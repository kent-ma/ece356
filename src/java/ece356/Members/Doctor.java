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
    private int staffId;
    private String name;
    private String department;
    
    public Doctor(int staffId, String name, String department){
        this.staffId = staffId;
        this.name = name;
        this.department = department;
    }
    
    public int getStaffId(){return staffId;}
    public String getName(){return name;}
    public String getDepartment(){return department;}
    
    public void setStaffId(int staffId){this.staffId = staffId;}
    public void setName(String name){this.name = name;}
    public void setDepartment(String department){this.department = department;}
    
    
    
}
