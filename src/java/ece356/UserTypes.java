/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;

/**
 *
 * @author kentma
 */
public enum UserTypes {
    Patient, Staff, Doctor, Legal, Financial;
    
    public static UserTypes fromInteger(int x){
        switch (x){
            case 0:
                return Patient;
            case 1:
                return Staff;
            case 2:
                return Doctor;
            case 3:
                return Legal;
            case 4:
                return Financial;
            default:
                return Patient;
        }
    }
}
