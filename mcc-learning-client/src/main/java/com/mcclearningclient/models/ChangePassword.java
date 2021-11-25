/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

/**
 *
 * @author William Yangjaya
 */
@Data
public class ChangePassword {

    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;

    public ChangePassword() {
    }

    public ChangePassword(String oldPassword, String newPassword, String repeatNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
    }

}
