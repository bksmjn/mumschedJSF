package cs425.mumsched.web.usermgmt.boundary;

import cs425.mumsched.web.StartUpBean;
import cs425.mumsched.web.usermgmt.control.UserFinder;
import cs425.mumsched.web.usermgmt.control.UserManager;
import cs425.mumsched.web.usermgmt.entity.User;
import cs425.mumsched.web.utils.Messages;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author praman
 */
@Named
public class PreferenceBean {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    @Autowired
    private UserFinder userFinder;
    @Autowired
    private UserManager userManager;
    @Autowired
    private Messages message;
    @Autowired
    StartUpBean startUpBean;

    public void saveButtonClickedHandler() {
        try {
            User u = this.userFinder.findUserByEmailAddress(this.startUpBean.getUserName());
            if (u.getPassword().equals(oldPassword)) {
                u.setPassword(newPassword);
                this.userManager.updateUser(u);
            }
            message.addInfo(null, "Change Password", "Password Changed Successfully");
        } catch (Exception ex) {
            message.addError(null, "Change Password", "Error occured while changing password");
        }
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
