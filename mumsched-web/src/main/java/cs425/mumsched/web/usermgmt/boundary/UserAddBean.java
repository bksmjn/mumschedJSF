package cs425.mumsched.web.usermgmt.boundary;

import cs425.mumsched.web.email.boundary.ClientBean;
import cs425.mumsched.web.email.control.MailService;
import cs425.mumsched.web.usermgmt.control.CourseFinder;
import cs425.mumsched.web.usermgmt.control.UserManager;
import cs425.mumsched.web.usermgmt.entity.Course;
import cs425.mumsched.web.usermgmt.entity.Role;
import cs425.mumsched.web.usermgmt.entity.User;
import cs425.mumsched.web.utils.Messages;
import cs425.mumsched.web.utils.UIHelperUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@ViewScoped
public class UserAddBean implements Serializable {

    private User user;

    @Autowired
    private UserManager userManager;

    @Autowired
    private Messages messages;

    public UserAddBean() {

    }

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public void saveButtonClickedHandler() throws MessagingException {
        try {
            this.userManager.addUser(user);
            messages.addInfo(null, "User", "User Added Successfully");
            String message="Username:"+user.getEmailAddress()+ " Password:"+user.getPassword();
            MailService.sendMessage(user.getEmailAddress(), "Login Credential", message);
            this.user = new User();
        } catch (IllegalArgumentException ex) {
            messages.addError(null, "User", ex.getMessage());
        } catch (AccessDeniedException e) {
            messages.addError(null, "User", e.getMessage());
        }catch(Exception ex){
            messages.addError(null, "User", ex.getMessage());
        }
    }


    public SelectItem[] getAllRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.STUDENT);
        roles.add(Role.PROFESSOR);
        roles.add(Role.ROLE_ADMIN);
        return UIHelperUtils.toArrayOfSelectItem(roles);
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

}
