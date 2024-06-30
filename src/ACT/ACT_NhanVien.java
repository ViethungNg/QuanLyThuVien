/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACT;

import DAO.DAO_NhanVien;
import GUI.GUI_Home;
import Model.Model_NhanVien;
import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vhung
 */
public class ACT_NhanVien {
    public void loadbangnhanvien(JTable table) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Cho phép chỉnh sửa ô
            }
        };

        Object[] title = {"Mã nhân viên", "Họ tên", "Ngày sinh", "Địa chỉ", "SĐT", "Email", "Mã quyền", "Tên đăng nhập"};
        model.setColumnIdentifiers(title);

        List<Model_NhanVien> danhsachNhanVien = new DAO_NhanVien().layDanhSachNhanVien();
        for (Model_NhanVien nv : danhsachNhanVien) {
            Object[] row = {nv.getMaNhanVien(), nv.getHoTen(), nv.getNgaySinh(), nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), nv.getMaQuyen(), nv.getTenDangNhap()};
            model.addRow(row);    
        }

        table.setModel(model);
    }

    
    
   public void themNhanVien(String hoTen, String ngaySinh, String diaChi, String soDienThoai, String email, int maQuyen, String tenDangNhap, JTable table, JTextField txtHoTen, JDateChooser dateChooser, JTextField txtDiaChi, JTextField txtSoDienThoai, JTextField txtEmail, JComboBox<String> cbbMaQuyen, JTextField txtTenDangNhap, JTextField them_txt_TenDangNhap) {
        try {
            Model_NhanVien nv = new Model_NhanVien();
            nv.setHoTen(hoTen);

            // Chuyển đổi ngày sinh từ String "yyyy/MM/dd" thành java.sql.Date nếu cần
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date ngaySinhDate = dateFormat.parse(ngaySinh);
            nv.setNgaySinh(new java.sql.Date(ngaySinhDate.getTime()));

            nv.setDiaChi(diaChi);
            nv.setSoDienThoai(soDienThoai);
            nv.setEmail(email);
            nv.setMaQuyen(maQuyen);
            nv.setTenDangNhap(tenDangNhap);

            DAO_NhanVien dao = new DAO_NhanVien();
            boolean isInserted = dao.insertNhanVien(nv);

            if (isInserted) {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                // Cập nhật lại bảng hiển thị nếu cần thiết
                loadbangnhanvien(table);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }
        
        public void suaTacGia(int maTacGia, String tenTacGia, int namSinh, int namMat, String quocTich, JTable table, GUI_Home guiHome) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(guiHome, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra xem có sự thay đổi so với dữ liệu ban đầu hay không
            boolean changed_TacGia = checkChanges_TacGia(maTacGia, tenTacGia, namSinh, namMat, quocTich, table);

            if (!changed_TacGia) {
                JOptionPane.showMessageDialog(guiHome, "Không có thay đổi cần cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Gọi phương thức update của DAO_TacGia để cập nhật thông tin tác giả
            boolean updated_TacGia = DAO.DAO_TacGia.updateTacGia(maTacGia, tenTacGia, namSinh, namMat, quocTich);
            if (updated_TacGia) {
                // Thông báo cập nhật thành công
                JOptionPane.showMessageDialog(guiHome, "Đã cập nhật thông tin tác giả thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Sau khi cập nhật thành công, cập nhật lại bảng hiển thị
                guiHome.loadbangtacgia();
            } else {
                // Thông báo cập nhật không thành công
                JOptionPane.showMessageDialog(guiHome, "Có lỗi xảy ra trong quá trình cập nhật thông tin tác giả.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
}

private boolean checkChanges_TacGia(int maTacGia, String tenTacGia, int namSinh, int namMat, String quocTich, JTable table) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            int maTacGiaInTable = (int) table.getValueAt(selectedRow, 0);
            String tenTacGiaInTable = (String) table.getValueAt(selectedRow, 1);
            int namSinhInTable = (int) table.getValueAt(selectedRow, 2);
            int namMatInTable = (int) table.getValueAt(selectedRow, 3);
            String quocTichInTable = (String) table.getValueAt(selectedRow, 4);

            // So sánh các giá trị
            if (maTacGia != maTacGiaInTable ||
                !tenTacGia.equals(tenTacGiaInTable) ||
                namSinh != namSinhInTable ||
                namMat != namMatInTable ||
                !quocTich.equals(quocTichInTable)) {
                return true; // Có sự thay đổi
            }

            return false; // Không có sự thay đổi
}

        
        
        public void xoaTacGia(JTable table, GUI_Home guiHome) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(guiHome, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ hàng được chọn
            int maTacGia = (int) table.getValueAt(selectedRow, 0);
            String tenTacGia = (String) table.getValueAt(selectedRow, 1); // Ví dụ lấy tên tác giả để hiển thị trong hộp thoại xác nhận

            // Hiển thị hộp thoại xác nhận
            int option = JOptionPane.showConfirmDialog(guiHome, "Bạn có chắc chắn muốn xóa tác giả \"" + tenTacGia + "\" không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Xóa tác giả từ CSDL
                boolean deleted = DAO.DAO_TacGia.deleteTacGia(maTacGia);

                if (deleted) {
                    JOptionPane.showMessageDialog(guiHome, "Xóa tác giả thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật lại bảng hiển thị sau khi xóa
                    guiHome.loadbangtacgia();
                } else {
                    JOptionPane.showMessageDialog(guiHome, "Xóa tác giả thất bại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    
}
