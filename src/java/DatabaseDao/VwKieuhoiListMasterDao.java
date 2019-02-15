package DatabaseDao;

import Bean.ConnectionProvider;
import ModelLocal.VwKieuhoiListMaster;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VwKieuhoiListMasterDao {

    private String sql = " SELECT * FROM KH.VW_KIEUHOI_LIST_MASTER ";
    private List<VwKieuhoiListMaster> vwKieuhoiListMasters = new ArrayList<>();

    public List<VwKieuhoiListMaster> laydanhsach(String trangthai, String daily, String madoitac, Date ngaybiennhan, String iddaily) {
        Connection con = ConnectionProvider.getCon();

        String tt1 = trangthai.substring(0, 1);
        String tt2 = trangthai.substring(1, 2);
        
        switch (trangthai) {
            case "YT":
                if (iddaily.equalsIgnoreCase("ALLDL")) {
                    sql += " WHERE DACHITRA IN ('" + tt1 + "','" + tt2 + "') AND ID_DOITAC = '" + madoitac + "' AND NGAY_BIENNHAN = ?";
                } else {
                    sql += " WHERE AGENT = '" + daily + "' AND DACHITRA IN ('" + tt1 + "','" + tt2 + "') AND ID_DOITAC = '" + madoitac + "' AND NGAY_BIENNHAN = ?";
                }
                break;
            case "NN":
                if (iddaily.equalsIgnoreCase("ALLDL")) {
                    sql += " WHERE DACHITRA = '" + tt1 + "' AND ID_DOITAC = '" + madoitac + "'  ";

                } else {
                    sql += " WHERE AGENT = '" + daily + "' AND DACHITRA = '" + tt1 + "' AND ID_DOITAC = '" + madoitac + "'";
                }
            default:break;
        }

        try {
            CallableStatement ps = con.prepareCall(sql);
            ps.setQueryTimeout(1800);

            switch (trangthai) {
                case "YT":
                    ps.registerOutParameter(1, oracle.jdbc.OracleTypes.DATE);

                    ps.setDate(1, new java.sql.Date(ngaybiennhan.getTime()));
                    break;
                default:
                    break;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VwKieuhoiListMaster d = new VwKieuhoiListMaster();
                d.setIddoitac(rs.getString("ID_DOITAC"));
                d.setDoitacname(rs.getString("DOITAC_NAME"));
                d.setAgent(rs.getString("AGENT"));
                d.setNgaybiennhan(rs.getDate("NGAY_BIENNHAN"));
                d.setDachitra(rs.getString("DACHITRA"));
                d.setShs(rs.getInt("SHS"));
//                d.setTungay(tungay);
//                d.setDengay(denngay);
                d.setDachitratemp(trangthai);
                
                if(tt1.equalsIgnoreCase("N")){
                    d.setDisdownload(true);
                }else{
                    d.setDisdownload(false);
                }

                vwKieuhoiListMasters.add(d);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            vwKieuhoiListMasters = new ArrayList<>();
        }

        return vwKieuhoiListMasters;
    }
}
