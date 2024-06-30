package DAO;

import Model.Model_Sach;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DAO_Sach {

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
                    s.setTenSach(rs.getString(2));
                    s.setMaTacGia(rs.getInt(3));
                    s.setMaNhaXuatBan(rs.getInt(4));
                    s.setTheLoai(rs.getString(5));
                    s.setNamXuatBan(rs.getInt(6));
                    s.setSoTrang(rs.getInt(7));      
                    s.setNgonNgu(rs.getString(8));
                    s.setSoLuong(rs.getInt(9));
                    s.setGiaThue(rs.getFloat(10));
                    s.setGiaBan(rs.getFloat(11));
                    danhsachSach.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsachSach;
    }
    
    public ArrayList<String> layDanhSachMaTenTacGia() {
    ArrayList<String> danhSachMaTenTacGia = new ArrayList<>();
    try {
        Connection conn = DAO_KetNoi.getConnection();
        if (conn != null) {
            PreparedStatement ps = conn.prepareStatement("SELECT MaTacGia, TenTacGia FROM TACGIA");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maTacGia = rs.getInt(1);
                String tenTacGia = rs.getString(2);
                String maTenTacGia = maTacGia + " - " + tenTacGia;
                danhSachMaTenTacGia.add(maTenTacGia);
            }
            rs.close();
            ps.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return danhSachMaTenTacGia;
}

    
    public boolean insertSach(Model_Sach s) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        String sql = "INSERT INTO SACH ( TENSACH, MATACGIA, MANHAXUATBAN, THELOAI, NAMXUATBAN, SOTRANG, NGONNGU, SOLUONG, GIATHUE, GIABAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, s.getTenSach());
                        ps.setInt(2, s.getMaTacGia());
                        ps.setInt(3, s.getMaNhaXuatBan());
                        ps.setString(4, s.getTheLoai());
                        ps.setInt(5, s.getNamXuatBan());
                        ps.setInt(6, s.getSoTrang());
                        ps.setString(7, s.getNgonNgu());
                        ps.setInt(8, s.getSoLuong());
                        ps.setFloat(9, s.getGiaThue());
                        ps.setFloat(10, s.getGiaBan());


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
public static boolean updateSach(int maSach, String tenSach, int maTacGia, int maNhaXuatBan, String theLoai, int namXuatBan, int soTrang, String ngonNgu, int soLuong, float giaThue, float giaBan) {
    boolean updated = false;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = DAO_KetNoi.getConnection();
        conn.setAutoCommit(false); // Bắt đầu giao dịch

        String query = "UPDATE SACH SET TenSach=?, MATACGIA=?, MANHAXUATBAN=?, THELOAI=?, NAMXUATBAN=?, SOTRANG=?, NGONNGU=?, SOLUONG=?, GIATHUE=?, GIABAN=? WHERE MaSach=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, tenSach);
        ps.setInt(2, maTacGia);
        ps.setInt(3, maNhaXuatBan);
        ps.setString(4, theLoai);
        ps.setInt(5, namXuatBan);
        ps.setInt(6, soTrang);
        ps.setString(7, ngonNgu);
        ps.setInt(8, soLuong);
        ps.setFloat(9, giaThue);
        ps.setFloat(10, giaBan);
        ps.setInt(11, maSach); // Đặt tham số MaSach

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
