package cs425.mumsched.web;

import cs425.mumsched.web.usermgmt.control.UserManager;
import cs425.mumsched.web.usermgmt.entity.Role;
import cs425.mumsched.web.usermgmt.entity.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bikesh
 */

@SessionScoped
public class StartUpBean implements Serializable{

    @Autowired
    private UserManager usermanager;
    private String userName;
    private String role;

    public StartUpBean() {

    }

    @PostConstruct
    public void init() {
        User u = new User();
        u.setEmailAddress("admin@gmail.com");
        u.setUserName("bksmjn");
        u.setPassword("12345");
        u.setRole(Role.ROLE_ADMIN.toString());
        usermanager.addUser(u);

        User u1 = new User();
        u1.setEmailAddress("student@gmail.com");
        u1.setUserName("student");
        u1.setPassword("12345");
        u1.setRole(Role.STUDENT.toString());
        usermanager.addUser(u1);

        User u2 = new User();
        u2.setEmailAddress("prof@gmail.com");
        u2.setUserName("professor");
        u2.setPassword("12345");
        u2.setRole(Role.PROFESSOR.toString());
        usermanager.addUser(u2);
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
