package ModelLocal;

import java.util.Date;

public class VwKieuhoiListMaster {

    private String iddoitac;
    private String doitacname;
    private String agent;
    private Date ngaybiennhan;
    private String dachitra;
    private Number shs;
    private Date tungay;
    private Date dengay;
    private String dachitratemp;
    private Boolean disdownload;

    public Boolean getDisdownload() {
        return disdownload;
    }

    public void setDisdownload(Boolean disdownload) {
        this.disdownload = disdownload;
    }

    public String getDachitratemp() {
        return dachitratemp;
    }

    public void setDachitratemp(String dachitratemp) {
        this.dachitratemp = dachitratemp;
    }

    public Date getTungay() {
        return tungay;
    }

    public void setTungay(Date tungay) {
        this.tungay = tungay;
    }

    public Date getDengay() {
        return dengay;
    }

    public void setDengay(Date dengay) {
        this.dengay = dengay;
    }

    public String getIddoitac() {
        return iddoitac;
    }

    public void setIddoitac(String iddoitac) {
        this.iddoitac = iddoitac;
    }

    public String getDoitacname() {
        return doitacname;
    }

    public void setDoitacname(String doitacname) {
        this.doitacname = doitacname;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getNgaybiennhan() {
        return ngaybiennhan;
    }

    public void setNgaybiennhan(Date ngaybiennhan) {
        this.ngaybiennhan = ngaybiennhan;
    }

    public String getDachitra() {
        return dachitra;
    }

    public void setDachitra(String dachitra) {
        this.dachitra = dachitra;
    }

    public Number getShs() {
        return shs;
    }

    public void setShs(Number shs) {
        this.shs = shs;
    }
}
