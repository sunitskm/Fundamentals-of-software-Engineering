/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lenovo
 */
@ManagedBean (name = "approveusers")
@SessionScoped
public class ApproveUsers implements Serializable{
    ListVoters voters = new ListVoters();
    private List<VoterDetails> list = new ArrayList<VoterDetails>();
    public List<VoterDetails> getList(){
     list = voters.listVoters();
     return list;
    }
    public void setList(List<VoterDetails> list){
        this.list = list;
    } 
}
