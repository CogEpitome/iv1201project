/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.jsoup.Jsoup;
import org.jsoup.safety.*;

/**
 *
 * @author Jonas
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class VerificationHandler {
        
    public String verifyInput(String in, String type){
        if(inputIsType(in, type)){
            String safe = Jsoup.clean(in, Whitelist.basic());
            return safe;
        } else {
            return "The input does not match the required input type of "+type.toString();
        }
    }
    
    private boolean inputIsType(String in, String type) {
        try{
            if(type.equalsIgnoreCase("string")){
                return true;
            } else if(type.equalsIgnoreCase("long")){
                Long.parseLong(in);
            } else {
                return false;
            }
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
}
