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
public class updateElection extends ElectionDBUtil{
    private String precinct;
    private String race;
    private String date;
    private int id;

    public String update() {
        try {
            statement = connect().createStatement();
            
            if (!(precinct.equals(""))){
                SQL = "UPDATE election SET precinct = '" + precinct + "' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(race.equals(""))){
                SQL = "UPDATE election SET race = '"+ race +"' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(date.equals(""))){
                SQL = "UPDATE election SET date = '" + race + "' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
}
