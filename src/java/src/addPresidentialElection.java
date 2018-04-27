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
public class addPresidentialElection extends ElectionDBUtil{

    /**
     * @return the electionType
     */
    public String getElectionType() {
        return electionType;
    }

    /**
     * @param electionType the electionType to set
     */
    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    /**
     * @return the race
     */
    public String getRace() {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    private String electionType;
    private String race;
    private String date;
    
    public String addElection() {
        try {
            statement = connect().createStatement();
            SQL = "INSERT INTO election (Election_Type,Race, State, District, Date, Winner_Id) VALUES ('"+ electionType +"','"+ race +"','"+ null +"','"+ null +"','"+ date +"','0')";
            statement.executeUpdate(SQL);
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return "admindashboard";
    }
}
