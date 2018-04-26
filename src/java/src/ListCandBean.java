/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Lenovo
 */
@SessionScoped
@Named("listcandbean")
public class ListCandBean implements Serializable{
 
    ListCand candidates = new ListCand();
    private List<CandDetails> list = new ArrayList<CandDetails>();
    public List<CandDetails> getList(){
     list = candidates.listCand();
     return list;
    }
    public void setList(List<CandDetails> list){
        this.list = list;
    } 


}
