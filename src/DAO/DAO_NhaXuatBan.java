package DAO;

import Model.Model_NhaXuatBan;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DAO_NhaXuatBan {

    ArrayList<Model_NhaXuatBan> danhsachNhaXuatBan = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Model_NhaXuatBan> layDanhSachNhaXuatBan() {
        try {
            Connection conn = DAO_KetNoi.getConnection();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("select * from NHAXUATBAN");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Model_NhaXuatBan tg = new Model_NhaXuatBan();
                    tg.setMaNhaXuatBan(rs.getInt(1));
                    tg.setTenNhaXuatBan(rs.getString(2));
                    tg.setDiaChi(rs.getString(3));
                    tg.setSoDienThoai(rs.getString(4));
                    tg.setEmail(rs.getString(5));
                    danhsachNhaXuatBan.add(tg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsachNhaXuatBan;
    }
    public boolean insertNhaXuatBan(Model_NhaXuatBan nxb) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        String sql = "INSERT INTO NHAXUATBAN (TENNHAXUATBAN, DIACHI, SODIENTHOAI, EMAIL) VALUES (?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, nxb.getTenNhaXuatBan());
                        ps.setString(2, nxb.getDiaChi());
                        ps.setString(3, nxb.getSoDienThoai());
                        ps.setString(4, nxb.getEmail());

                        int rowsInserted = ps.executeUpdate();
                        if (rowsInserted > 0) {
                            result = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
}
    public static boolean deleteNhaXuatBan(int maNhaXuatBan) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM NHAXUATBAN WHERE MaNhaXuatBan = ?");
                        ps.setInt(1, maNhaXuatBan);
                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            result = true;
                        }
                        ps.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return result;
}
public static boolean updateNhaXuatBan(int maNhaXuatBan, String tenNhaXuatBan, String diaChi, String soDienThoai, String email) {
    boolean updated = false;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = DAO_KetNoi.getConnection();
        conn.setAutoCommit(false); // Bắt đầu giao dịch

        String query = "UPDATE NHAXUATBAN SET TenNhaXuatBan=?, DIACHI=?, SODIENTHOAI=?, EMAIL=? WHERE MaNhaXuatBan=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, tenNhaXuatBan);
        ps.setString(2, diaChi);
        ps.setString(3, soDienThoai);
        ps.setString(4, email);
        ps.setInt(5, maNhaXuatBan); // Đặt tham số MaNhaXuatBan

        int rowUpdated = ps.executeUpdate();
        if (rowUpdated > 0) {
            updated = true;
            conn.commit(); // Xác nhận giao dịch thành công
        } else {
            conn.rollback(); // Quay lại trạng thái trước khi bắt đầu giao dịch nếu không thành công
        }
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            if (conn != null) {
                conn.rollback(); // Quay lại trạng thái trước khi bắt đầu giao dịch nếu có lỗi
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true); // Trả lại tự động commit cho kết nối
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return updated;
}



}
