package DAO;

import Model.Model_Sach;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DAO_SACH {

    ArrayList<Model_Sach> danhsachSach = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Model_Sach> layDanhSachSach() {
        try {
            Connection conn = DAO_KetNoi.getConnection();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("select * from SACH");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Model_Sach s = new Model_Sach();
                    s.setMaSach(rs.getInt(1));
                    s.setTieuDe(rs.getString(2));
                    s.setMaTacGia(rs.getInt(3));
                    s.setMaNhaXuatBan(rs.getInt(4));
                    s.setTheLoai(rs.getString(5));
                    s.setMaNhaXuatBan(rs.getInt(6));
                    s.setSoTrang(rs.getInt(7));      
                    s.setNgonNgu(rs.getString(8));
                    s.setMaNhaXuatBan(rs.getInt(9));
                    danhsachSach.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsachSach;
    }
    public boolean insertSach(Model_Sach s) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        String sql = "INSERT INTO SACH (MASACH, TIEUDE, MATACGIA, MANHAXUATBAN, THELOAI, NAMXUATBAN, SOTRANG, NGONNGU, SOLUONG) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, s.getTieuDe());
                        ps.setInt(2, s.getMaTacGia());
                        ps.setInt(3, s.getMaNhaXuatBan());
                        ps.setString(4, s.getTheLoai());
                        ps.setInt(5, s.getSoTrang());
                        ps.setString(7, s.getNgonNgu());
                        ps.setInt(8, s.getSoLuong());


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
    public static boolean deleteSach(int maSach) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM SACH WHERE MaSach = ?");
                        ps.setInt(1, maSach);
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
public static boolean updateSach(int maSach, String tieuDe, int maTacGia, int maNhaXuatBan, String theLoai, int namXuatBan, int soTrang, String ngonNgu, int soLuong) {
    boolean updated = false;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = DAO_KetNoi.getConnection();
        conn.setAutoCommit(false); // Bắt đầu giao dịch

        String query = "UPDATE SACH SET TIEUDE=?, MATACGIA=?, MANHAXUATBAN=?, THELOAI=?, NAMXUATBAN=?, SOTRANG=?, NGONNGU=?, SOLUONG=? WHERE MaSach=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, tieuDe);
        ps.setInt(2, maTacGia);
        ps.setInt(3, maNhaXuatBan);
        ps.setString(4, theLoai);
        ps.setInt(5, namXuatBan);
        ps.setInt(6, soTrang);
        ps.setString(7, ngonNgu);
        ps.setInt(8, soLuong);
        ps.setInt(9, maSach); // Đặt tham số MaSach

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
