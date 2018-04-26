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
    private String race;
    private String canFirstName;
    private String canLastName;  
    private String canEmailId;
    private String vpFirstName;
    private String vpLastName;
    private int votes;

    public CandDetails() {
    }

    public CandDetails(String race, String canFirstName, String canLastName, String canEmailId, int votes) {
        this.race = race;
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.canEmailId = canEmailId;
        this.votes = votes;
    }
    public CandDetails(String race, String canFirstName, String canLastName, String canEmailId) {
        this.race = race; 
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.canEmailId = canEmailId;
        this.votes = 0;
    }
    
    public CandDetails(String race, String canFirstName, String canLastName, String canEmailId, String vpFirstName, String vpLastName, int votes) {
        this.race = race;
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.canEmailId = canEmailId;
        this.vpFirstName = vpFirstName;
        this.vpLastName = vpLastName;
        this.votes = votes;
    }
    
    public CandDetails(String race, String canFirstName, String canLastName, String canEmailId, String vpFirstName, String vpLastName) {
        this.race = race;
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.canEmailId = canEmailId;
        this.vpFirstName = vpFirstName;
        this.vpLastName = vpLastName;
        this.votes = 0;
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

    public String getCanEmailId() {
        return canEmailId;
    }

    public void setCanEmailId(String canEmailId) {
        this.canEmailId = canEmailId;
    }
     public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    
    public String getVpFirstName() {
        return vpFirstName;
    }

    public void setVpFirstName(String vpFirstName) {
        this.vpFirstName = vpFirstName;
    }
    
    public String getVpLastName() {
        return vpLastName;
    }

    public void setVpLastName(String vpLastName) {
        this.vpLastName = vpLastName;
    }
}
