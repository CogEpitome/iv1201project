package kthknugarna.iv1201project.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import kthknugarna.iv1201project.controller.LoginController;
import kthknugarna.iv1201project.model.SessionUtils;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * This class is responsible for handling the presentation logic for the login web page.
 * It uses a LoginController object to access lower layers in the architecture.
 * 
 * @see login.xhtml
 * @see LoginController.java
 */
@ManagedBean
@SessionScoped
public class LoginView implements Serializable{
    @EJB
    private LoginController controller;
    public String username;
    private String password;
    
    /**
     * Validates the entered username and password. If credentials are valid,
     * create a http session and return role (used for paging)
     * @return a string used for paging
     */
    public String validateUsernamePassword(){
            try{
                boolean valid = controller.login(username, password);
                String role;
                if(valid){
                    HttpSession session = SessionUtils.getSession();

                    session.setAttribute("username", username);
                    role = controller.getRoleName(username);
                    return role;
                } else{
                    ViewUtils.SetWarning("Incorrect"
                                    + " username and password", "Please enter correct"
                                            + " username and password");
                    return "login";
                }
            } catch (Exception e){
                ViewUtils.SetWarning("An error occured while logging in, it was probably our bad! Try reloading the page or contact us for support."+e.getMessage(), e.getMessage());
                return "login";
            }
    }
    
    /**
     * Invalidate the http session
     * @return String "login"
     */
    public String logout() {
        try{
            HttpSession session = SessionUtils.getSession();
            session.invalidate();
            return "login";
        } catch(Exception e){
            ViewUtils.SetWarning("An error occured while logging out, it was probably our bad! Try reloading the page or contact us for support.", e.getMessage());
            return "login";
        }
    }
    
    
    public LoginController getLoginController(){
        return controller;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
