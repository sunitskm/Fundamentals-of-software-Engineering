/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Lenovo
 */
public class CandDetails {
    private int id;
    private String race;
    private String canFirstName;
    private String canLastName;  
    private String state;
    private String city;
    private String zip;
    private int votes;

    public CandDetails() {
        System.out.println("In cand detail");
    }

    public CandDetails(int id, String race, String canFirstName, String canLastName, String state, String city,String zip, int votes) {
        this.id = id;
        this.race = race;
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.votes = votes;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
    
    public String getCanFirstName() {
        return canFirstName;
    }

    public void setCanFirstName(String canFirstName) {
        this.canFirstName = canFirstName;
    }

    public String getCanLastName() {
        return canLastName;
    }

    public void setCanLastName(String canLastName) {
        this.canLastName = canLastName;
    }

     public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
