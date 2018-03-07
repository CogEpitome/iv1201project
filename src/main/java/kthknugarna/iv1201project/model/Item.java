package kthknugarna.iv1201project.model;


/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * Represents an item in the list of competences in applications.xhtml
 * 
 * @see applications.xhtml
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
