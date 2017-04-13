package cs425.mumsched.web.utils;

import cs425.mumsched.web.StartUpBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
public class TemplateController {
    
    @Autowired
    StartUpBean startUpBean;

    public TemplateController() {
    }

    public String getUserName() {
        return startUpBean.getUserName();
    }

}
