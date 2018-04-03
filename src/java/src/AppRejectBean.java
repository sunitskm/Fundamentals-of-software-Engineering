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
         return "listunapproveduser" ;
    }
     public String reject(String uid){
         System.out.println("Inside reject of app reject bean");
        ApproveReject ap = new ApproveReject();
        ap.reject(uid);
         return "listunapproveduser" ;
    }
}
