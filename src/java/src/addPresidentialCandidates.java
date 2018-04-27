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
public class addPresidentialCandidates extends ElectionDBUtil{

    private String pres_first_name;
    private String pres_last_name;
    private String email;
    private String vp_first_name;
    private String vp_last_name;
    /**
     * @return the pres_first_name
     */
    public String getPres_first_name() {
        return pres_first_name;
    }

    /**
     * @param pres_first_name the pres_first_name to set
     */
    public void setPres_first_name(String pres_first_name) {
        this.pres_first_name = pres_first_name;
    }

    /**
     * @return the pres_last_name
     */
    public String getPres_last_name() {
        return pres_last_name;
    }

    /**
     * @param pres_last_name the pres_last_name to set
     */
    public void setPres_last_name(String pres_last_name) {
        this.pres_last_name = pres_last_name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the vp_first_name
     */
    public String getVp_first_name() {
        return vp_first_name;
    }

    /**
     * @param vp_first_name the vp_first_name to set
     */
    public void setVp_first_name(String vp_first_name) {
        this.vp_first_name = vp_first_name;
    }

    /**
     * @return the vp_last_name
     */
    public String getVp_last_name() {
        return vp_last_name;
    }

    /**
     * @param vp_last_name the vp_last_name to set
     */
    public void setVp_last_name(String vp_last_name) {
        this.vp_last_name = vp_last_name;
    }

    public String addCandidate() {
        try {
            statement = connect().createStatement();
            String race = "United States President";
            int default_votes = 0;
            SQL = "INSERT INTO pres_cand (race,first_name,last_name,email,vp_first,vp_last) VALUES ('United States President','"+pres_first_name+"','"+pres_last_name+"','"+email+"','"+vp_first_name+"','"+vp_last_name+"')";
            statement.executeUpdate(SQL);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "admindashboard";
    }
    
}
