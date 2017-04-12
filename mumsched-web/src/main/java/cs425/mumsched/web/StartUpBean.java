package cs425.mumsched.web;

import cs425.mumsched.web.usermgmt.control.UserManager;
import cs425.mumsched.web.usermgmt.entity.Role;
import cs425.mumsched.web.usermgmt.entity.User;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bikesh
 */
public class StartUpBean {

    @Autowired
    private UserManager usermanager;
    private static String userName;
    private static String role;

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

    public UserManager getUsermanager() {
        return usermanager;
    }

    public void setUsermanager(UserManager usermanager) {
        this.usermanager = usermanager;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        StartUpBean.userName = userName;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        StartUpBean.role = role;
    }

}
