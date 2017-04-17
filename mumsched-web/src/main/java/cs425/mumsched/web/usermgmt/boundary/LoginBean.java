package cs425.mumsched.web.usermgmt.boundary;

import cs425.mumsched.web.StartUpBean;
import cs425.mumsched.web.usermgmt.control.UserFinder;
import cs425.mumsched.web.usermgmt.control.UserManager;
import cs425.mumsched.web.usermgmt.entity.User;
import cs425.mumsched.web.utils.Messages;
import java.io.Serializable;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bikesh
 */
@Named
public class LoginBean implements Serializable {

    private String userName;
    private String password;

    @Autowired
    UserManager um;

    @Autowired
    UserFinder userFinder;

    @Autowired
    StartUpBean startUpBean;

    @Autowired
    private Messages messages;

    @Autowired
    Messages message;

    public LoginBean() {
        System.out.println("LOGIN BEAN CREATED");
    }

    public String doLogin() {
        try {
            System.out.println("doLogin Method called");
            User u = this.userFinder.findUserByEmailAddress(userName);
            if (u.getPassword().equals(password)) {
                startUpBean.setUserName(u.getEmailAddress());
                startUpBean.setRole(u.getRole());
                  return "/faces/home.xhtml?faces-redirect=true";
            }else{
                this.messages.addError(null, "Login", "Invalid Credentials");
            }

        } catch (Exception ex) {
            messages.addError(null, "User", "Invalid Username/Password");
        }
      return null;

    }
    
    public String doLogout(){
        try{
            
        }catch(Exception ex){
            
        }
        return null;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserManager getUm() {
        return um;
    }

    public void setUm(UserManager um) {
        this.um = um;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

}
