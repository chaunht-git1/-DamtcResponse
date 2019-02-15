package DatabaseDao;

import Bean.ConnectionProvider;
import ModelLocal.VwLichsuGdListWeb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VwLichsuGdListWebDao {

    private String sql = " SELECT * FROM VW_LICHSU_GD_LIST_WEB ";
    private List<VwLichsuGdListWeb> vwLichsuGdListWebs = new ArrayList<>();

    public List<VwLichsuGdListWeb> timtheomabiennhan(String mabiennhan) {
        Connection con = ConnectionProvider.getCon();

        sql += " WHERE MABIENNHAN = '" + mabiennhan + "'";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setQueryTimeout(1800);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                VwLichsuGdListWeb d = new VwLichsuGdListWeb();
                d.setMabiennhan(rs.getString("MABIENNHAN"));
                d.setLoai(rs.getString("LOAI"));
                d.setLydo(rs.getString("LYDO"));
                d.setTennv(rs.getString("TENNV"));
                d.setGiodieuchinh(rs.getDate("GIO_DIEUCHINH"));
                d.setIdchinhanh(rs.getString("ID_CHINHANH"));
                d.setSophieu(rs.getString("SOPHIEU"));
                d.setStt(rs.getString("STT"));
                d.setSobn(rs.getString("SOBN"));
                d.setIddoitac(rs.getString("ID_DOITAC"));
                d.setChargio(rs.getString("CHAR_GIO")); 
                

                vwLichsuGdListWebs.add(d);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            vwLichsuGdListWebs = new ArrayList<>();
        }

        return vwLichsuGdListWebs;
    }
}
