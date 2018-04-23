/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Statement;
/**
 *
 * @author Owner
 */

@ManagedBean
@SessionScoped
public class verifyIdentity extends ElectionDBUtil{
    private String uidToVerify;
    private String emailToVerify;
    
    /**
     * @return the uidToVerify
     */
    public String getUidToVerify() {
        return uidToVerify;
    }

    /**
     * @param uidToVerify the uidToVerify to set
     */
    public void setUidToVerify(String uidToVerify) {
        this.uidToVerify = uidToVerify;
    }

    /**
     * @return the emailToVerify
     */
    public String getEmailToVerify() {
        return emailToVerify;
    }

    /**
     * @param emailToVerify the emailToVerify to set
     */
    
    public void setEmailToVerify(String emailToVerify) {
        this.emailToVerify = emailToVerify;
    }


    public String verify() {
        try {
            statement = connect().createStatement();
            SQL = "Select * from userreg where userid like ('" + uidToVerify +"')";
            resultSet = statement.executeQuery(SQL);
            
            if(!resultSet.isBeforeFirst()) { // user not found
                return "verifyIdentityFailure";
            } else { // user exists
                resultSet.next();
                System.out.println("resultSet(4): " + resultSet.getString(4) + "\nemailToVerify: " + emailToVerify + "\n");
                if(resultSet.getString(4).equals(emailToVerify)) { 
                    statement.close();
                    Statement securityQStatement = connect().createStatement();
                    SQL = "Select * from users_security_questions where userid like ('"+ uidToVerify +"')";
                    resultSet = securityQStatement.executeQuery(SQL);
                    
                    if(!resultSet.isBeforeFirst()) { // if user has not set up security questions go to setup
                        return "securityQuestionSetup";
                    } else {
                        return "askSecurityQuestions";
                    }
                    
                    
                    //return "success_reg";
                } else {
                    return "verifyIdentityFailure";
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "success";
    }
}
