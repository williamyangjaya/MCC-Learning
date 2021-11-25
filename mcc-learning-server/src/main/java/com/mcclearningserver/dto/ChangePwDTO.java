/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

/**
 *
 * @author ASUS
 */
public class ChangePwDTO {

    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;

    public ChangePwDTO() {
    }

//    public ChangePwDTO(String oldPassword, String newPassword) {
//        this.oldPassword = oldPassword;
//        this.newPassword = newPassword;
//    }
//
//    public ChangePwDTO(String oldPassword) {
//        this.oldPassword = oldPassword;
//    }
    public ChangePwDTO(String oldPassword, String newPassword, String repeatNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }

}
