/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dto;

import java.io.Serializable;

/**
 *
 * @author Nguyen Khoi
 */
public class UserDTO implements Serializable{
    private String email;
    private String password;
    private String fullName;
    private String roleID;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String fullName, String roleID, boolean status) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roleID = roleID;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
