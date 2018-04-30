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
public class addCandidate extends ElectionDBUtil{
    private String race;
    private String firstName;
    private String lastName;
    private String state;
    private String city;
    private String zip;

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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String addCandidate() {
        try {
            statement = connect().createStatement();
            SQL = "INSERT INTO candidates (race,first_name,last_name,state,city,zip) VALUES ('"+ race +"','"+ firstName +"','"+ lastName +"','"+ state +"','"+ city +"','"+ zip +"')";
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
