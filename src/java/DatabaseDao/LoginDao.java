package DatabaseDao;

import Bean.ConnectionProvider;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginDao {

    public static boolean validate(String user, String password) {
        String ketqua;

        try {
            // Kiem tra trang thai reload hay moi khoi tao  .
            ConnectionProvider.reconnectdbastatic();
            Connection con = null;
            con = ConnectionProvider.getCon();

            String fnCall = "{ ? = call KH.SMPKS.fn_signon_doitac_api(?,?)}";

            CallableStatement stm = con.prepareCall(fnCall);

            stm.registerOutParameter(1, Types.VARCHAR);
            stm.registerOutParameter(2, Types.VARCHAR);
            stm.registerOutParameter(3, Types.VARCHAR);
            stm.setString(2, user.toUpperCase());
            stm.setString(3, password.toUpperCase());
            stm.execute();

            ketqua = (String) stm.getString(1);
            stm.close();

            if (ketqua.equalsIgnoreCase("T")) {
                return true;

            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getMadoitac(String username) {
        Connection con = ConnectionProvider.getCon();

        String fnCall = "{?=call KH.PKS_API_SERVICE.FUNC_TIMDOITAC_FTAIKHOAN(?)}";
        String kq = null;

        try {
            CallableStatement cs = con.prepareCall(fnCall);
            cs.setQueryTimeout(1800);

            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            cs.setString(2, username);

            cs.executeUpdate();

            kq = cs.getString(1);

            cs.close();

        } catch (Exception e) {
            kq = e.getMessage();
        }

        return kq;
    }
    
}
