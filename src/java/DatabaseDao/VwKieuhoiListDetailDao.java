package DatabaseDao;

import Bean.ConnectionProvider;
import ModelLocal.VwKieuhoiListDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VwKieuhoiListDetailDao {

    private String sql = " SELECT MA_QLDS,SOBN,SOPHIEU,STT,ID_CHINHANH,CHINHANH_NAME,MA_NGUOINHAN,DACHITRA,HINH_BN,"
            + "NGAY_NHAPLIEU,NGAY_CHITRA,NGAY_HOIBAO,ID_DIADIEMCHITRA,NGUOIGOI,HOTEN,DIACHI,GHICHU,GHICHU_GOI,"
            + "SO_CT_TUYTHAN,DIENTHOAI,SOTIENGOI,ID_LOAITIENGOI, DECODE(LENGTH(HINH_BN),NULL,'N','Y') AS CHECKIMAGE,AGENT FROM KH.VW_KIEUHOI_LIST_DETAIL ";
    private List<VwKieuhoiListDetail> vwKieuhoiListDetails = new ArrayList<>();
    private static int i;

    public List<VwKieuhoiListDetail> laydanhsach(String trangthai, String daily, String madoitac, Date ngaybiennhan) throws ParseException {

        Connection con = ConnectionProvider.getCon();
        String tt1 = trangthai.substring(0, 1);
        String tt2 = trangthai.substring(1, 2);

        String chuoingay = null;

        switch (trangthai) {
            case "YT":
                SimpleDateFormat sm = new SimpleDateFormat("dd-MMM-yyyy");
                chuoingay = sm.format(ngaybiennhan);
                sql += " WHERE AGENT = '" + daily + "' AND DACHITRA IN ('" + tt1 + "','" + tt2 + "') AND ID_DOITAC = '" + madoitac + "' AND NGAY_BIENNHAN = '" + chuoingay + "'";
                break;
            default:
                sql += " WHERE AGENT = '" + daily + "' AND DACHITRA = '" + tt1 + "' AND ID_DOITAC = '" + madoitac + "'";
                break;
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setQueryTimeout(1800);
            ResultSet rs = ps.executeQuery();
            /*
             SELECT MA_QLDS,SOBN,SOPHIEU,STT,ID_CHINHANH,CHINHANH_NAME,MA_NGUOINHAN,DACHITRA,"
             + "NGAY_NHAPLIEU,NGAY_CHITRA,NGAY_HOIBAO,ID_DIADIEMCHITRA,NGUOIGOI,HOTEN,DIACHI,"
             + "SO_CT_TUYTHAN,DIENTHOAI,SOTIENGOI,ID_LOAITIENGOI
             */
            while (rs.next()) {
                VwKieuhoiListDetail d = new VwKieuhoiListDetail();
                d.setMaqlds(rs.getString("MA_QLDS"));
                d.setSobn(rs.getInt("SOBN"));
                d.setSophieu(rs.getString("SOPHIEU"));
                d.setStt(rs.getInt("STT"));
                d.setAgent(rs.getString("AGENT"));
                d.setIdchinhanh(rs.getString("ID_CHINHANH"));
                d.setChinhanhname(rs.getString("CHINHANH_NAME"));
                d.setNgaynhaplieu(rs.getDate("NGAY_NHAPLIEU"));
                d.setHoten(rs.getString("HOTEN"));
                d.setSocttuythan(rs.getString("SO_CT_TUYTHAN"));
                d.setManguoinhan(rs.getString("MA_NGUOINHAN"));
                d.setIdloaitiengoi(rs.getString("ID_LOAITIENGOI"));
                d.setNguoigoi(rs.getString("NGUOIGOI"));
                d.setSotiengoi(rs.getBigDecimal("SOTIENGOI"));
                d.setDiachi(rs.getString("DIACHI"));
                d.setDienthoai(rs.getString("DIENTHOAI"));
                d.setIddiadiemchitra(rs.getString("ID_DIADIEMCHITRA"));
                d.setDachitra(rs.getString("DACHITRA"));
                d.setNgayhoibao(rs.getDate("NGAY_HOIBAO"));
                d.setNgaychitra(rs.getDate("NGAY_CHITRA"));
                d.setCheckimage(rs.getString("CHECKIMAGE"));
                d.setGhichu(rs.getString("GHICHU"));
                d.setGhichugoi(rs.getString("GHICHU_GOI"));
                d.setHinhbn(rs.getBlob("HINH_BN"));

                if (d.getCheckimage().equalsIgnoreCase("Y")) {
                    d.setDuongdanimage("haveimage.png");
                    d.setDisimage(false);
                } else {
                    d.setDuongdanimage("noimage.png");
                    d.setDisimage(true);
                }

                vwKieuhoiListDetails.add(d);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            vwKieuhoiListDetails = new ArrayList<>();
        }

        return vwKieuhoiListDetails;
    }

    public VwKieuhoiListDetail findmanguoinhan(String manguoinhan, String iddoitac) {

        Connection con = ConnectionProvider.getCon();

        sql += " WHERE MA_NGUOINHAN =  '" + manguoinhan + "' AND ID_DOITAC = '" + iddoitac + "'";
        VwKieuhoiListDetail d = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setQueryTimeout(1800);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                d = new VwKieuhoiListDetail();
                d.setMaqlds(rs.getString("MA_QLDS"));
                d.setSobn(rs.getInt("SOBN"));
                d.setSophieu(rs.getString("SOPHIEU"));
                d.setStt(rs.getInt("STT"));
                d.setIdchinhanh(rs.getString("ID_CHINHANH"));
                d.setChinhanhname(rs.getString("CHINHANH_NAME"));
                d.setNgaynhaplieu(rs.getDate("NGAY_NHAPLIEU"));
                d.setHoten(rs.getString("HOTEN"));
                d.setSocttuythan(rs.getString("SO_CT_TUYTHAN"));
                d.setManguoinhan(rs.getString("MA_NGUOINHAN"));
                d.setIdloaitiengoi(rs.getString("ID_LOAITIENGOI"));
                d.setNguoigoi(rs.getString("NGUOIGOI"));
                d.setSotiengoi(rs.getBigDecimal("SOTIENGOI"));
                d.setDiachi(rs.getString("DIACHI"));
                d.setDienthoai(rs.getString("DIENTHOAI"));
                d.setIddiadiemchitra(rs.getString("ID_DIADIEMCHITRA"));
                d.setDachitra(rs.getString("DACHITRA"));
                d.setNgayhoibao(rs.getDate("NGAY_HOIBAO"));
                d.setNgaychitra(rs.getDate("NGAY_CHITRA"));
                d.setCheckimage(rs.getString("CHECKIMAGE"));
                d.setHinhbn(rs.getBlob("HINH_BN"));
                d.setGhichu(rs.getString("GHICHU"));
                d.setGhichugoi(rs.getString("GHICHU_GOI"));

                if (rs.getString("DACHITRA").equalsIgnoreCase("Y")
                        || rs.getString("DACHITRA").equalsIgnoreCase("T")) {
                    d.setDachitradetail("ĐÃ CHI TRẢ");
                } else {
                    d.setDachitradetail("CHƯA CHI TRẢ");
                }

                if (rs.getString("ID_DIADIEMCHITRA").equalsIgnoreCase("TN")) {
                    d.setDiadiemchitradetail("TẠI NHÀ");
                } else {
                    d.setDiadiemchitradetail("TẠI QUẦY");
                }

                if (d.getCheckimage().equalsIgnoreCase("Y")) {
                    d.setDuongdanimage("haveimage.png");
                    d.setDisimage(false);
                } else {
                    d.setDuongdanimage("noimage.png");
                    d.setDisimage(true);
                }
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            d = null;
        }

        return d;
    }
}
