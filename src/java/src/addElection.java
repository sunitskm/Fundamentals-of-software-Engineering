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
public class addElection extends ElectionDBUtil{
    private String precinct;
    private String race;
    private String date;
    
    public String add() {
        try {
            statement = connect().createStatement();
            SQL = "INSERT INTO election (precinct,race,date) VALUES ('"+ precinct +"','"+ race +"','"+ date +"')";
            statement.executeUpdate(SQL);
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
                connection.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return "failure";
    }

    /**
     * @return the precinct
     */
    public String getPrecinct() {
        return precinct;
    }

    /**
     * @param precinct the precinct to set
     */
    public void setPrecinct(String precinct) {
        this.precinct = precinct;
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
    
}
