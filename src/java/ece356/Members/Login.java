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
public class Login {
    int loginId;
    String password;
    
    public Login(int loginId, String password){
        this.loginId = loginId;
        this.password = password;
    }
    
    public int getLoginId(){return loginId;}
    public String getPassword(){return password;}
    
    public void setLoginId(){this.loginId = loginId;}
    public void setPassword(){this.password = password;}
}
