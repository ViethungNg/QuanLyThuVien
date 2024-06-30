/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Model_NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author vhung
 */
public class DAO_NhanVien {
    ArrayList<Model_NhanVien> danhsachNhanVien = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public ArrayList<Model_NhanVien> layDanhSachNhanVien() {
        try {
            Connection conn = DAO_KetNoi.getConnection();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("select * from NHANVIEN");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Model_NhanVien nv = new Model_NhanVien();
                    nv.setMaNhanVien(rs.getInt(1));
                    nv.setHoTen(rs.getString(2));
                    nv.setNgaySinh(rs.getDate(3));
                    nv.setDiaChi(rs.getString(4));
                    nv.setSoDienThoai(rs.getString(5));
                    nv.setEmail(rs.getString(6));
                    nv.setMaQuyen(rs.getInt(7));
                    nv.setTenDangNhap(rs.getString(8));
                    nv.setMatKhau(rs.getString(9));
                    danhsachNhanVien.add(nv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhsachNhanVien;
    }
    
    
   public boolean insertNhanVien(Model_NhanVien nv) {
            boolean result = false;
            try {
                Connection conn = DAO_KetNoi.getConnection();
                if (conn != null) {
                    String sql = "INSERT INTO NHANVIEN (HOTEN, NGAYSINH, DIACHI, SODIENTHOAI, EMAIL, MAQUYEN, TENDANGNHAP, MATKHAU) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, nv.getHoTen());
                    ps.setDate(2, (Date) nv.getNgaySinh());
                    ps.setString(3, nv.getDiaChi());
                    ps.setString(4, nv.getSoDienThoai());
                    ps.setString(5, nv.getEmail());
                    ps.setInt(6, nv.getMaQuyen());
                    ps.setString(7, nv.getTenDangNhap());
                    ps.setString(8, nv.getMatKhau());

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
   
    public static boolean deleteNhanVien(int maNhanVien) {
                boolean result = false;
                try {
                    Connection conn = DAO_KetNoi.getConnection();
                    if (conn != null) {
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM NHANVIEN WHERE MaNhanVien = ?");
                        ps.setInt(1, maNhanVien);
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
public static boolean updateNhanVien(int maNhanVien, String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String email, int maQuyen) {
            boolean updated = false;
            Connection conn = null;
            PreparedStatement ps = null;

            try {
                conn = DAO_KetNoi.getConnection();
                conn.setAutoCommit(false); // Bắt đầu giao dịch

                String query = "UPDATE NHANVIEN SET HoTen=?, NgaySinh=?, DiaChi=?, SoDienThoai=?, Email=?, MaQuyen=? WHERE MaNhanVien=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, hoTen);
                ps.setDate(2, ngaySinh);
                ps.setString(3, diaChi);
                ps.setString(4, soDienThoai);
                ps.setString(5, email);
                ps.setInt(6, maQuyen);
                ps.setInt(7, maNhanVien); // Đặt tham số MaTacGia

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
