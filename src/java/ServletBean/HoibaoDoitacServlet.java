package ServletBean;

import static Bean.Provider.DBS_NAME;
import static Bean.Provider.PASSWORD;
import static Bean.Provider.REPORT_PATH;
import static Bean.Provider.SERVERNAME_REPORT;
import static Bean.Provider.URL_REPORT;
import static Bean.Provider.USERNAME;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HoibaoDoitacServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HoibaoDoitacServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HoibaoDoitacServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            byte[] imageBytes = null;
            byte[] buffer = new byte[8192];
            int bytesRead;

            String prmagent = request.getParameter("prmagent");
            String prmdoitac = request.getParameter("prmdoitac");
            String prmfromdate = request.getParameter("prmfromdate");

            //Convert biến 'ngay' về kiểu chuỗi, định dạng chuỗi là dd-MMM-yy, vd: 27-JUN-16
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date date = sdf.parse(prmfromdate);
            prmfromdate = new SimpleDateFormat("dd-MMM-yy").format(date);

            String report = "RE_HB_AGENTWEB.rdf";

            String pathreport = null;

            pathreport = URL_REPORT + SERVERNAME_REPORT + "&destype=cache&desformat=PDF&report="
                    + REPORT_PATH + report + "&userid=" + USERNAME + "/" + PASSWORD
                    + "@" + DBS_NAME + "&PRM_AGENT=" + prmagent + "&PRM_DOITAC=" + prmdoitac
                    + "&PRM_FROMDATE=" + prmfromdate;

            URL urlConn = new URL(pathreport);
            InputStream inputStream = urlConn.openStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            OutputStream out = response.getOutputStream();
            imageBytes = output.toByteArray();
            response.setContentType("application/pdf");
            String filename = "SGCentral" + "_" + prmagent + "_" + prmfromdate;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
