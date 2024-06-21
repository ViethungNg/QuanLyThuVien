/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACT;

import DAO.DAO_TacGia;
import GUI.GUI_Home;
import Model.Model_TacGia;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ACT_TacGia {

    public void loadbangtacgia(JTable table) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Cho phép chỉnh sửa ô
            }
        };

        Object[] title = {"Mã tác giả", "Tên tác giả", "Năm sinh", "Năm mất", "Quốc tịch"};
        model.setColumnIdentifiers(title);

        List<Model_TacGia> danhsachTacGia = new DAO_TacGia().layDanhSachTacGia();
        for (Model_TacGia tg : danhsachTacGia) {
            Object[] row = {tg.getMaTacGia(), tg.getTenTacGia(), tg.getNamSinh(), tg.getNamMat(), tg.getQuocTich()};
            model.addRow(row);    
        }

        table.setModel(model);
    }

    
    
    public void themTacGia(String tenTacGia, int namSinh, int namMat, String quocTich, JTable table, JTextField them_txt_tenTacGia, JTextField them_txt_namSinh, JTextField them_txt_namMat, JTextField them_txt_quocTich) {
        try {
            Model_TacGia tg = new Model_TacGia();
            tg.setTenTacGia(tenTacGia);
            tg.setNamSinh(namSinh);
            tg.setNamMat(namMat);
            tg.setQuocTich(quocTich);

            DAO_TacGia dao = new DAO_TacGia();
            boolean isInserted = dao.insertTacGia(tg);

            // Trả về rỗng
            them_txt_tenTacGia.setText(""); 
            them_txt_namSinh.setText(""); 
            them_txt_namMat.setText(""); 
            them_txt_quocTich.setText(""); 

            if (isInserted) {
                JOptionPane.showMessageDialog(null, "Thêm tác giả thành công!");
                // Cập nhật lại bảng hiển thị nếu cần thiết
                loadbangtacgia(table);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm tác giả thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }
    
        public void suaTacGia(int maTacGia, String tenTacGia, int namSinh, int namMat, String quocTich, JTable table, GUI_Home guiHome) {
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

