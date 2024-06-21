/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACT;

import DAO.DAO_NhaXuatBan;
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
}

