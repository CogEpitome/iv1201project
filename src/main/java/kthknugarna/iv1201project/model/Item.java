/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model;


/**
 *
 * @author Anton
 */
public class Item {

    private String value;
    private double experience;

    public String getValue() {
        return value;
    }
    
    public double getExperience(){
        return experience;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void setExperience(double experience){
        this.experience = experience;
    }

    @Override
    public String toString() {
        return value;
    }

}
