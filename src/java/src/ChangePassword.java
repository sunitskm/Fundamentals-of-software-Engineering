/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Owner
 */

@ManagedBean
@SessionScoped
public class ChangePassword extends ElectionDBUtil{
    private String newPassword;
    private String newPasswordConfirm;
    private static final String SALT = "my-secret-text";
    
    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the newPasswordConfim
     */
    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    /**
     * @param newPasswordConfim the newPasswordConfim to set
     */
    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
    
    public String verifyAndUpdate(String uid) {
        if(newPassword.equals(newPasswordConfirm)) {
            try {
                statement = connect().createStatement();
                String hashedPassword = generateHash(SALT + newPassword);
                SQL = "UPDATE user01 SET password='"+hashedPassword+"' WHERE userid like ('"+ uid +"') ";
                statement.executeUpdate(SQL);
                return "passwordChangeSuccess";

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } 
        return "changePassword";  
    }
    
    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                            'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                    byte b = hashedBytes[idx];
                    hash.append(digits[(b & 0xf0) >> 4]);
                    hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        }

        return hash.toString();
    }   
}
