/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * Contains common methods for the view layer classes.
 */
public class ViewUtils {
    /**
     * Sets a warning message that is presented to the client user.
     * @param summary   The warning to be displayed to the user.
     * @param detail    A more detailed message, not currently presented to the client.
     */
    public static void SetWarning(String summary, String detail){
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }
}
