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
    private int canId;
    private String canFirstName;
    private String canLastName;  
    private String canEmailId;
    private String canZip;
    private String canPrecinct;
    private String canCity;
    private String canState;
    private String canRace;
    private String canInElection;
    

    public CandDetails() {
    }

    public CandDetails(int canId,String canFirstName, String canLastName, String canEmailId, String canZip, String canPrecinct,String canCity, String canState, String canRace, String canInElection) {
        this.canId = canId;
        this.canFirstName = canFirstName;
        this.canLastName = canLastName;
        this.canEmailId = canEmailId;
        this.canZip = canZip;
        this.canPrecinct = canPrecinct;
        this.canCity = canCity;
        this.canState = canState;
        this.canRace = canRace;
        this.canInElection = canInElection;
    }

    public int getCanId() {
        return canId;
    }

    public void setCanId(int canId) {
        this.canId = canId;
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

    public String getCanPrecinct() {
        return canPrecinct;
    }

    public void setCanPrecinct(String canPrecinct) {
        this.canPrecinct = canPrecinct;
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
