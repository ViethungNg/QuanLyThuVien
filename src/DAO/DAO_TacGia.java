package DAO;

import Model.Model_TacGia;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DAO_TacGia {
    ArrayList<Model_TacGia> danhsachTacGia = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Model_TacGia> layDanhSachTacGia() {
        try {
            Connection conn = DAO_KetNoi.getConnection();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("select * from TACGIA");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Model_TacGia tg = new Model_TacGia();
                    tg.setMaTacGia(rs.getInt(1));
                    tg.setTenTacGia(rs.getString(2));
                    tg.setNamSinh(rs.getInt(3));
                    tg.setNamMat(rs.getInt(4));
                    tg.setQuocTich(rs.getString(5));
                    danhsachTacGia.add(tg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsachTacGia;
    }
    public boolean insertTacGia(Model_TacGia tg) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        String sql = "INSERT INTO TACGIA (TENTACGIA, NAMSINH, NAMMAT, QUOCTICH) VALUES (?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, tg.getTenTacGia());
                        ps.setInt(2, tg.getNamSinh());
                        ps.setInt(3, tg.getNamMat());
                        ps.setString(4, tg.getQuocTich());

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
    public static boolean deleteTacGia(int maTacGia) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM TACGIA WHERE MaTacGia = ?");
                        ps.setInt(1, maTacGia);
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
public static boolean updateTacGia(int maTacGia, String tenTacGia, int namSinh, int namMat, String quocTich) {
    boolean updated = false;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = DAO_KetNoi.getConnection();
        conn.setAutoCommit(false); // Bắt đầu giao dịch

        String query = "UPDATE TACGIA SET TenTacGia=?, NamSinh=?, NamMat=?, QuocTich=? WHERE MaTacGia=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, tenTacGia);
        ps.setInt(2, namSinh);
        ps.setInt(3, namMat);
        ps.setString(4, quocTich);
        ps.setInt(5, maTacGia); // Đặt tham số MaTacGia

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
