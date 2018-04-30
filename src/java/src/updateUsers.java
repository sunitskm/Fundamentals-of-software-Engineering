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
public class updateUsers extends ElectionDBUtil{
    private String address;
    private String city;
    private String state;
    private String zip;

    public String update(String id) {
        try {
            statement = connect().createStatement();
            
            if (!(address.equals(""))){
                SQL = "UPDATE userreg SET st_addr = '"+ address +"' where userid like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(city.equals(""))){
                SQL = "UPDATE userreg SET city = '"+ city +"' where userid like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(state.equals(""))){
                SQL = "UPDATE userreg SET state = '"+ state +"' where userid like '" + id + "'";
                statement.executeUpdate(SQL);
            }
            
            if (!(zip.equals(""))){
                SQL = "UPDATE userreg SET zip = '"+ zip +"' where userid like '" + id + "'";
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
        return "userdashboard";
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
