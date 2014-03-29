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
    private int loginId;
    private String password;
    private String name;
    private int userType;
    
    public Login(int loginId, String password, String name, int userType){
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }
    
    public Login(){
        this.loginId = 0;
        this.password = null;
        this.name = null;
        this.userType = 0;
    }
    
    public int getLoginId(){return loginId;}
    public String getPassword(){return password;}
    
    public void setLoginId(int loginId){this.loginId = loginId;}
    public void setPassword(String password){this.password = password;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
