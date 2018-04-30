/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lenovo
 */
@ManagedBean (name = "apprejectbean")
@SessionScoped
public class AppRejectBean implements Serializable {
    public AppRejectBean(){
    
    }
    public String approve(String uid){
        System.out.println("Inside approve of app reject bean");
        ApproveReject ap = new ApproveReject();
        ap.approve(uid);
        /*Additional Code to add a user to user_voting. This only occurs if a user 
        has been approved by an Admin*/
        try {
            String SQL = "INSERT INTO user_voting VALUES ('"+uid+"','0','0','0','0')";
            ap.statement.executeUpdate(SQL);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                ap.statement.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        /********************************************************************/
        
        
         return "approveUsers" ;
    }
     public String reject(String uid){
         System.out.println("Inside reject of app reject bean");
        ApproveReject ap = new ApproveReject();
        ap.reject(uid);
         return "approveUsers" ;
    }
}
