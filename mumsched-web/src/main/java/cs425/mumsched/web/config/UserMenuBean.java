package cs425.mumsched.web.config;

import cs425.mumsched.web.StartUpBean;
import cs425.mumsched.web.usermgmt.control.UserFinder;
import cs425.mumsched.web.usermgmt.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@Scope("prototype")
public class UserMenuBean implements Serializable {

    private MenuModel menuModel;
    private List<ApplicationMenu> menus;
    @Autowired
    private UserFinder userFinder;

    @PostConstruct
    private void init() {
        this.menuModel = new DefaultMenuModel();
        this.menus = new ArrayList<>();
        User u = this.userFinder.findUserByEmailAddress(StartUpBean.getUserName());
        System.out.println("ROLE:" + StartUpBean.getRole());
        if (u.getRole().equals("ROLE_ADMIN")) {
            this.addTopLevelMenuForADMIN();

        } else if (u.getRole().equals("PROFESSOR")) {
            this.addTopLevelMenuForProfessor();
        } else {
            this.addTopLevelMenuForStudents();
        }
//    this.addTopLevelMenu();
        this.initializeMenu();

    }

    private void initializeMenu() {
        List<ApplicationMenu> topLevelMenus = this.findAllTopLevelApplicationMenu(ApplicationMenu.MenuType.Root);
        for (ApplicationMenu app : topLevelMenus) {
            DefaultSubMenu rootLevelMenu = new DefaultSubMenu(app.getLabel());
            rootLevelMenu.setStyle("border: none;");

            //For entries
            DefaultSubMenu entriesSubMenu = new DefaultSubMenu("Entries");
            rootLevelMenu.addElement(entriesSubMenu);
            List<ApplicationMenu> entries_menus = this.findApplicationMenuByTypeAndParenCode(ApplicationMenu.MenuType.Entries, app.getCode());
            for (ApplicationMenu menu : entries_menus) {
                entriesSubMenu.addElement(new DefaultMenuItem(menu.getLabel(), "ui-icon-pencil", menu.getUrl()));
            }

            //For queries           
            DefaultSubMenu queriesSubMenu = new DefaultSubMenu("Queries");
            rootLevelMenu.addElement(queriesSubMenu);
            List<ApplicationMenu> queries_menus = this.findApplicationMenuByTypeAndParenCode(ApplicationMenu.MenuType.Queries, app.getCode());
            for (ApplicationMenu menu : queries_menus) {
                queriesSubMenu.addElement(new DefaultMenuItem(menu.getLabel(), "ui-icon-help", menu.getUrl()));
            }
            this.menuModel.addElement(rootLevelMenu);
        }
    }

    private void addTopLevelMenuForADMIN() {
        String[] menu_codes = {"User", "Preferences", "CourseManagement", "BlockManagement ", "EntryManagement", "ScheduleManagement","SectionManagement"};
        String[] menu_levels = {"User Management", "Preferences", "Course Management", "Block Management", "Entry Management", "Schedule Management","Section Management"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenuForADMIN(menu_codes);
    }

    private void addTopLevelMenuForStudents() {
        String[] menu_codes = {"My Profile", "Course Registration", "Preferences"};
        String[] menu_levels = {"My Profile", "Course Registration", "Preferences"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenuForStudent(menu_codes);
    }

    private void addSubMenuForADMIN(String[] menu_codes) {
        this.addUserStructure(menu_codes[0]);
        this.addPreference(menu_codes[1]);
        this.addCourseStructure(menu_codes[2]);
        this.addBlockStructure(menu_codes[3]);
        this.addEntryStructure(menu_codes[4]);
        this.addScheduleStructure(menu_codes[5]);
        this.addSections(menu_codes[6]);
    }

    private void addSubMenuForStudent(String[] menu_codes) {
        this.addStudentProfile(menu_codes[0]);
        this.addCourseRegistrationStructure(menu_codes[1]);
        this.addPreference(menu_codes[2]);
    }

    private void addTopLevelMenuForProfessor() {
        String[] menu_codes = {"Choose Block", "Preferences"};
        String[] menu_levels = {"Choose Block", "Preferences"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenuForProfessor(menu_codes);
    }

    private void addSubMenuForProfessor(String[] menu_codes) {
        this.addChooseBlockForProfessor(menu_codes[0]);
        this.addPreference(menu_codes[1]);
    }

    private void addTopLevelMenu() {
        String[] menu_codes = {"User", "Preferences", "Chapters"};
        String[] menu_levels = {"User Management", "Preferences", "Chapters"};
        int index = 0;
        for (String s : menu_codes) {
            this.menus.add(new ApplicationMenu(s, menu_levels[index], null));
            index++;
        }
        this.addSubMenu(menu_codes);
    }

    private void addSubMenu(String[] menu_codes) {
        this.addUserStructure(menu_codes[0]);
        this.addPreference(menu_codes[1]);
        this.addChapters(menu_codes[2]);

    }

    private void addUserStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("user_manage", "Add User", parentCode, "/faces/users/useradd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("user_manage", "Add Course", parentCode, "/faces/courses/courseadd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("user_list", "List User", parentCode, "/faces/users/userlist.xhtml", " ", ApplicationMenu.MenuType.Queries));
        this.menus.add(new ApplicationMenu("role_list", "List Course", parentCode, "/faces/courses/courselist.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addCourseStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("course_manage", "Add Course", parentCode, "/faces/courses/courseadd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("course_list", "List Course", parentCode, "/faces/courses/courselist.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addBlockStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("block_manage", "Add Block", parentCode, "/faces/blocks/addblock.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("block_list", "List Block", parentCode, "/faces/blocks/listblock.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addEntryStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("entry_manage", "Add Entry", parentCode, "/faces/blocks/entryadd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("entry_list", "List Entry", parentCode, "/faces/blocks/entrylist.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addScheduleStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("schedule_manage", "Create Schedule", parentCode, "/faces/schedules/createschedule.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("schedule_list", "List Schedule", parentCode, "/faces/schedules/listschedule.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addStudentProfile(String parentCode) {

        this.menus.add(new ApplicationMenu("student_profile", "Add Profile", parentCode, "/faces/profiles/studentprofile.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("view_profile", "Show Profile", parentCode, "/faces/profiles/listprofile.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addCourseRegistrationStructure(String parentCode) {

        this.menus.add(new ApplicationMenu("register_course", "Register Course", parentCode, "/faces/courses/registercourse.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("list_chosen_course", "List Chosen Course", parentCode, "/faces/courses/chosencourselist.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addPreference(String parentCode) {
        this.menus.add(new ApplicationMenu("change_password", "Change Password", parentCode, "/faces/preferences/changepassword.xhtml", " ", ApplicationMenu.MenuType.Entries));

    }

    private void addSections(String parentCode) {
        this.menus.add(new ApplicationMenu("list_sections", "List Sections", parentCode, "/faces/sections/listsection.xhtml", " ", ApplicationMenu.MenuType.Queries));

    }

    private void addChapters(String parentCode) {
        this.menus.add(new ApplicationMenu("chapter_manage", "Add Chapter", parentCode, "/faces/chapters/chapteradd.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("chapter_list", "List Chapter", parentCode, "/faces/chapters/chapterlist.xhtml", " ", ApplicationMenu.MenuType.Queries));
    }

    private void addChooseBlockForProfessor(String parentCode) {
        this.menus.add(new ApplicationMenu("choose_block", "Choose Block", parentCode, "/faces/blocks/chooseblock.xhtml", " ", ApplicationMenu.MenuType.Entries));
        this.menus.add(new ApplicationMenu("choose_block", "View Block", parentCode, "/faces/blocks/viewblock.xhtml", " ", ApplicationMenu.MenuType.Queries));
    }

    private List<ApplicationMenu> findApplicationMenuByTypeAndParenCode(ApplicationMenu.MenuType menuType, String parentCode) {
        List<ApplicationMenu> filteredMenus = new ArrayList<>();
        for (ApplicationMenu menu : this.menus) {
            if (menu.getMenuType() == menuType && StringUtils.equals(menu.getParentCode(), parentCode)) {
                filteredMenus.add(menu);
            }

        }
        return filteredMenus;
    }

    private List<ApplicationMenu> findAllTopLevelApplicationMenu(ApplicationMenu.MenuType menuType) {
        List<ApplicationMenu> filteredMenus = new ArrayList<>();
        for (ApplicationMenu menu : this.menus) {
            if (menu.getMenuType() == menuType) {
                filteredMenus.add(menu);
            }
        }
        return filteredMenus;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public List<ApplicationMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<ApplicationMenu> menus) {
        this.menus = menus;
    }

    public UserFinder getUserFinder() {
        return userFinder;
    }

    public void setUserFinder(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

}
