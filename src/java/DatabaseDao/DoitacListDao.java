package DatabaseDao;

import Bean.ConnectionProvider;
import ModelLocal.DoitacList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoitacListDao {

    private String sql = " SELECT * FROM KH.DOITAC_LIST ";
    private List<DoitacList> doitacLists = new ArrayList<>();

    public List<DoitacList> getdoitacimportweb() throws SQLException {
        Connection con = ConnectionProvider.getCon();
        sql += " WHERE ID_VALIDITY='O' AND IMPORT_WEB='Y'";

        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DoitacList d = new DoitacList();
            d.setIdcode(rs.getString("ID_CODE"));
            d.setDescription(rs.getString("DESCRIPTION"));
            d.setDiachi(rs.getString("DIACHI"));
            d.setIdcity(rs.getString("ID_CITY"));
            d.setTel(rs.getString("TEL"));
            d.setFax(rs.getString("FAX"));
            d.setIdvalidity(rs.getString("ID_VALIDITY"));
            d.setDatemodified(rs.getDate("DATE_MODIFIED"));
            d.setIdnational(rs.getString("ID_NATIONAL"));
            d.setSotaikhoan(rs.getString("SOTAIKHOAN"));
            d.setIdkyhopdong(rs.getString("ID_KYHOPDONG"));
            d.setNguoidaidien(rs.getString("NGUOIDAIDIEN"));
            d.setChucdanh(rs.getString("CHUCDANH"));
            d.setIddanghoahong(rs.getString("ID_DANGHOAHONG"));
            d.setIdcongnocodinh(rs.getString("ID_CONGNO_CODINH"));
            d.setIdloaitienkhautru(rs.getString("ID_LOAITIENKHAUTRU"));
            d.setTinhnguyente(rs.getString("TINHNGUYENTE"));
            d.setTachhoahong(rs.getString("TACHHOAHONG"));
            d.setIdtygiabank(rs.getString("ID_TYGIA_BANK"));
            d.setThitruong(rs.getString("THITRUONG"));
            d.setIdq5(rs.getString("ID_Q5"));
            d.setTygiatheobank(rs.getString("TYGIATHEOBANK"));
            d.setThitruongnew(rs.getString("THITRUONG_NEW"));
            d.setTentiengviet(rs.getString("TENTIENGVIET"));
            d.setTentienganh(rs.getString("TENTIENGANH"));
            d.setIdkiemtra(rs.getString("ID_KIEMTRA"));
            d.setMasothue(rs.getString("MASOTHUE"));
            d.setIdcongtacvien(rs.getString("ID_CONGTACVIEN"));
            d.setTaikhoan(rs.getString("TAIKHOAN"));
            d.setIddoitien(rs.getString("ID_DOITIEN"));
            d.setIdchuyenkhoan(rs.getString("ID_CHUYENKHOAN"));
            d.setIdduyettudong(rs.getString("ID_DUYETTUDONG"));
            d.setIdlanhb(rs.getString("ID_LAN_HB"));
            d.setIddaucau(rs.getString("ID_DAUCAU"));
            d.setIdbiennhan(rs.getString("ID_BIENNHAN"));
            d.setIdnhaplieu(rs.getString("ID_NHAPLIEU"));
            d.setIdcongno(rs.getString("ID_CONGNO"));
            d.setIdketoan(rs.getString("ID_KETOAN"));
            d.setIdtk(rs.getString("ID_TK"));
            d.setIdchuyendoict(rs.getString("ID_CHUYENDOI_CT"));
            d.setIdltgsdtd(rs.getString("ID_LTG_SDTD"));
            d.setChitrakhaccn(rs.getString("CHITRA_KHAC_CN"));
            d.setIddoitacgoctk(rs.getString("ID_DOITAC_GOC_TK"));
            d.setHieulucqlds(rs.getString("HIEULUC_QLDS"));
            d.setUserkycongno(rs.getString("USER_KY_CONGNO"));
            d.setNgaykyhd(rs.getDate("NGAY_KYHD"));
            d.setNgaypsgd(rs.getDate("NGAY_PSGD"));
            d.setTygiaapdung(rs.getString("TYGIA_APDUNG"));
            d.setIddvuse(rs.getString("ID_DV_USE"));
            d.setIddttt(rs.getString("ID_DT_TT"));
            d.setIdduyetqldsauto(rs.getString("ID_DUYET_QLDS_AUTO"));
            d.setNhaptygia(rs.getString("NHAP_TYGIA"));
            d.setIdloaihhdb(rs.getString("ID_LOAIHH_DB"));
            d.setIdtructuyen(rs.getString("ID_TRUCTUYEN"));
            d.setDuthuong(rs.getString("DUTHUONG"));
            d.setIdhoahong(rs.getString("ID_HOAHONG"));
            d.setTentat(rs.getString("TENTAT"));
            d.setGiaophieuweb(rs.getString("GIAOPHIEU_WEB"));
            d.setImportweb(rs.getString("IMPORT_WEB"));

            doitacLists.add(d);
        }
        ps.close();
        return doitacLists;
    }
}
