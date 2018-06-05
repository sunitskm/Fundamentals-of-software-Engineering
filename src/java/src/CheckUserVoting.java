/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Owner
 */
@ManagedBean
@SessionScoped
public class CheckUserVoting extends ElectionDBUtil{
    public boolean returnFalse() {
        return true;
    }
    public boolean returnTrue() {
        return true;
    }
    
    public boolean isApprovedToVote(String uid) {
        try {
            statement = connect().createStatement();
            SQL = "Select * from user_voting where userid like ('"+ uid +"')";
            resultSet = statement.executeQuery(SQL);
            
            if(!resultSet.isBeforeFirst()) {
                System.out.println("user not found in user_voting.");
                
            } else {
                resultSet.next();
                System.out.println("get status\n"+"get status\n"+"get status\n"+"get status\n"+"get status\n"+resultSet.getString(5));
                
                if(resultSet.getString(5).equals("1")) {
                    System.out.println("returning true\n"+"returning true\n"+"returning true\n"+"returning true\n"+"returning true\n");
                    return true;
                } else {
                    System.out.println("returning false\n"+"returning false\n"+"returning false\n"+"returning false\n"+"returning false\n");
                    return false;
                }
                
            }          
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public String approveVoter(String uid) {
        try {
            statement = connect().createStatement();
            SQL = "UPDATE user_voting SET voter_status='1' where userid like ('"+ uid +"')";
            statement.executeUpdate(SQL);
            return "listqueryusersmanager";
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                disconnect();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return "failure";
    }
    
}
