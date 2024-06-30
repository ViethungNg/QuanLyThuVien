/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACT;

import DAO.DAO_CongViec;
import GUI.GUI_Home;
import Model.Model_CongViec;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
// Đây là main
/**
 *
 * @author vhung
 */
public class ACT_CongViec {
   public void loadbangcongviec(JTable table) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Cho phép chỉnh sửa ô
            }
        };

        Object[] title = {"Mã quyền", "Tên công việc"};
        model.setColumnIdentifiers(title);

        List<Model_CongViec> danhsachCongViec = new DAO_CongViec().layDanhSachCongViec();
        for (Model_CongViec tg : danhsachCongViec) {
            Object[] row = {tg.getMaQuyen(), tg.getTenCongViec()};
            model.addRow(row);    
        }

        table.setModel(model);
    }

    
    
    public void themCongViec(String tenCongViec, JTable table, JTextField them_txt_tenCongViec) {
        try {
            Model_CongViec cv = new Model_CongViec();
            cv.setTenCongViec(tenCongViec);

            DAO_CongViec dao = new DAO_CongViec();
            boolean isInserted = dao.insertCongViec(cv);

            // Trả về rỗng
            them_txt_tenCongViec.setText("");             

            if (isInserted) {
                JOptionPane.showMessageDialog(null, "Thêm công việc thành công!");
                // Cập nhật lại bảng hiển thị nếu cần thiết
                loadbangcongviec(table);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm công việc thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }
        
        public void suaCongViec(int maQuyen, String tenCongViec, JTable table, GUI_Home guiHome) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(guiHome, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra xem có sự thay đổi so với dữ liệu ban đầu hay không
            boolean changed_CongViec = checkChanges_CongViec(maQuyen, tenCongViec, table);

            if (!changed_CongViec) {
                JOptionPane.showMessageDialog(guiHome, "Không có thay đổi cần cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Gọi phương thức update của DAO_CONGVIEC để cập nhật thông tin công việc
            boolean updated_CongViec = DAO.DAO_CongViec.updateCongViec(maQuyen, tenCongViec);
            if (updated_CongViec) {
                // Thông báo cập nhật thành công
                JOptionPane.showMessageDialog(guiHome, "Đã cập nhật thông tin công việc thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Sau khi cập nhật thành công, cập nhật lại bảng hiển thị
                guiHome.loadbangcongviec();
            } else {
                // Thông báo cập nhật không thành công
                JOptionPane.showMessageDialog(guiHome, "Có lỗi xảy ra trong quá trình cập nhật thông tin công việc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
}

private boolean checkChanges_CongViec(int maQuyen, String tenCongViec, JTable table) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            int maQuyenInTable = (int) table.getValueAt(selectedRow, 0);
            String tenCongViecInTable = (String) table.getValueAt(selectedRow, 1);
            

            // So sánh các giá trị
            if (maQuyen != maQuyenInTable ||
                !tenCongViec.equals(tenCongViecInTable))
                 {
                return true; // Có sự thay đổi
            }

            return false; // Không có sự thay đổi
}

        
        
        public void xoaCongViec(JTable table, GUI_Home guiHome) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(guiHome, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ hàng được chọn
            int maQuyen = (int) table.getValueAt(selectedRow, 0);
            String tenCongViec = (String) table.getValueAt(selectedRow, 1); // Ví dụ lấy tên công việc để hiển thị trong hộp thoại xác nhận

            // Hiển thị hộp thoại xác nhận
            int option = JOptionPane.showConfirmDialog(guiHome, "Bạn có chắc chắn muốn xóa công việc \"" + tenCongViec + "\" không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Xóa công việc từ CSDL
                boolean deleted = DAO.DAO_CongViec.deleteCongViec(maQuyen);

                if (deleted) {
                    JOptionPane.showMessageDialog(guiHome, "Xóa công việc thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật lại bảng hiển thị sau khi xóa
                    guiHome.loadbangcongviec();
                } else {
                    JOptionPane.showMessageDialog(guiHome, "Xóa công việc thất bại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        } 

   
}
