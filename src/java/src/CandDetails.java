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
    private String canFirstName;
    private String canLastName;  
    private String canEmailId;
    private String canZip;
    private String canCity;
    private String canState;
    private String canRace;
    private String canInElection;

    public CandDetails() {
    }

    public CandDetails(String canFirstName, String canLastName, String canEmailId, String canZip, String canCity, String canState, String canRace, String canInElection) {
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.canEmailId = canEmailId;
        this.canZip = canZip;
        this.canCity = canCity;
        this.canState = canState;
        this.canRace = canRace;
        this.canInElection = canInElection;
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

    public String getCanZip() {
        return canZip;
    }

    public void setCanZip(String canZip) {
        this.canZip = canZip;
    }

    public String getCanCity() {
        return canCity;
    }

    public void setCanCity(String canCity) {
        this.canCity = canCity;
    }

    public String getCanState() {
        return canState;
    }

    public void setCanState(String canState) {
        this.canState = canState;
    }

    public String getCanRace() {
        return canRace;
    }

    public void setCanRace(String canRace) {
        this.canRace = canRace;
    }

    public String getCanInElection() {
        return canInElection;
    }

    public void setCanInElection(String canInElection) {
        this.canInElection = canInElection;
    }
    
    
    
}
