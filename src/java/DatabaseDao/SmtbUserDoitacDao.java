package DatabaseDao;

import Bean.ConnectionProvider;
import ModelLocal.SmtbUserDoitac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SmtbUserDoitacDao {

    public SmtbUserDoitac laythongtinUser(String userid) {
        SmtbUserDoitac smtbUserDoitac = new SmtbUserDoitac();

        Connection con = ConnectionProvider.getCon();

        String sql = " SELECT USER_ID,USER_NAME,ID_DOITAC,DOITAC,KHACHHANG,DAILY,ID_DAILY,ADMIN_DT,INTERNAL_USER FROM KH.SMTB_USER_DOITAC WHERE USER_ID = ? AND ID_VALIDITY= 'O' ";
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, userid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                smtbUserDoitac.setUserid(rs.getString("USER_ID"));
                smtbUserDoitac.setUsername(rs.getString("USER_NAME"));
                smtbUserDoitac.setIddoitac(rs.getString("ID_DOITAC"));
                smtbUserDoitac.setDoitac(rs.getString("DOITAC"));
                smtbUserDoitac.setKhachhang(rs.getString("KHACHHANG"));
                smtbUserDoitac.setDaily(rs.getString("DAILY"));
                smtbUserDoitac.setIddaily(rs.getString("ID_DAILY"));
                smtbUserDoitac.setAdmindt(rs.getString("ADMIN_DT"));
                smtbUserDoitac.setInternaluser(rs.getString("INTERNAL_USER"));
            }
            
            rs.close();
            ps.close();
        } catch (Exception e) {
            smtbUserDoitac = new SmtbUserDoitac();
        }

        return smtbUserDoitac;
    }
}
