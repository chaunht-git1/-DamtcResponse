package DatabaseDao;

import Bean.ConnectionProvider;
import Model.SmtbDanhmucListModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SmtbDanhmucListDao {

    private List<SmtbDanhmucListModel> smtbDanhmucListModels = new ArrayList<>();

    public List<SmtbDanhmucListModel> laydanhsachdaily(String doitac) {
        Connection con = ConnectionProvider.getCon();
        String sql = " SELECT DESCRIPTION,ID FROM KH.SMTB_DANHMUC_LIST WHERE MA_DANHMUC = '068' AND ID_TIEUDE= "+ doitac+ " AND  ID_VALIDITY = 'O' ";

        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                SmtbDanhmucListModel d = new SmtbDanhmucListModel();

                d.setDescription(rs.getString("DESCRIPTION"));
                d.setId(rs.getString("ID"));
 
                smtbDanhmucListModels.add(d);

            }

            rs.close();
            s.close();

        } catch (Exception e) {
            smtbDanhmucListModels = new ArrayList<>();
        }

        return smtbDanhmucListModels;
    }

    public String timtendaily(String madaily) {
        Connection con = ConnectionProvider.getCon();

        try {
            String fnCall = "{?= call KH.PKS_TIM_THONGTIN.TEN_DANHMUC(?)}";
            CallableStatement stm = con.prepareCall(fnCall);
            stm.setQueryTimeout(1800);
            stm.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            stm.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);

            stm.setString(2, madaily);
            stm.executeUpdate();
            String tendaily = stm.getString(1);
            stm.close();

            return tendaily;
        } catch (Exception e) {
            return "";
        }

    }
}
