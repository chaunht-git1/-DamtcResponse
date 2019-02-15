package Controler;

import static Bean.Provider.DBS_NAME;
import static Bean.Provider.PASSWORD;
import static Bean.Provider.REPORT_PATH;
import static Bean.Provider.SERVERNAME_REPORT;
import static Bean.Provider.URL_REPORT;
import static Bean.Provider.USERNAME;
import DatabaseDao.DoitacListDao;
import DatabaseDao.SmtbDanhmucListDao;
import DatabaseDao.VwKieuhoiListDetailDao;
import DatabaseDao.VwKieuhoiListMasterDao;
import DatabaseDao.VwLichsuGdListWebDao;
import Global.SessionBean;
import Model.SmtbDanhmucListModel;
import ModelLocal.DoitacList;
import ModelLocal.VwKieuhoiListDetail;
import ModelLocal.VwKieuhoiListMaster;
import ModelLocal.VwLichsuGdListWeb;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named(value = "hoibaoManagedBean")
@SessionScoped
public class HoibaoManagedBean implements Serializable {

     

    private List<VwKieuhoiListDetail> vwKieuhoiListDetails;
    private List<VwKieuhoiListMaster> vwKieuhoiListMasters;
    private List<SmtbDanhmucListModel> smtbDanhmucListModels;
    private List<VwLichsuGdListWeb> vwLichsuGdListWebs;

    private VwKieuhoiListMaster vwKieuhoiListMaster;
    private VwKieuhoiListDetail vwKieuhoiListDetail;

    private Date tungay;
    private Date denngay;
    private String trangthai;
    private String iddaily;
    private String tendaily;
    private String manguoinhan;
    private String iddoitac;
    private Date ngaybiennhan;
    private Boolean dischondaily;
    private Boolean dischondoitac;

    //khai bao bien servlet
    private String prmagent;
    private String prmdoitac;
    private Date prmfromdate;
    private String prmidloaitien;
    private Date prmtodate;
    
    
    public void kiemtraphanquyenuser(){
    
        Calendar cal = Calendar.getInstance();
        tungay = cal.getTime();
        denngay = cal.getTime();
        ngaybiennhan = cal.getTime();

        
        iddaily = SessionBean.getUseriNFOR().getIddaily();
//        this.timtendaily();
        iddoitac = SessionBean.getUseriNFOR().getIddoitac();
        this.laydanhsachdaily();

        if (SessionBean.getUseriNFOR().getAdmindt().equalsIgnoreCase("Y")) {
            
            iddaily = "alldl";
            if(iddoitac.equalsIgnoreCase("42689"))
            {
                dischondaily = true;
            }
            else{
                dischondaily = false;
           
                
            }
        } else {
            dischondaily = true;
        }

        if (SessionBean.getUseriNFOR().getInternaluser().equalsIgnoreCase("Y")) {
            dischondoitac = false;
        } else {
            dischondoitac = true;
           // iddoitac = SessionBean.getUseriNFOR().getIddoitac();
        }
        
        
}

    public void hienthighichugiaodich() {

        String mabiennhan = null;
        mabiennhan = vwKieuhoiListDetail.getSophieu() + vwKieuhoiListDetail.getStt();

        VwLichsuGdListWebDao dao = new VwLichsuGdListWebDao();
        vwLichsuGdListWebs = dao.timtheomabiennhan(mabiennhan);
    }

    public String chuyentrangdownload() {
        return "downloadbn?faces-redirect=true";
    }

    public void hienthithongtindetail() throws ParseException {
        VwKieuhoiListDetailDao dao = new VwKieuhoiListDetailDao();
        vwKieuhoiListDetails = dao.laydanhsach(vwKieuhoiListMaster.getDachitratemp(), vwKieuhoiListMaster.getAgent(), iddoitac, vwKieuhoiListMaster.getNgaybiennhan());

    }

    public void hienthithongtinmaster() {
        this.timtendaily();
        VwKieuhoiListMasterDao dao = new VwKieuhoiListMasterDao();
        //iddoitac="20064";
        vwKieuhoiListMasters = dao.laydanhsach(trangthai, tendaily, iddoitac, ngaybiennhan, iddaily);
    }

    public void laydanhsachdaily() {
        smtbDanhmucListModels = new ArrayList<>();
        SmtbDanhmucListDao dao = new SmtbDanhmucListDao();
        smtbDanhmucListModels = dao.laydanhsachdaily(iddoitac);
    }

    public void timtendaily() {
//        String madaily = SessionBean.getIdDaily();
        SmtbDanhmucListDao dao = new SmtbDanhmucListDao();
        tendaily = dao.timtendaily(iddaily);
    }

    public void downloadbiennhan( VwKieuhoiListDetail detail) throws SQLException, IOException {

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.setResponseContentType("image/jpeg");

        String attachmentName = "attachment;filename=" + "HP" + detail.getManguoinhan()+ ".jpg";
        ec.setResponseHeader("Content-Disposition", attachmentName);
//        byte[] bytes = IOUtils.toByteArray(inputStream1);
        try (OutputStream out = ec.getResponseOutputStream()) {
            InputStream inputStream;
            inputStream = detail.getHinhbn().getBinaryStream();
            byte[] bytes = new byte[4096];
            byte[] outputByte = new byte[4096];
            while (inputStream.read(outputByte, 0, 4096) != -1) {

                out.write(outputByte, 0, 4096);
            }
//        out.write(outputByte, 0, 1873);

            out.flush();
            fc.responseComplete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String chuyentranghoibao() throws ParseException {

        this.hienthithongtindetail();

        return "hoibao" + "?faces-redirect=true";
    }

    public void timtheomanguoinhan() {
        VwKieuhoiListDetailDao dao = new VwKieuhoiListDetailDao();
        vwKieuhoiListDetail = dao.findmanguoinhan(manguoinhan, iddoitac);
        
        this.hienthighichugiaodich();
    }

    public List<DoitacList> laydsdoitac() throws SQLException {

        DoitacListDao dao = new DoitacListDao();
        List<DoitacList> dsdoitac = new ArrayList<>();
        dsdoitac = dao.getdoitacimportweb();

        return dsdoitac;
    }

    public String quanlyhoibao() {
        return "quanlyhoibao" + "?faces-redirect=true";
    }

    public String showhoibao(VwKieuhoiListMaster vwKieuhoiListMaster) throws IOException, ParseException {
     
        
        prmagent = vwKieuhoiListMaster.getAgent();
        prmdoitac = vwKieuhoiListMaster.getIddoitac();
        prmfromdate = vwKieuhoiListMaster.getNgaybiennhan();

//        String report = "RE_HB_AGENTWEB.rdf";
//
//        String pathreport = null;
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(vwKieuhoiListMaster.getNgaybiennhan().toString());
//        String chuoingay = new SimpleDateFormat("dd-MMM-yyyy").format(date);
//
//        pathreport = URL_REPORT + SERVERNAME_REPORT + "&destype=cache&desformat=PDF&report="
//                + REPORT_PATH + report + "&userid=" + USERNAME + "/" + "2014nobody"
//                + "@" + DBS_NAME + "&PRM_AGENT=" + prmagent + "&PRM_DOITAC=" + prmdoitac
//                + "&PRM_FROMDATE=" + chuoingay;
//        downloadbn(pathreport);
        return "showhoibao" + "?faces-redirect=true";
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Image img = Image.getInstance("");
        Document document = new Document(img);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        /*  for (String image : IMAGES) {
         img = Image.getInstance(image);
         document.setPageSize(img);
         document.newPage();
         img.setAbsolutePosition(0, 0);
         document.add(img);
         }
         */
        document.close();
    }

    public String downloadtemp() throws ParseException, IOException {
        String report = "RE_HB_AGENTWEB.rdf";

        String pathreport = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(prmfromdate.toString());
        String chuoingay = new SimpleDateFormat("dd-MMM-yyyy").format(date);

        pathreport = URL_REPORT + SERVERNAME_REPORT + "&destype=cache&desformat=PDF&report="
                + REPORT_PATH + report + "&userid=" + USERNAME + "/" + PASSWORD
                + "@" + DBS_NAME + "&PRM_AGENT=" + prmagent + "&PRM_DOITAC=" + prmdoitac
                + "&PRM_FROMDATE=" + chuoingay;
        downloadbn(pathreport);
        return "quanlyhoibao?faces-redirect=true";
    }

    public void downloadbn(String link) throws IOException {

        try {
            byte[] imageBytes = null;
            byte[] buffer = new byte[8192];
            int bytesRead;
            HttpServletRequest request = SessionBean.getRequest();
            HttpServletResponse response = SessionBean.getResponse();

            URL urlConn = new URL(link);
            InputStream inputStream = urlConn.openStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            OutputStream out = response.getOutputStream();
            imageBytes = output.toByteArray();
            response.setContentType("application/pdf");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(prmfromdate.toString());
            String chuoingay = new SimpleDateFormat("dd-MMM-yyyy").format(date);
            String filename = "SGCentral" + "_" + tendaily + "_" + chuoingay;
            response.setHeader("Content-disposition", "attachment;filename=" + filename + ".pdf");
            out.write(imageBytes);
            output.flush();
            output.close();

            // Download 
            out.flush();

            out.close();

        } catch (Exception e) {
        }

    }

    //get set
    public List<VwLichsuGdListWeb> getVwLichsuGdListWebs() {
        return vwLichsuGdListWebs;
    }

    public void setVwLichsuGdListWebs(List<VwLichsuGdListWeb> vwLichsuGdListWebs) {
        this.vwLichsuGdListWebs = vwLichsuGdListWebs;
    }

    public Boolean getDischondoitac() {
        return dischondoitac;
    }

    public void setDischondoitac(Boolean dischondoitac) {
        this.dischondoitac = dischondoitac;
    }

    public Boolean getDischondaily() {
        return dischondaily;
    }

    public void setDischondaily(Boolean dischondaily) {
        this.dischondaily = dischondaily;
    }

    public Date getNgaybiennhan() {
        return ngaybiennhan;
    }

    public void setNgaybiennhan(Date ngaybiennhan) {
        this.ngaybiennhan = ngaybiennhan;
    }

    public String getPrmagent() {
        return prmagent;
    }

    public void setPrmagent(String prmagent) {
        this.prmagent = prmagent;
    }

    public String getPrmdoitac() {
        return prmdoitac;
    }

    public void setPrmdoitac(String prmdoitac) {
        this.prmdoitac = prmdoitac;
    }

    public Date getPrmfromdate() {
        return prmfromdate;
    }

    public void setPrmfromdate(Date prmfromdate) {
        this.prmfromdate = prmfromdate;
    }

    public Date getPrmtodate() {
        return prmtodate;
    }

    public void setPrmtodate(Date prmtodate) {
        this.prmtodate = prmtodate;
    }

    public String getPrmidloaitien() {
        return prmidloaitien;
    }

    public void setPrmidloaitien(String prmidloaitien) {
        this.prmidloaitien = prmidloaitien;
    }

    public String getIddoitac() {
        return iddoitac;
    }

    public void setIddoitac(String iddoitac) {
        this.iddoitac = iddoitac;
    }

    public VwKieuhoiListDetail getVwKieuhoiListDetail() {
        return vwKieuhoiListDetail;
    }

    public void setVwKieuhoiListDetail(VwKieuhoiListDetail vwKieuhoiListDetail) {
        this.vwKieuhoiListDetail = vwKieuhoiListDetail;
    }

    public String getManguoinhan() {
        return manguoinhan;
    }

    public void setManguoinhan(String manguoinhan) {
        this.manguoinhan = manguoinhan;
    }

    public List<SmtbDanhmucListModel> getSmtbDanhmucListModels() {
        return smtbDanhmucListModels;
    }

    public void setSmtbDanhmucListModels(List<SmtbDanhmucListModel> smtbDanhmucListModels) {
        this.smtbDanhmucListModels = smtbDanhmucListModels;
    }

    public VwKieuhoiListMaster getVwKieuhoiListMaster() {
        return vwKieuhoiListMaster;
    }

    public void setVwKieuhoiListMaster(VwKieuhoiListMaster vwKieuhoiListMaster) {
        this.vwKieuhoiListMaster = vwKieuhoiListMaster;
    }

    public List<VwKieuhoiListMaster> getVwKieuhoiListMasters() {
        return vwKieuhoiListMasters;
    }

    public void setVwKieuhoiListMasters(List<VwKieuhoiListMaster> vwKieuhoiListMasters) {
        this.vwKieuhoiListMasters = vwKieuhoiListMasters;
    }

    public List<VwKieuhoiListDetail> getVwKieuhoiListDetails() {
        return vwKieuhoiListDetails;
    }

    public void setVwKieuhoiListDetails(List<VwKieuhoiListDetail> vwKieuhoiListDetails) {
        this.vwKieuhoiListDetails = vwKieuhoiListDetails;
    }

    public Date getTungay() {
        return tungay;
    }

    public void setTungay(Date tungay) {
        this.tungay = tungay;
    }

    public Date getDenngay() {
        return denngay;
    }

    public void setDenngay(Date denngay) {
        this.denngay = denngay;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getIddaily() {
        return iddaily;
    }

    public void setIddaily(String iddaily) {
        this.iddaily = iddaily;
    }

    public String getTendaily() {
        return tendaily;
    }

    public void setTendaily(String tendaily) {
        this.tendaily = tendaily;
    }

}
