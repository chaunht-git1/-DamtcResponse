package Controler;

import DatabaseDao.LoginDao;
import DatabaseDao.SmtbUserDoitacDao;
import Global.SessionBean;
import ModelLocal.SmtbUserDoitac;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String username;
    private String password;

    public String checklogin() {

//        String kq = "TRUE";
        Boolean kq = LoginDao.validate(username, password);
        HttpSession session = SessionBean.getSession();
        SmtbUserDoitac smtbUserDoitac = new SmtbUserDoitac();
        SmtbUserDoitacDao dao = new SmtbUserDoitacDao();

        if (kq) {
            smtbUserDoitac=null;
           
            smtbUserDoitac = dao.laythongtinUser(username.toUpperCase());
//            session.setAttribute("g_username", smtbUserDoitac.getUsername());
//            session.setAttribute("g_userid", smtbUserDoitac.getUserid());
//            session.setAttribute("g_iddoitac", smtbUserDoitac.getIddoitac());
//            session.setAttribute("g_doitac", smtbUserDoitac.getDoitac());
//            session.setAttribute("g_khachhang", smtbUserDoitac.getKhachhang());
//            session.setAttribute("g_daily", smtbUserDoitac.getDaily());
//            session.setAttribute("g_iddaily", smtbUserDoitac.getIddaily());
//            session.setAttribute("g_admindt", smtbUserDoitac.getAdmindt());
//            session.setAttribute("g_internaluser", smtbUserDoitac.getInternaluser());
            session.setAttribute("g_userinfor", smtbUserDoitac);
            
            return "index" + "?faces-redirect=true";
        } else {
            return "login" + "?faces-redirect=true";
        }

    }
    
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login" + "?faces-redirect=true";
    }

    //get set
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
