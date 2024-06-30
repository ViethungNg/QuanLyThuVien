/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Model_CongViec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//
/**
 *
 * @author vhung
 */
public class DAO_CongViec {
    ArrayList<Model_CongViec> danhsachCongViec = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Model_CongViec> layDanhSachCongViec() {
        try {
            Connection conn = DAO_KetNoi.getConnection();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("select * from CONGVIEC");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Model_CongViec cv = new Model_CongViec();
                    cv.setMaQuyen(rs.getInt(1));
                    cv.setTenCongViec(rs.getString(2));                 
                    danhsachCongViec.add(cv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsachCongViec;
    }

    public boolean insertCongViec(Model_CongViec cv) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        String sql = "INSERT INTO CONGVIEC ( TENCONGVIEC) VALUES (?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, cv.getTenCongViec());
                        

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
    public static boolean deleteCongViec(int maQuyen) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM CONGVIEC WHERE MaQuyen = ?");
                        ps.setInt(1, maQuyen);
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
public static boolean updateCongViec(int maQuyen, String tenCongViec) {
    boolean updated = false;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = DAO_KetNoi.getConnection();
        conn.setAutoCommit(false); // Bắt đầu giao dịch

        String query = "UPDATE CONGVIEC SET TenCongViec=? WHERE MaQuyen=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, tenCongViec);
        ps.setInt(2, maQuyen); // Đặt tham số MaQuyen

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
