package Bean;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

@ManagedBean
public class SystemBean {

    public static void proMessage(String mess) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mess, mess));

    }

    public static void proMessError(String mess) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mess, mess));

    }

    public ResultSet layGiaTri1Para(String tablename, String colname, String outcolname, String prm1, Date prm2, Integer prm3, int sttpara, int outvalue) throws SQLException {

        Connection con = ConnectionProvider.getCon();

        try {
            String sql;
            if (outvalue == 0) {
                sql = " SELECT * FROM" + tablename + " WHERE " + colname + "= ?   ";
            } else {
                sql = " SELECT " + outcolname + " FROM" + tablename + " WHERE " + colname + "= ?   ";
            }
            // Tạo một câu SQL có 2 tham số (?)
            // String sql = strsql;

            // Tạo một đối tượng PreparedStatement.
            PreparedStatement pstm = con.prepareStatement(sql);

            // Sét đặt giá trị tham số thứ nhất (Dấu ? thứ nhất)  
            // Kiem tra kieu du lieu cua bien truyen vao  . 
            switch (sttpara) {

                case 1:
                    pstm.setString(1, prm1);
                    break;
                case 2:
                    pstm.setDate(1, (java.sql.Date) prm2);
                    break;
                case 3:
                    pstm.setInt(1, prm3);
                    break;
            }

            ResultSet rs = pstm.executeQuery();

            return rs;

        } catch (Exception e) {

            return null;

        }

    }

    // Chuyen doi gia tri sang Buffimage  
    public BufferedImage layImage(InputStream byteImage) throws IOException {

        BufferedImage bf = null;

        // InputStream in =new ByteInputStream();
        bf = ImageIO.read(byteImage);

        return bf;
    }

}
