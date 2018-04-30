/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lenovo
 */
public class ApproveReject extends ElectionDBUtil{
    PreparedStatement pstmt;
    public ApproveReject(){
    try{
         
            statement = connect().createStatement(); 
        /*SQL = "Select * from USERREG where approved like ('0')";
        VoterDetails v;
        resultSet = statement.executeQuery(SQL);*/
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void approve(String uid){
        String app = "1";
        try{
        SQL = "UPDATE USERREG " +
              " set approved = ? "+
               "where userid = ?";
        pstmt = connect().prepareStatement(SQL);
        pstmt.setString(1,app);
        pstmt.setString(2,uid);
        pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
        
        
        try{
             statement = connect().createStatement(); 
        SQL = "Select email_id from USER01 where userid like ('" + uid +"')";
        resultSet = statement.executeQuery(SQL);
        resultSet.next();
        String email = resultSet.getString(1).toString();
        sendEmail.sendApprovedEmail(email);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
      
    }
    public void reject(String uid){
        String reject = "-1";
        try{
        SQL = "UPDATE USERREG " +
              " set approved = ? "+
               "where userid = ?";
        pstmt = connect().prepareStatement(SQL);
        pstmt.setString(1,reject);
        pstmt.setString(2,uid);
        pstmt.executeUpdate();
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
        
        
        
        try{
             statement = connect().createStatement(); 
        SQL = "Select email_id from USER01 where userid like ('" + uid +"')";
        resultSet = statement.executeQuery(SQL);
        resultSet.next();
        String email = resultSet.getString(1).toString();
        sendEmail.sendRejectedEmail(email);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
        
        
       try{
             statement = connect().createStatement(); 
             System.out.println("User getting deleted " + uid);
             
        SQL = "DELETE from USERREG"+
                " where userid like ('" + uid +"')";
        statement.executeUpdate(SQL);
        System.out.println("Deleted");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
    }
}
