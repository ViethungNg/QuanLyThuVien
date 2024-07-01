/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACT;

import DAO.DAO_Sach;
import GUI.GUI_Home;
import Model.Model_Sach;
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
public class ACT_Sach {
    public void loadbangsach(JTable table) {
        
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true; // Cho phép chỉnh sửa ô
        }
    };

    Object[] title = {"Mã sách", "Tên sách", "Mã TG", "Mã NXB", "Thể loại","Năm XB", "Số trang", "Ngôn ngữ", "Số lượng", "Giá thuê", "Giá bán"};
    model.setColumnIdentifiers(title);

    List<Model_Sach> danhsachSach;
            danhsachSach = new DAO_Sach().layDanhSachSach();
    for (Model_Sach nxb : danhsachSach) {
        Object[] row = {nxb.getMaSach(), nxb.getTenSach(), nxb.getMaTacGia(), nxb.getMaNhaXuatBan(), nxb.getTheLoai(), nxb.getNamXuatBan(), nxb.getSoTrang(), nxb.getNgonNgu(), nxb.getSoLuong(), nxb.getGiaThue(), nxb.getGiaBan()};
        model.addRow(row);    
    }
    
    table.setModel(model);
}
       
  public void themSach(String tenSach, 
          int maTacGia, 
          int maNhaXuaBan, 
          String theLoai, 
          int namXuatBan, 
          int soTrang, 
          String ngonNgu, 
          int soLuong, 
          float giaThue, 
          float giaBan, 
          JTable table, 
          JTextField them_txt_tenSach, 
          JComboBox<String> them_cbb_maTacGia, 
          JComboBox<String> them_cbb_maNhaXuatBan, 
          JTextField them_txt_theLoai, 
          JTextField them_txt_namXuatBan, 
          JTextField them_txt_soTrang, 
          JTextField them_txt_ngonNgu, 
          JTextField them_txt_soLuong, 
          JTextField them_txt_giaThue, 
          JTextField them_txt_giaBan) {
       try {

            Model_Sach s = new Model_Sach();
            s.setTenSach(tenSach);
            s.setMaTacGia(maTacGia);
            s.setMaNhaXuatBan(maNhaXuaBan);
            s.setTheLoai(theLoai);
            s.setNamXuatBan(namXuatBan);
            s.setSoTrang(soTrang);
            s.setNgonNgu(ngonNgu);
            s.setSoLuong(soLuong);
            s.setGiaThue(giaThue);
            s.setGiaBan(giaBan);

            DAO_Sach dao = new DAO_Sach();
            boolean isInserted = dao.insertSach(s);

            // Trả về rỗng
            them_txt_tenSach.setText(""); 
            them_cbb_maTacGia.setSelectedIndex(-1); 
            them_cbb_maNhaXuatBan.setSelectedIndex(-1);
            them_txt_theLoai.setText(""); 
            them_txt_namXuatBan.setText(""); 
            them_txt_soTrang.setText(""); 
            them_txt_ngonNgu.setText(""); 
            them_txt_soLuong.setText(""); 
            them_txt_giaThue.setText(""); 
            them_txt_giaBan.setText(""); 

//
            if (isInserted) {
                JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
                // Cập nhật lại bảng hiển thị nếu cần thiết
                loadbangsach(table);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm sách bản thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
  }

 
 public void suaSach( int maSach, String tenSach, int maTacGia, int maNhaXuatBan, String theLoai, int namXuatBan, int soTrang, String ngonNgu, int soLuong, float giaThue, float giaBan, JTable table, GUI_Home guiHome ){
                  // Kiểm tra xem có sự thay đổi so với dữ liệu ban đầu hay không
            boolean changed_sach = checkChanges_Sach(maSach, tenSach, maTacGia, maNhaXuatBan, theLoai, namXuatBan, soTrang, ngonNgu, soLuong, giaThue, giaBan, table);

            if (!changed_sach) {
                JOptionPane.showMessageDialog(guiHome, "Không có thay đổi cần cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Gọi phương thức update của Sach để cập nhật thông tin sách
            boolean updated_Sach = DAO.DAO_Sach.updateSach( maSach, tenSach, maTacGia, maNhaXuatBan, theLoai, namXuatBan, soTrang, ngonNgu, soLuong, giaThue, giaBan);
            if (updated_Sach) {
                // Thông báo cập nhật thành công
                JOptionPane.showMessageDialog(guiHome, "Đã cập nhật thông tin sách thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Sau khi cập nhật thành công, cập nhật lại bảng hiển thị
                guiHome.loadbangsach();
            } else {
                // Thông báo cập nhật không thành công
                JOptionPane.showMessageDialog(guiHome, "Có lỗi xảy ra trong quá trình cập nhật thông tin sách.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
    } 
  //
  private boolean checkChanges_Sach(int maSach, String tenSach, int maTacGia, int maNhaXuatBan, String theLoai, int namXuatBan, int soTrang, String ngonNgu, int soLuong, float giaThue, float giaBan, JTable table) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                int maSachInTable = (int) table.getValueAt(selectedRow, 0);
                String tenSachInTable = (String) table.getValueAt(selectedRow, 1);
                int maTacGiaInTable = (int) table.getValueAt(selectedRow, 2);
                int maNhaXuatBanInTable = (int) table.getValueAt(selectedRow, 3);
                String theLoaiInTable = (String) table.getValueAt(selectedRow, 4);
                int namXuatBanInTable = (int) table.getValueAt(selectedRow, 5);
                int soTrangInTable = (int) table.getValueAt(selectedRow, 6);
                String ngonNguInTable = (String) table.getValueAt(selectedRow, 7);
                int soLuongInTable = (int) table.getValueAt(selectedRow, 8);
                float giaThueInTable = (float) table.getValueAt(selectedRow, 9);
                float giaBanInTable = (float) table.getValueAt(selectedRow, 10);

            if (maTacGia != maTacGiaInTable ||
                maNhaXuatBan != maNhaXuatBanInTable ||
                !tenSach.equals(tenSachInTable) ||
                !theLoai.equals(theLoaiInTable) ||
                namXuatBan != namXuatBanInTable ||
                soTrang != soTrangInTable ||
                !ngonNgu.equals(ngonNguInTable) ||
                soLuong != soLuongInTable ||
                giaThue != giaThueInTable ||
                giaBan != giaBanInTable) {
                return true; // Có sự thay đổi
            }
                    return false; // Không có sự thay đổi
                }

    public void xoaSach(JTable table, GUI_Home guiHome) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(guiHome, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Lấy thông tin từ hàng được chọn
                int maSach = (int) table.getValueAt(selectedRow, 0);
                String tenSach = (String) table.getValueAt(selectedRow, 1); // Ví dụ lấy tên sách để hiển thị trong hộp thoại xác nhận

                // Hiển thị hộp thoại xác nhận
                int option = JOptionPane.showConfirmDialog(guiHome, "Bạn có chắc chắn muốn xóa sách \"" + tenSach + "\" không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Xóa sách từ CSDL
                    boolean deleted = DAO.DAO_Sach.deleteSach(maSach);

                    if (deleted) {
                        JOptionPane.showMessageDialog(guiHome, "Xóa sách thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        // Cập nhật lại bảng hiển thị sau khi xóa
                        guiHome.loadbangsach();
                    } else {
                        JOptionPane.showMessageDialog(guiHome, "Xóa sách thất bại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

    
}
