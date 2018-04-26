/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Acer
 */
/**
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
*/
/**
 *
 * @author Jack Geati
 */
/**
@ManagedBean (name = "findelections")
@SessionScoped
public class FindElections implements Serializable{
    ListElections elections = new ListElections();
    private List<ElectionDetails> list = new ArrayList<>();
    
    public List<ElectionDetails> getList(){
        list = elections.listElections();
        return list;
    }
    
    public void setList(List<ElectionDetails> list){
        this.list = list;
    } 
}
*/