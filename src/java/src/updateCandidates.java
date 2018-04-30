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
public class updateCandidates extends ElectionDBUtil{
    private String race;
    private String firstName;
    private String lastName;
    private String state;
    private String city;
    private String zip;
    private int id;

    public String update() {
        try {
            statement = connect().createStatement();
            
            if (!(race.equals(""))){
                SQL = "UPDATE candidates SET race = '"+ race +"' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(firstName.equals(""))){
                SQL = "UPDATE candidates SET first_name = '"+ firstName +"' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(lastName.equals(""))){
                SQL = "UPDATE candidates SET last_name = '"+ lastName +"' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(state.equals(""))){
                SQL = "UPDATE candidates SET state = '"+ state +"' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(city.equals(""))){
                SQL = "UPDATE candidates SET city = '"+ city +"' where id like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(zip.equals(""))){
                SQL = "UPDATE candidates SET zip = '"+ zip +"' where id like '" + id + "'";
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }
    
    
}
