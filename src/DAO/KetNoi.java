/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KetNoi {
    static String connurl = "jdbc:sqlserver://VIETHUNG\\SQLEXPRESS:1433;databaseName=QuanLyThuVien;user=sa;password=Viethung1609;encrypt=false;";
    static Connection conn;

    // Phương thức mở kết nối và trả về đối tượng Connection
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(connurl);
                System.out.println("Kết nối thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    // Phương thức đóng kết nối
    public static boolean close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đóng kết nối thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đóng kết nối: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            // Sử dụng try-with-resources để tự đóng kết nối sau khi sử dụng
            try (Connection connection = DriverManager.getConnection(connurl)) {
                System.out.println("Kết nối thành công!");
            } catch (SQLException ex) {
                System.err.println("Lỗi kết nối: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



