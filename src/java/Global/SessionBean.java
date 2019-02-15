package Global;

import ModelLocal.SmtbUserDoitac;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
    
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("g_username").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_userid");
        } else {
            return null;
        }
    }

    public static String getIdDoitac() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_iddoitac");
        } else {
            return null;
        }
    }

    public static String getDoitac() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_doitac");
        } else {
            return null;
        }
    }

    public static String getKhachhang() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_khachhang");
        } else {
            return null;
        }
    }

    public static String getDaily() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_daily");
        } else {
            return null;
        }
    }

    public static String getIdDaily() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_iddaily");
        } else {
            return null;
        }
    }
    
    public static String getAdminDt() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_admindt");
        } else {
            return null;
        }
    }
    
    public static String getInternalUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("g_internaluser");
        } else {
            return null;
        }
    }

    public static SmtbUserDoitac getUseriNFOR() {
        HttpSession session = getSession();
        if (session != null) {
            return (SmtbUserDoitac) session.getAttribute("g_userinfor");
        } else {
            return null;
        }
    }

}
