/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACT;

import DAO.DAO_NhaXuatBan;
import GUI.GUI_Home;
import Model.Model_NhaXuatBan;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vhung
 */
public class ACT_NhaXuatBan {
       public void loadbangnhaxuatban(JTable table) {
        
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true; // Cho phép chỉnh sửa ô
        }
    };

    Object[] title = {"Mã nhà xuất bản", "Tên nhà xuất bản", "Địa chỉ", "Số điện thoại", "Email"};
    model.setColumnIdentifiers(title);

    List<Model_NhaXuatBan> danhsachNhaXuatBan;
            danhsachNhaXuatBan = new DAO_NhaXuatBan().layDanhSachNhaXuatBan();
    for (Model_NhaXuatBan nxb : danhsachNhaXuatBan) {
        Object[] row = {nxb.getMaNhaXuatBan(), nxb.getTenNhaXuatBan(), nxb.getDiaChi(), nxb.getSoDienThoai(), nxb.getEmail()};
        model.addRow(row);    
    }
    
    table.setModel(model);
}
       
  public void themNhaXuatBan(String tenNhaXuatBan, String diaChi, String soDienThoai, String Email, JTable table, JTextField them_txt_tenNhaXuatBan, JTextField them_txt_diaChi, JTextField them_txt_soDienThoai, JTextField them_txt_email) {
       try {
            // Giả sử bạn có các JTextField cho các trường dữ liệu tương ứng


            Model_NhaXuatBan nxb = new Model_NhaXuatBan();
            nxb.setTenNhaXuatBan(tenNhaXuatBan);
            nxb.setDiaChi(diaChi);
            nxb.setSoDienThoai(soDienThoai);
            nxb.setEmail(Email);

            DAO_NhaXuatBan dao = new DAO_NhaXuatBan();
            boolean isInserted = dao.insertNhaXuatBan(nxb);

            // Trả về rỗng
            them_txt_tenNhaXuatBan.setText(""); 
            them_txt_diaChi.setText(""); 
            them_txt_soDienThoai.setText(""); 
            them_txt_email.setText(""); 

            if (isInserted) {
                JOptionPane.showMessageDialog(null, "Thêm nhà xuất bản thành công!");
                // Cập nhật lại bảng hiển thị nếu cần thiết
                loadbangnhaxuatban(table);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm nhà xuất bản thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
  }
  
  public void suaNhaXuatBan(int maNhaXuatBan, String tenNhaXuatBan, String diaChi, String soDienThoai, String Email, JTable table, GUI_Home guiHome ){
                  // Kiểm tra xem có sự thay đổi so với dữ liệu ban đầu hay không
            boolean changed_nhaXuatBan = checkChanges_NhaXuatBan(maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai, Email,table);

            if (!changed_nhaXuatBan) {
                JOptionPane.showMessageDialog(guiHome, "Không có thay đổi cần cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Gọi phương thức update của DAO_NhaXuatBan để cập nhật thông tin tác giả
            boolean updated_NhaXuatBan = DAO.DAO_NhaXuatBan.updateNhaXuatBan( maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai, Email);
            if (updated_NhaXuatBan) {
                // Thông báo cập nhật thành công
                JOptionPane.showMessageDialog(guiHome, "Đã cập nhật thông tin nhà xuất bản thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Sau khi cập nhật thành công, cập nhật lại bảng hiển thị
                guiHome.loadbangnhaxuatban();
            } else {
                // Thông báo cập nhật không thành công
                JOptionPane.showMessageDialog(guiHome, "Có lỗi xảy ra trong quá trình cập nhật thông tin nhà xuất bản.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
    } 
  
  private boolean checkChanges_NhaXuatBan(int maNhaXuatBan, String tenNhaXuatBan, String diaChi, String soDienThoai, String email, JTable table) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                int maNhaXuatBanInTable = (int) table.getValueAt(selectedRow, 0);
                String tenNhaXuatBanInTable = (String) table.getValueAt(selectedRow, 1);
                String diaChiInTable = (String) table.getValueAt(selectedRow, 2);
                String soDienThoaiInTable = (String) table.getValueAt(selectedRow, 3);
                String emailInTable = (String) table.getValueAt(selectedRow, 4);

                // So sánh các giá trị
                if (maNhaXuatBan != maNhaXuatBanInTable ||
                    !tenNhaXuatBan.equals(tenNhaXuatBanInTable) ||
                    diaChi != diaChiInTable ||
                    soDienThoai != soDienThoaiInTable ||
                    !email.equals(emailInTable)) {
                    return true; // Có sự thay đổi
                }

                return false; // Không có sự thay đổi
            }
      
  public void xoaNhaXuatBan(JTable table, GUI_Home guiHome) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(guiHome, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ hàng được chọn
            int maNhaXuatBan = (int) table.getValueAt(selectedRow, 0);
            String tenNhaXuatBan = (String) table.getValueAt(selectedRow, 1); // Ví dụ lấy tên tác giả để hiển thị trong hộp thoại xác nhận

            // Hiển thị hộp thoại xác nhận
            int option = JOptionPane.showConfirmDialog(guiHome, "Bạn có chắc chắn muốn xóa nhà xuất bản \"" + tenNhaXuatBan + "\" không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Xóa nhà xuất bản từ CSDL
                boolean deleted = DAO.DAO_NhaXuatBan.deleteNhaXuatBan(maNhaXuatBan);

                if (deleted) {
                    JOptionPane.showMessageDialog(guiHome, "Xóa nhà xuất bản thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật lại bảng hiển thị sau khi xóa
                    guiHome.loadbangnhaxuatban();
                } else {
                    JOptionPane.showMessageDialog(guiHome, "Xóa tác giả thất bại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
}
