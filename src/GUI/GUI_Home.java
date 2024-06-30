/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
/**
 *
 * @author vhung
 */
import ACT.ACT_NhaXuatBan;
import ACT.ACT_TacGia;
import ACT.ACT_Sach;
import ACT.ACT_CongViec;
import ACT.ACT_NhanVien;
import DAO.DAO_KetNoi;
import DAO.DAO_TacGia;
import DAO.DAO_NhaXuatBan;
import DAO.DAO_NhanVien;
import Model.Model_CongViec;
import Model.Model_NhaXuatBan;
import Model.Model_NhanVien;
import Model.Model_Sach;
import Model.Model_TacGia;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GUI_Home extends javax.swing.JFrame {
    ArrayList<Model_TacGia> danhsachTacGia;
    ArrayList<Model_NhaXuatBan> danhsachNhaXuatBan;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    

    private static void setSelectedIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private ArrayList<Model_Sach> danhsachSach;
    private ArrayList<Model_CongViec> danhsachCongViec;
    private ArrayList<Model_NhanVien> danhsachNhanVien;
    
    public JTextField getHienThiTaiKhoanLabel() {
        return HienThiTaiKhoan;
    }
    private final String TenTaiKhoan;

    /**
     * Creates new form Home
     */
    public GUI_Home(String TenTaiKhoan) {
        
        this.TenTaiKhoan = TenTaiKhoan;
        
        initComponents();
        
        HienThiTaiKhoan.setText("Xin chào: "+ TenTaiKhoan);
        
        loadbangtacgia();
        
        loadbangnhaxuatban();
        
        loadbangsach();
            loadComboBox_them_cbb_maTacGia();
            loadComboBox_them_cbb_maNhaXuatBan();
            // Trả về rỗng
            them_cbb_maTacGia.setSelectedIndex(-1); 
            them_cbb_maNhaXuatBan.setSelectedIndex(-1);
            cbb_maTacGia.setSelectedIndex(-1); 
            cbb_maNhaXuatBan.setSelectedIndex(-1);
            cbb_maQuyen.setSelectedIndex(-1);
            them_cbb_MaQuyen.setSelectedIndex(-1);
        loadbangcongviec();    
        
        loadbangnhanvien();
            loadComboBox_them_cbb_maQuyen();
        
        TableHienThiTacGia.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Lấy chỉ số hàng được chọn
            int selectedRow_TacGia = TableHienThiTacGia.getSelectedRow();

            // Lấy dữ liệu từ hàng được chọn và hiển thị lên các JTextField
            txt_maTacGia.setText(TableHienThiTacGia.getValueAt(selectedRow_TacGia, 0).toString());
            txt_tenTacGia.setText(TableHienThiTacGia.getValueAt(selectedRow_TacGia, 1).toString());
            txt_namSinh.setText(TableHienThiTacGia.getValueAt(selectedRow_TacGia, 2).toString());
            txt_namMat.setText(TableHienThiTacGia.getValueAt(selectedRow_TacGia, 3).toString());
            txt_quocTich.setText(TableHienThiTacGia.getValueAt(selectedRow_TacGia, 4).toString());
             }
            }
        );
        TableHienThiNhaXuatBan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Lấy chỉ số hàng được chọn
                    int selectedRow = TableHienThiNhaXuatBan.getSelectedRow();

                    // Lấy dữ liệu từ hàng được chọn và hiển thị lên các JTextField
                    txt_maNhaXuatBan.setText(TableHienThiNhaXuatBan.getValueAt(selectedRow, 0).toString());
                    txt_tenNhaXuatBan.setText(TableHienThiNhaXuatBan.getValueAt(selectedRow, 1).toString());
                    txt_diaChi.setText(TableHienThiNhaXuatBan.getValueAt(selectedRow, 2).toString());
                    txt_soDienThoai.setText(TableHienThiNhaXuatBan.getValueAt(selectedRow, 3).toString());
                    txt_email.setText(TableHienThiNhaXuatBan.getValueAt(selectedRow, 4).toString());
                }
             }
         );
        
        TableHienThiSach.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Lấy chỉ số hàng được chọn
                    int selectedRow = TableHienThiSach.getSelectedRow();

                    // Lấy dữ liệu từ hàng được chọn và hiển thị lên các JTextField
                    txt_maSach.setText(TableHienThiSach.getValueAt(selectedRow, 0).toString());
                    txt_tenSach.setText(TableHienThiSach.getValueAt(selectedRow, 1).toString());
                    
                    String selectedTacGiaItem = (String) cbb_maTacGia.getSelectedItem();
                        int maTacGia = getMaTacGiaFromComboBox(selectedTacGiaItem);
                        String selectedNhaXuatBanItem = (String) them_cbb_maNhaXuatBan.getSelectedItem();
                        int maNhaXuatBan = getMaNhaXuatBanFromComboBox(selectedNhaXuatBanItem);
                    
                    //cbb_maTacGia.setSelectedItem(TableHienThiSach.getValueAt(selectedRow, 2).toString());
                    //cbb_maNhaXuatBan.setSelectedItem(TableHienThiSach.getValueAt(selectedRow, 3).toString());
                    txt_theLoai.setText(TableHienThiSach.getValueAt(selectedRow, 4).toString());
                    txt_namXuatBan.setText(TableHienThiSach.getValueAt(selectedRow, 5).toString());
                    txt_soTrang.setText(TableHienThiSach.getValueAt(selectedRow, 6).toString());
                    txt_ngonNgu.setText(TableHienThiSach.getValueAt(selectedRow, 7).toString());
                    txt_soLuong.setText(TableHienThiSach.getValueAt(selectedRow, 8).toString());
                    txt_giaThue.setText(TableHienThiSach.getValueAt(selectedRow, 9).toString());
                    txt_giaBan.setText(TableHienThiSach.getValueAt(selectedRow, 10).toString());
                }
             }
         );
        
        TableHienThiCongViec.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Lấy chỉ số hàng được chọn
            int selectedRow_CongViec = TableHienThiCongViec.getSelectedRow();

            // Lấy dữ liệu từ hàng được chọn và hiển thị lên các JTextField
            txt_maQuyen.setText(TableHienThiCongViec.getValueAt(selectedRow_CongViec, 0).toString());
            txt_tenCongViec.setText(TableHienThiCongViec.getValueAt(selectedRow_CongViec, 1).toString());
             }
            }
        );
        
//        TableHienThiNhanVien.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    // Lấy chỉ số hàng được chọn
//                    int selectedRow = TableHienThiNhanVien.getSelectedRow();
//
//                    // Lấy dữ liệu từ hàng được chọn và hiển thị lên các JTextField
//                    txt_maNhanVien.setText(TableHienThiNhanVien.getValueAt(selectedRow, 0).toString());
//                    txt_tenSach.setText(TableHienThiNhanVien.getValueAt(selectedRow, 1).toString());
//                    
//                    String selectedTacGiaItem = (String) cbb_maTacGia.getSelectedItem();
//                        int maTacGia = getMaTacGiaFromComboBox(selectedTacGiaItem);
//                        String selectedNhaXuatBanItem = (String) them_cbb_maNhaXuatBan.getSelectedItem();
//                        int maNhaXuatBan = getMaNhaXuatBanFromComboBox(selectedNhaXuatBanItem);
//                    
//                    //cbb_maTacGia.setSelectedItem(TableHienThiSach.getValueAt(selectedRow, 2).toString());
//                    //cbb_maNhaXuatBan.setSelectedItem(TableHienThiSach.getValueAt(selectedRow, 3).toString());
//                    txt_theLoai.setText(TableHienThiNhanVien.getValueAt(selectedRow, 4).toString());
//                    txt_namXuatBan.setText(TableHienThiNhanVien.getValueAt(selectedRow, 5).toString());
//                    txt_soTrang.setText(TableHienThiNhanVien.getValueAt(selectedRow, 6).toString());
//                    txt_ngonNgu.setText(TableHienThiNhanVien.getValueAt(selectedRow, 7).toString());
//                    txt_soLuong.setText(TableHienThiNhanVien.getValueAt(selectedRow, 8).toString());
//                    txt_giaThue.setText(TableHienThiNhanVien.getValueAt(selectedRow, 9).toString());
//                    txt_giaBan.setText(TableHienThiNhanVien.getValueAt(selectedRow, 10).toString());
//                }
//             }
//         );
    }
    
    
public void loadbangtacgia() {
        
                                    DefaultTableModel model = new DefaultTableModel() {
                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                            return true; // Cho phép chỉnh sửa ô
                                        }
                                    };

                                    Object[] title = {"Mã tác giả", "Tên tác giả", "Năm sinh", "Năm mất", "Quốc tịch"};
                                    model.setColumnIdentifiers(title);

                                    danhsachTacGia = new DAO.DAO_TacGia().layDanhSachTacGia();
                                    for (Model_TacGia tg : danhsachTacGia) {
                                        Object[] row = {tg.getMaTacGia(), tg.getTenTacGia(), tg.getNamSinh(), tg.getNamMat(), tg.getQuocTich()};
                                        model.addRow(row);    
                                    }

                                    TableHienThiTacGia.setModel(model);
}
    
public void loadbangnhaxuatban() {
        
                                    DefaultTableModel model = new DefaultTableModel() {
                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                            return true; // Cho phép chỉnh sửa ô
                                        }
                                    };

                                    Object[] title = {"Mã nhà xuất bản", "Tên nhà xuất bản", "Địa chỉ", "Số điện thoại", "Email"};
                                    model.setColumnIdentifiers(title);

                                    danhsachNhaXuatBan = new DAO.DAO_NhaXuatBan().layDanhSachNhaXuatBan();
                                    for (Model_NhaXuatBan nxb : danhsachNhaXuatBan) {
                                        Object[] row = {nxb.getMaNhaXuatBan(), nxb.getTenNhaXuatBan(), nxb.getDiaChi(), nxb.getSoDienThoai(), nxb.getEmail()};
                                        model.addRow(row);    
                                    }

                                    TableHienThiNhaXuatBan.setModel(model);
}
public void loadbangsach() {
        
                                    DefaultTableModel model = new DefaultTableModel() {
                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                            return true; // Cho phép chỉnh sửa ô
                                        }
                                    };

                                    Object[] title = {"Mã sách", "Tên sách", "Mã TG", "Mã NXB", "Thể loại","Năm XB", "Số trang", "Ngôn ngữ", "Số lượng", "Giá thuê", "Giá bán"};
                                    model.setColumnIdentifiers(title);

                                    danhsachSach = new DAO.DAO_Sach().layDanhSachSach();
                                    for (Model_Sach s : danhsachSach) {
                                        Object[] row = {s.getMaSach(), s.getTenSach(), s.getMaTacGia(), s.getMaNhaXuatBan(), s.getTheLoai(), s.getNamXuatBan(), s.getSoTrang(), s.getNgonNgu(), s.getSoLuong(), s.getGiaThue(), s. getGiaBan()};
                                        model.addRow(row);    
                                    }

                                    TableHienThiSach.setModel(model);
    
    
}
 
public void loadComboBox_them_cbb_maTacGia() {
                                    them_cbb_maTacGia.removeAllItems(); // Xóa tất cả các item hiện tại trong ComboBox
                                    cbb_maTacGia.removeAllItems();
                                    List<Model_TacGia> danhsachTacGia = new DAO.DAO_TacGia().layDanhSachTacGia();
                                    for (Model_TacGia tg : danhsachTacGia) {
                                        String displayValue = tg.getMaTacGia() + " - " + tg.getTenTacGia();
                                        them_cbb_maTacGia.addItem(displayValue);
                                        cbb_maTacGia.addItem(displayValue);// Thêm chuỗi "MaTacGia - TenTacGia" vào ComboBox
                                    }
}

public void loadComboBox_them_cbb_maNhaXuatBan() {
                                    them_cbb_maNhaXuatBan.removeAllItems(); // Xóa tất cả các item hiện tại trong ComboBox
                                    cbb_maNhaXuatBan.removeAllItems();
                                    List<Model_NhaXuatBan> danhsachNhaXuatBan = new DAO.DAO_NhaXuatBan().layDanhSachNhaXuatBan();
                                    for (Model_NhaXuatBan nxb : danhsachNhaXuatBan) {
                                        String displayValue = nxb.getMaNhaXuatBan()+ " - " + nxb.getTenNhaXuatBan();
                                        them_cbb_maNhaXuatBan.addItem(displayValue);
                                        cbb_maNhaXuatBan.addItem(displayValue);// Thêm chuỗi "MaNhaXuatBan - TenNhaXuatBan" vào ComboBox
                                    }
}

public void loadbangcongviec() {
        
                                    DefaultTableModel model = new DefaultTableModel() {
                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                            return true; // Cho phép chỉnh sửa ô
                                        }
                                    };

                                    Object[] title = {"Mã quyền", "Tên công việc"};
                                    model.setColumnIdentifiers(title);

                                    danhsachCongViec = new DAO.DAO_CongViec().layDanhSachCongViec();
                                    for (Model_CongViec tg : danhsachCongViec) {
                                        Object[] row = {tg.getMaQuyen(), tg.getTenCongViec()};
                                        model.addRow(row);    
                                    }

                                    TableHienThiCongViec.setModel(model);
}

public void loadbangnhanvien() {
        
                                    DefaultTableModel model = new DefaultTableModel() {
                                        @Override
                                        public boolean isCellEditable(int row, int column) {
                                            return true; // Cho phép chỉnh sửa ô
                                        }
                                    };

                                    Object[] title = {"Mã nhân viên", "Họ tên", "Ngày sinh", "Địa chỉ", "SĐt", "Email", "Mã quyền", "Tên đăng nhập"};
                                    model.setColumnIdentifiers(title);

                                    danhsachNhanVien = new DAO.DAO_NhanVien().layDanhSachNhanVien();
                                    for (Model_NhanVien nv : danhsachNhanVien) {
                                        Object[] row = {nv.getMaNhanVien(), nv.getHoTen(), nv.getNgaySinh(), nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), nv.getMaQuyen(), nv.getTenDangNhap()};
                                        model.addRow(row);    
                                    }

                                    TableHienThiNhanVien.setModel(model);
}
public void loadComboBox_them_cbb_maQuyen() {
                                    them_cbb_MaQuyen.removeAllItems(); // Xóa tất cả các item hiện tại trong ComboBox
                                    cbb_maQuyen.removeAllItems();
                                    List<Model_CongViec> danhsachCongViec = new DAO.DAO_CongViec().layDanhSachCongViec();
                                    for (Model_CongViec cv : danhsachCongViec) {
                                        String displayValue = cv.getMaQuyen()+ " - " + cv.getTenCongViec();
                                        them_cbb_MaQuyen.addItem(displayValue);
                                        cbb_maQuyen.addItem(displayValue);// Thêm chuỗi "MaQuyen - TenCongViec" vào ComboBox
                                    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        Home = new javax.swing.JTabbedPane();
        QL_NhaXuatBan = new javax.swing.JPanel();
        btn_Sua_NhaXuatBan = new javax.swing.JButton();
        jScrollPane_NhaXuatBan = new javax.swing.JScrollPane();
        TableHienThiNhaXuatBan = new javax.swing.JTable();
        btn_Reset_NhaXuatBan = new javax.swing.JButton();
        btn_Them_NhaXuatBan = new javax.swing.JButton();
        btn_Xoa_NhaXuatBan = new javax.swing.JButton();
        jPanel_Sua = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txt_maNhaXuatBan = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_tenNhaXuatBan = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_diaChi = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        them_txt_email = new javax.swing.JTextField();
        them_txt_tenNhaXuatBan = new javax.swing.JTextField();
        them_txt_diaChi = new javax.swing.JTextField();
        them_txt_soDienThoai = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        QL_TacGia = new javax.swing.JPanel();
        btn_Sua_TacGia = new javax.swing.JButton();
        jScrollPane_TacGia = new javax.swing.JScrollPane();
        TableHienThiTacGia = new javax.swing.JTable();
        btn_Reset_TacGia = new javax.swing.JButton();
        btn_Them_TacGia = new javax.swing.JButton();
        btn_Xoa_TacGia = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_maTacGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tenTacGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_namSinh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namMat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_quocTich = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        them_txt_quocTich = new javax.swing.JTextField();
        them_txt_tenTacGia = new javax.swing.JTextField();
        them_txt_namSinh = new javax.swing.JTextField();
        them_txt_namMat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        QL_Sach = new javax.swing.JPanel();
        jPanel_Sua1 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txt_theLoai = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_maSach = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txt_giaBan = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txt_tenSach = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbb_maTacGia = new javax.swing.JComboBox<>();
        cbb_maNhaXuatBan = new javax.swing.JComboBox<>();
        txt_soLuong = new javax.swing.JTextField();
        txt_soTrang = new javax.swing.JTextField();
        txt_ngonNgu = new javax.swing.JTextField();
        txt_giaThue = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btn_Sua_Sach = new javax.swing.JButton();
        txt_namXuatBan = new javax.swing.JTextField();
        btn_Reset_Sach = new javax.swing.JButton();
        btn_Xoa_Sach = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        them_txt_tenSach = new javax.swing.JTextField();
        them_txt_soTrang = new javax.swing.JTextField();
        them_txt_ngonNgu = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        them_txt_giaBan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        them_txt_namXuatBan = new javax.swing.JTextField();
        them_txt_giaThue = new javax.swing.JTextField();
        them_txt_soLuong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        them_cbb_maTacGia = new javax.swing.JComboBox<>();
        them_cbb_maNhaXuatBan = new javax.swing.JComboBox<>();
        them_txt_theLoai = new javax.swing.JTextField();
        btn_Them_Sach = new javax.swing.JButton();
        jScrollPane_NhaXuatBan1 = new javax.swing.JScrollPane();
        TableHienThiSach = new javax.swing.JTable();
        DoiMatKhau = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txt_matKhauCu = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_matKhauMoi = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_matKhauXacNhan = new javax.swing.JTextField();
        btn_doiMatKhau = new javax.swing.JButton();
        QL_NhanVien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableHienThiNhanVien = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        them_txt_HoTen = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        them_date_ngaySinh = new com.toedter.calendar.JDateChooser();
        jLabel47 = new javax.swing.JLabel();
        them_txt_DiaChi_NhanVien = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        them_txt_soDienThoai_NhanVien = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        them_txt_Email_NhanVien = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        them_cbb_MaQuyen = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        them_txt_TenDangNhap = new javax.swing.JTextField();
        cbb_maQuyen = new javax.swing.JComboBox<>();
        btn_Them_NhanVien = new javax.swing.JButton();
        QL_CongViec = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableHienThiCongViec = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        them_txt_tenCongViec = new javax.swing.JTextField();
        btn_Them_CongViec = new javax.swing.JButton();
        btn_Sua_CongViec = new javax.swing.JButton();
        btn_Xoa_CongViec = new javax.swing.JButton();
        txt_tenCongViec = new javax.swing.JTextField();
        txt_maQuyen = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        ChucNang = new javax.swing.JPanel();
        btn_QuanLyTacGia = new javax.swing.JButton();
        btnQuanLyNhaXuatBan = new javax.swing.JButton();
        btnQuanLySach = new javax.swing.JButton();
        btnQuanLyMuon = new javax.swing.JButton();
        btnQuanLyDatSach = new javax.swing.JButton();
        btnDanhSachPhat = new javax.swing.JButton();
        btnQuanLyNhanVien = new javax.swing.JButton();
        btnCongViec = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        HienThiTaiKhoan = new javax.swing.JTextField();
        lbl_dangXuat = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 51));
        setMinimumSize(new java.awt.Dimension(1500, 811));
        setPreferredSize(new java.awt.Dimension(1540, 800));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Sua_NhaXuatBan.setText("Sửa");
        btn_Sua_NhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua_NhaXuatBanActionPerformed(evt);
            }
        });

        TableHienThiNhaXuatBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane_NhaXuatBan.setViewportView(TableHienThiNhaXuatBan);

        btn_Reset_NhaXuatBan.setText("Làm mới");
        btn_Reset_NhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Reset_NhaXuatBanActionPerformed(evt);
            }
        });

        btn_Them_NhaXuatBan.setText("Thêm");
        btn_Them_NhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_NhaXuatBanActionPerformed(evt);
            }
        });

        btn_Xoa_NhaXuatBan.setText("Xóa");
        btn_Xoa_NhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_NhaXuatBanActionPerformed(evt);
            }
        });

        jLabel29.setText("Mã NXB");

        txt_maNhaXuatBan.setEditable(false);
        txt_maNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maNhaXuatBanActionPerformed(evt);
            }
        });

        jLabel30.setText("Tên NXB");

        jLabel31.setText("Địa chỉ");

        jLabel32.setText("SĐT");

        txt_soDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soDienThoaiActionPerformed(evt);
            }
        });

        jLabel33.setText("Email");

        javax.swing.GroupLayout jPanel_SuaLayout = new javax.swing.GroupLayout(jPanel_Sua);
        jPanel_Sua.setLayout(jPanel_SuaLayout);
        jPanel_SuaLayout.setHorizontalGroup(
            jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SuaLayout.createSequentialGroup()
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenNhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(txt_maNhaXuatBan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_diaChi)
                    .addComponent(txt_soDienThoai)
                    .addComponent(txt_email))
                .addContainerGap())
        );
        jPanel_SuaLayout.setVerticalGroup(
            jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SuaLayout.createSequentialGroup()
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_maNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel34.setText("Email");

        jLabel35.setText("SĐT");

        jLabel36.setText("Địa chỉ");

        jLabel37.setText("Tên NXB");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(them_txt_soDienThoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(them_txt_diaChi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_txt_tenNhaXuatBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_txt_email))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_tenNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout QL_NhaXuatBanLayout = new javax.swing.GroupLayout(QL_NhaXuatBan);
        QL_NhaXuatBan.setLayout(QL_NhaXuatBanLayout);
        QL_NhaXuatBanLayout.setHorizontalGroup(
            QL_NhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_NhaXuatBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(QL_NhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(QL_NhaXuatBanLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_Them_NhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Sua_NhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Xoa_NhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Reset_NhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(229, Short.MAX_VALUE))
            .addGroup(QL_NhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(QL_NhaXuatBanLayout.createSequentialGroup()
                    .addGap(666, 666, 666)
                    .addComponent(jPanel_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(224, Short.MAX_VALUE)))
        );
        QL_NhaXuatBanLayout.setVerticalGroup(
            QL_NhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QL_NhaXuatBanLayout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(QL_NhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(QL_NhaXuatBanLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Them_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214)
                        .addComponent(btn_Sua_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Xoa_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Reset_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
            .addGroup(QL_NhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(QL_NhaXuatBanLayout.createSequentialGroup()
                    .addGap(279, 279, 279)
                    .addComponent(jPanel_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(371, Short.MAX_VALUE)))
        );

        Home.addTab("Nhà xuất bản", QL_NhaXuatBan);

        btn_Sua_TacGia.setText("Sửa");
        btn_Sua_TacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua_TacGiaActionPerformed(evt);
            }
        });

        TableHienThiTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane_TacGia.setViewportView(TableHienThiTacGia);

        btn_Reset_TacGia.setText("Làm mới");
        btn_Reset_TacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Reset_TacGiaActionPerformed(evt);
            }
        });

        btn_Them_TacGia.setText("Thêm");
        btn_Them_TacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_TacGiaActionPerformed(evt);
            }
        });

        btn_Xoa_TacGia.setText("Xóa");
        btn_Xoa_TacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_TacGiaActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(5, 2));

        jLabel3.setText("Mã tác giả");
        jPanel1.add(jLabel3);

        txt_maTacGia.setEditable(false);
        txt_maTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maTacGiaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_maTacGia);

        jLabel4.setText("Tên tác giả");
        jPanel1.add(jLabel4);
        jPanel1.add(txt_tenTacGia);

        jLabel5.setText("Năm sinh");
        jPanel1.add(jLabel5);
        jPanel1.add(txt_namSinh);

        jLabel6.setText("Năm mất");
        jPanel1.add(jLabel6);
        jPanel1.add(txt_namMat);

        jLabel7.setText("Quốc tịch");
        jPanel1.add(jLabel7);
        jPanel1.add(txt_quocTich);

        jLabel9.setText("Quốc tịch");

        jLabel10.setText("Năm mất");

        jLabel11.setText("Năm sinh");

        jLabel12.setText("Tên tác giả");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(them_txt_namMat, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_txt_namSinh, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_txt_tenTacGia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_txt_quocTich))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_tenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_namSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_namMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_quocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout QL_TacGiaLayout = new javax.swing.GroupLayout(QL_TacGia);
        QL_TacGia.setLayout(QL_TacGiaLayout);
        QL_TacGiaLayout.setHorizontalGroup(
            QL_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_TacGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(QL_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addGroup(QL_TacGiaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_Them_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btn_Sua_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Xoa_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Reset_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        QL_TacGiaLayout.setVerticalGroup(
            QL_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_TacGiaLayout.createSequentialGroup()
                .addGroup(QL_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QL_TacGiaLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Them_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Sua_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Xoa_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Reset_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(QL_TacGiaLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jScrollPane_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        Home.addTab("Tác giả", QL_TacGia);

        jLabel38.setText("Mã sách");

        txt_theLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_theLoaiActionPerformed(evt);
            }
        });

        jLabel39.setText("Tên sách");

        jLabel40.setText("Mã TG");

        txt_maSach.setEditable(false);

        jLabel41.setText("Mã NXB");

        txt_giaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giaBanActionPerformed(evt);
            }
        });

        jLabel42.setText("Thể loại");

        jLabel16.setText("Ngôn ngữ");

        jLabel17.setText("S Lượng");

        jLabel18.setText("Giá thuê");

        jLabel19.setText("Giá bán");

        cbb_maTacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_maTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_maTacGiaActionPerformed(evt);
            }
        });

        cbb_maNhaXuatBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_maNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_maNhaXuatBanActionPerformed(evt);
            }
        });

        txt_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soLuongActionPerformed(evt);
            }
        });

        txt_soTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soTrangActionPerformed(evt);
            }
        });

        txt_ngonNgu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngonNguActionPerformed(evt);
            }
        });

        txt_giaThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giaThueActionPerformed(evt);
            }
        });

        jLabel21.setText("Số trang");

        jLabel22.setText("Năm XB");

        btn_Sua_Sach.setText("Sửa");
        btn_Sua_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua_SachActionPerformed(evt);
            }
        });

        txt_namXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namXuatBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Sua1Layout = new javax.swing.GroupLayout(jPanel_Sua1);
        jPanel_Sua1.setLayout(jPanel_Sua1Layout);
        jPanel_Sua1Layout.setHorizontalGroup(
            jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbb_maTacGia, 0, 256, Short.MAX_VALUE)
                            .addComponent(txt_tenSach)
                            .addComponent(cbb_maNhaXuatBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_maSach)))
                    .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ngonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_soTrang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_giaThue, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(txt_giaBan))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_namXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_Sua_Sach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel_Sua1Layout.setVerticalGroup(
            jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_maTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_maNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_Sua1Layout.createSequentialGroup()
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_ngonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_giaThue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanel_Sua1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_namXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel_Sua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_soLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Sua_Sach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );

        btn_Reset_Sach.setText("Làm mới");
        btn_Reset_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Reset_SachActionPerformed(evt);
            }
        });

        btn_Xoa_Sach.setText("Xóa");
        btn_Xoa_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_SachActionPerformed(evt);
            }
        });

        them_txt_tenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_txt_tenSachActionPerformed(evt);
            }
        });

        them_txt_ngonNgu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_txt_ngonNguActionPerformed(evt);
            }
        });

        jLabel43.setText("Thể loại");

        jLabel44.setText("Mã NXB");

        jLabel45.setText("Mã tác giả");

        jLabel46.setText("Tên sách");

        them_txt_giaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_txt_giaBanActionPerformed(evt);
            }
        });

        jLabel2.setText("Năm XB");

        jLabel8.setText("Số trang");

        jLabel13.setText("Ngôn ngữ");

        jLabel14.setText("Số lượng");

        jLabel1.setText("Giá thuê");

        jLabel15.setText("Giá bán");

        them_cbb_maTacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        them_cbb_maTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_cbb_maTacGiaActionPerformed(evt);
            }
        });

        them_cbb_maNhaXuatBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_Them_Sach.setText("Thêm");
        btn_Them_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_SachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(them_cbb_maTacGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(them_txt_tenSach))
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(them_txt_giaBan)
                            .addComponent(them_txt_giaThue, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(them_txt_theLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(them_txt_namXuatBan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(them_txt_soLuong)
                                    .addComponent(them_txt_soTrang)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(them_txt_ngonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Them_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(them_cbb_maNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_txt_tenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_cbb_maTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_cbb_maNhaXuatBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(them_txt_soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(them_txt_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(them_txt_namXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(them_txt_ngonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(them_txt_giaThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(them_txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(them_txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(btn_Them_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        TableHienThiSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane_NhaXuatBan1.setViewportView(TableHienThiSach);

        javax.swing.GroupLayout QL_SachLayout = new javax.swing.GroupLayout(QL_Sach);
        QL_Sach.setLayout(QL_SachLayout);
        QL_SachLayout.setHorizontalGroup(
            QL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_SachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_NhaXuatBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(QL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, QL_SachLayout.createSequentialGroup()
                            .addComponent(btn_Xoa_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Reset_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_Sua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        QL_SachLayout.setVerticalGroup(
            QL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_SachLayout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(QL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QL_SachLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jPanel_Sua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(QL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Xoa_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Reset_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QL_SachLayout.createSequentialGroup()
                        .addComponent(jScrollPane_NhaXuatBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );

        Home.addTab("Sách", QL_Sach);

        jLabel20.setFont(new java.awt.Font("JetBrains Mono ExtraLight", 3, 18)); // NOI18N
        jLabel20.setText("ĐỔI MẬT KHẨU");

        jPanel3.setLayout(new java.awt.GridLayout(3, 2, 1, 0));

        jLabel25.setText("Mật khẩu cũ");
        jPanel3.add(jLabel25);
        jPanel3.add(txt_matKhauCu);

        jLabel24.setText("Mật khẩu mới");
        jPanel3.add(jLabel24);

        txt_matKhauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_matKhauMoiActionPerformed(evt);
            }
        });
        jPanel3.add(txt_matKhauMoi);

        jLabel23.setText("Nhập lại mật khẩu mới");
        jPanel3.add(jLabel23);
        jPanel3.add(txt_matKhauXacNhan);

        btn_doiMatKhau.setText("ĐỔI MẬT KHẨU");
        btn_doiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiMatKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DoiMatKhauLayout = new javax.swing.GroupLayout(DoiMatKhau);
        DoiMatKhau.setLayout(DoiMatKhauLayout);
        DoiMatKhauLayout.setHorizontalGroup(
            DoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoiMatKhauLayout.createSequentialGroup()
                .addGroup(DoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DoiMatKhauLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addGroup(DoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_doiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DoiMatKhauLayout.createSequentialGroup()
                        .addGap(419, 419, 419)
                        .addComponent(jLabel20)))
                .addContainerGap(382, Short.MAX_VALUE))
        );
        DoiMatKhauLayout.setVerticalGroup(
            DoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoiMatKhauLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(btn_doiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        Home.addTab("Đổi mật khẩu", DoiMatKhau);

        TableHienThiNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TableHienThiNhanVien);

        jPanel4.setLayout(new java.awt.GridLayout(4, 2, 1, 1));

        jLabel48.setText("Họ tên");
        jPanel4.add(jLabel48);
        jPanel4.add(them_txt_HoTen);

        jLabel50.setText("Ngày sinh");
        jPanel4.add(jLabel50);
        jPanel4.add(them_date_ngaySinh);

        jLabel47.setText("Địa chỉ");
        jPanel4.add(jLabel47);
        jPanel4.add(them_txt_DiaChi_NhanVien);

        jLabel49.setText("SĐT");
        jPanel4.add(jLabel49);
        jPanel4.add(them_txt_soDienThoai_NhanVien);

        jPanel5.setLayout(new java.awt.GridLayout(3, 2, 1, 1));

        jLabel53.setText("Email");
        jPanel5.add(jLabel53);
        jPanel5.add(them_txt_Email_NhanVien);

        jLabel52.setText("Mã quyền");
        jPanel5.add(jLabel52);

        them_cbb_MaQuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        them_cbb_MaQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_cbb_MaQuyenActionPerformed(evt);
            }
        });
        jPanel5.add(them_cbb_MaQuyen);

        jLabel51.setText("Tên đăng nhập");
        jPanel5.add(jLabel51);
        jPanel5.add(them_txt_TenDangNhap);

        cbb_maQuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_Them_NhanVien.setText("Thêm");
        btn_Them_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_NhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QL_NhanVienLayout = new javax.swing.GroupLayout(QL_NhanVien);
        QL_NhanVien.setLayout(QL_NhanVienLayout);
        QL_NhanVienLayout.setHorizontalGroup(
            QL_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_NhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(QL_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QL_NhanVienLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(QL_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Them_NhanVien))
                    .addGroup(QL_NhanVienLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(cbb_maQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        QL_NhanVienLayout.setVerticalGroup(
            QL_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_NhanVienLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(QL_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(QL_NhanVienLayout.createSequentialGroup()
                        .addGroup(QL_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(QL_NhanVienLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(btn_Them_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(cbb_maQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        Home.addTab("Tài khoản", QL_NhanVien);

        TableHienThiCongViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TableHienThiCongViec);

        jLabel26.setText("Tên Công việc");

        btn_Them_CongViec.setText("Thêm");
        btn_Them_CongViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_CongViecActionPerformed(evt);
            }
        });

        btn_Sua_CongViec.setText("Sửa");
        btn_Sua_CongViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua_CongViecActionPerformed(evt);
            }
        });

        btn_Xoa_CongViec.setText("Xóa");
        btn_Xoa_CongViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_CongViecActionPerformed(evt);
            }
        });

        txt_maQuyen.setEditable(false);

        jLabel27.setText("Mã Quyền");

        jLabel28.setText("Tên Công việc");

        javax.swing.GroupLayout QL_CongViecLayout = new javax.swing.GroupLayout(QL_CongViec);
        QL_CongViec.setLayout(QL_CongViecLayout);
        QL_CongViecLayout.setHorizontalGroup(
            QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_CongViecLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addGroup(QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenCongViec)
                    .addComponent(btn_Xoa_CongViec, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(btn_Sua_CongViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(them_txt_tenCongViec)
                    .addComponent(btn_Them_CongViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(QL_CongViecLayout.createSequentialGroup()
                        .addComponent(txt_maQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(298, 298, 298))
        );
        QL_CongViecLayout.setVerticalGroup(
            QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QL_CongViecLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QL_CongViecLayout.createSequentialGroup()
                        .addGroup(QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(them_txt_tenCongViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Them_CongViec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_maQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(QL_CongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tenCongViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Sua_CongViec, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Xoa_CongViec, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(288, Short.MAX_VALUE))
        );

        Home.addTab("Công việc", QL_CongViec);

        getContentPane().add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, -36, 1120, 840));

        ChucNang.setPreferredSize(new java.awt.Dimension(141, 101));
        ChucNang.setLayout(new java.awt.GridLayout(0, 1));

        btn_QuanLyTacGia.setText("Quản lý tác giả");
        btn_QuanLyTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_QuanLyTacGiaMouseClicked(evt);
            }
        });
        btn_QuanLyTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuanLyTacGiaActionPerformed(evt);
            }
        });
        ChucNang.add(btn_QuanLyTacGia);

        btnQuanLyNhaXuatBan.setText("Quản lý nhà xuất bản");
        btnQuanLyNhaXuatBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuanLyNhaXuatBanMouseClicked(evt);
            }
        });
        btnQuanLyNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyNhaXuatBanActionPerformed(evt);
            }
        });
        ChucNang.add(btnQuanLyNhaXuatBan);

        btnQuanLySach.setText("Quản lý sách");
        btnQuanLySach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuanLySachMouseClicked(evt);
            }
        });
        btnQuanLySach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLySachActionPerformed(evt);
            }
        });
        ChucNang.add(btnQuanLySach);

        btnQuanLyMuon.setText("Quản lý mượn");
        btnQuanLyMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyMuonActionPerformed(evt);
            }
        });
        ChucNang.add(btnQuanLyMuon);

        btnQuanLyDatSach.setText("Danh sách đặt sách");
        ChucNang.add(btnQuanLyDatSach);

        btnDanhSachPhat.setText("Danh sách phạt");
        ChucNang.add(btnDanhSachPhat);

        btnQuanLyNhanVien.setText("Quản lý Nhân Viên");
        btnQuanLyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyNhanVienActionPerformed(evt);
            }
        });
        ChucNang.add(btnQuanLyNhanVien);

        btnCongViec.setText("Chức vụ");
        btnCongViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCongViecActionPerformed(evt);
            }
        });
        ChucNang.add(btnCongViec);

        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        ChucNang.add(btnDoiMatKhau);

        getContentPane().add(ChucNang, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 91, 180, 410));

        HienThiTaiKhoan.setEditable(false);
        getContentPane().add(HienThiTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 180, 40));

        lbl_dangXuat.setText("Đăng xuất");
        lbl_dangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dangXuatMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_dangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_QuanLyTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_QuanLyTacGiaMouseClicked
            // TODO add your handling code here:
    }//GEN-LAST:event_btn_QuanLyTacGiaMouseClicked

    private void btnQuanLySachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuanLySachMouseClicked
                  
    }//GEN-LAST:event_btnQuanLySachMouseClicked

    private void btnQuanLyNhaXuatBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuanLyNhaXuatBanMouseClicked
        int index = Home.indexOfComponent(QL_NhaXuatBan);
            Home.setSelectedIndex(index);// 
    }//GEN-LAST:event_btnQuanLyNhaXuatBanMouseClicked

    private void btn_QuanLyTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuanLyTacGiaActionPerformed
         int index = Home.indexOfComponent(QL_TacGia);
            Home.setSelectedIndex(index);//    
    }//GEN-LAST:event_btn_QuanLyTacGiaActionPerformed

    
    
    
                                  
    private void btn_Them_TacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_TacGiaActionPerformed
                                             
                    String tenTacGia = them_txt_tenTacGia.getText().trim();
                    String namSinhStr = them_txt_namSinh.getText().trim();
                    String namMatStr = them_txt_namMat.getText().trim();
                    String quocTich = them_txt_quocTich.getText().trim();

                    if (tenTacGia.isEmpty() || namSinhStr.isEmpty() || namMatStr.isEmpty() || quocTich.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int namSinh;
                    int namMat;

                    try {
                        namSinh = Integer.parseInt(namSinhStr);
                        namMat = Integer.parseInt(namMatStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Năm sinh và năm mất phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (namMat < namSinh) {
                        JOptionPane.showMessageDialog(this, "Năm mất phải lớn hơn năm sinh.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    ACT_TacGia actTacGia = new ACT_TacGia();
                    actTacGia.themTacGia(tenTacGia, namSinh, namMat, quocTich, TableHienThiTacGia, them_txt_tenTacGia, them_txt_namSinh, them_txt_namMat, them_txt_quocTich);
                    loadComboBox_them_cbb_maTacGia(); 

                    // Xóa các trường sau khi thêm tác giả
                    them_txt_tenTacGia.setText("");
                    them_txt_namSinh.setText("");
                    them_txt_namMat.setText("");
                    them_txt_quocTich.setText("");


    }//GEN-LAST:event_btn_Them_TacGiaActionPerformed

    private void txt_maTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maTacGiaActionPerformed

    private void btn_Xoa_TacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_TacGiaActionPerformed
           ACT.ACT_TacGia actTacGia = new ACT.ACT_TacGia();
           actTacGia.xoaTacGia(TableHienThiTacGia, this);
           loadComboBox_them_cbb_maTacGia();
    }//GEN-LAST:event_btn_Xoa_TacGiaActionPerformed

    private void btn_Reset_TacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Reset_TacGiaActionPerformed
                    txt_maTacGia.setText(""); 
                    txt_tenTacGia.setText(""); 
                    txt_namSinh.setText(""); 
                    txt_namMat.setText(""); 
                    txt_quocTich.setText(""); 

                    them_txt_tenTacGia.setText(""); 
                    them_txt_namSinh.setText(""); 
                    them_txt_namMat.setText(""); 
                    them_txt_quocTich.setText(""); 

                    loadbangtacgia();
    }//GEN-LAST:event_btn_Reset_TacGiaActionPerformed

    private void btn_Sua_TacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua_TacGiaActionPerformed
                    // Lấy dữ liệu từ các JTextField
                     int maTacGia = Integer.parseInt(txt_maTacGia.getText());
                     String tenTacGia = txt_tenTacGia.getText();
                     int namSinh = Integer.parseInt(txt_namSinh.getText());
                     int namMat = Integer.parseInt(txt_namMat.getText());
                     String quocTich = txt_quocTich.getText();

                     ACT.ACT_TacGia actTacGia = new ACT.ACT_TacGia();
                     actTacGia.suaTacGia(maTacGia, tenTacGia, namSinh, namMat, quocTich, TableHienThiTacGia, this);
                     loadComboBox_them_cbb_maTacGia();
 
    }//GEN-LAST:event_btn_Sua_TacGiaActionPerformed

    private void btn_Sua_NhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua_NhaXuatBanActionPerformed
                    // Lấy dữ liệu từ các JTextField
                        int maNhaXuatBan = Integer.parseInt(txt_maNhaXuatBan.getText());
                        String tenNhaXuatBan = txt_tenNhaXuatBan.getText();
                        String diaChi = txt_diaChi.getText();
                        String soDienThoai = txt_soDienThoai.getText();
                        String email = txt_email.getText();

                        ACT.ACT_NhaXuatBan actnhaXuatBan = new ACT.ACT_NhaXuatBan();
                        actnhaXuatBan.suaNhaXuatBan(maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai, email, TableHienThiNhaXuatBan, this);
                        loadComboBox_them_cbb_maNhaXuatBan();
    }//GEN-LAST:event_btn_Sua_NhaXuatBanActionPerformed
//               
    private void btn_Reset_NhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Reset_NhaXuatBanActionPerformed
                    txt_maNhaXuatBan.setText(""); 
                    txt_tenNhaXuatBan.setText(""); 
                    txt_diaChi.setText(""); 
                    txt_soDienThoai.setText(""); 
                    txt_email.setText(""); 

                    them_txt_tenNhaXuatBan.setText(""); 
                    them_txt_diaChi.setText(""); 
                    them_txt_soDienThoai.setText(""); 
                    them_txt_email.setText(""); 

                    loadbangnhaxuatban();
    }//GEN-LAST:event_btn_Reset_NhaXuatBanActionPerformed

    private void btn_Them_NhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_NhaXuatBanActionPerformed
                    String tenNhaXuatBan = them_txt_tenNhaXuatBan.getText().trim();
                    String diaChi = them_txt_diaChi.getText().trim();
                    String soDienThoai = them_txt_soDienThoai.getText().trim();
                    String email = them_txt_email.getText().trim();

                    if (tenNhaXuatBan.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty() || email.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Thực hiện kiểm tra thêm nếu cần, ví dụ: định dạng email, độ dài số điện thoại, v.v.
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    ACT_NhaXuatBan actNhaXuatBan = new ACT_NhaXuatBan();
                    actNhaXuatBan.themNhaXuatBan(tenNhaXuatBan, diaChi, soDienThoai, email, TableHienThiNhaXuatBan, them_txt_tenNhaXuatBan, them_txt_diaChi, them_txt_soDienThoai, them_txt_email);
                    loadbangnhaxuatban();
                    loadComboBox_them_cbb_maNhaXuatBan();

                    // Xóa các trường sau khi thêm nhà xuất bản
                    them_txt_tenNhaXuatBan.setText("");
                    them_txt_diaChi.setText("");
                    them_txt_soDienThoai.setText("");
                    them_txt_email.setText(""); 
    }//GEN-LAST:event_btn_Them_NhaXuatBanActionPerformed

    private void btn_Xoa_NhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_NhaXuatBanActionPerformed
        ACT.ACT_NhaXuatBan actNhaXuatBan = new ACT.ACT_NhaXuatBan();
           actNhaXuatBan.xoaNhaXuatBan(TableHienThiNhaXuatBan, this);
           loadComboBox_them_cbb_maNhaXuatBan();
    }//GEN-LAST:event_btn_Xoa_NhaXuatBanActionPerformed

    private void txt_maNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maNhaXuatBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maNhaXuatBanActionPerformed

    private void btnQuanLyNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyNhaXuatBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuanLyNhaXuatBanActionPerformed

    private void txt_soDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_soDienThoaiActionPerformed

    private void btnQuanLySachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLySachActionPerformed
        int index = Home.indexOfComponent(QL_Sach);
            Home.setSelectedIndex(index);//
    }//GEN-LAST:event_btnQuanLySachActionPerformed

    private void btn_Reset_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Reset_SachActionPerformed
                // Trả về rỗng
                    them_txt_tenSach.setText(""); 
                    them_cbb_maTacGia.setSelectedIndex(-1); 
                    them_cbb_maNhaXuatBan.setSelectedIndex(-1);
                    cbb_maTacGia.setSelectedIndex(-1); 
                    cbb_maNhaXuatBan.setSelectedIndex(-1);
                    them_txt_theLoai.setText(""); 
                    them_txt_namXuatBan.setText(""); 
                    them_txt_soTrang.setText(""); 
                    them_txt_ngonNgu.setText(""); 
                    them_txt_soLuong.setText(""); 
                    them_txt_giaThue.setText(""); 
                    them_txt_giaBan.setText("");
                    
                    txt_maSach.setText("");
                    txt_tenSach.setText("");
                    txt_theLoai.setText(""); 
                    txt_namXuatBan.setText(""); 
                    txt_soTrang.setText(""); 
                    txt_ngonNgu.setText(""); 
                    txt_soLuong.setText(""); 
                    txt_giaThue.setText(""); 
                    txt_giaBan.setText("");
                    
    }//GEN-LAST:event_btn_Reset_SachActionPerformed

    private void btn_Xoa_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_SachActionPerformed
                    ACT.ACT_Sach actSach = new ACT.ACT_Sach();
                   actSach.xoaSach(TableHienThiSach, this);
                   
    }//GEN-LAST:event_btn_Xoa_SachActionPerformed

    private void txt_theLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_theLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_theLoaiActionPerformed

    private void txt_giaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giaBanActionPerformed
                private int getMaTacGiaFromComboBox(String item) {
                if (item != null && !item.isEmpty()) {
                    String[] parts = item.split(" - ");
                    if (parts.length > 0) {
                        try {
                            return Integer.parseInt(parts[0]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return -1; // Giá trị mặc định nếu không thể tách hoặc chuyển đổi
                }

                private int getMaNhaXuatBanFromComboBox(String item) {
                if (item != null && !item.isEmpty()) {
                    String[] parts = item.split(" - ");
                    if (parts.length > 0) {
                        try {
                            return Integer.parseInt(parts[0]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return -1; // Giá trị mặc định nếu không thể tách hoặc chuyển đổi
                }

    private void btn_Them_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_SachActionPerformed
                        String tenSach = them_txt_tenSach.getText().trim();
                        String selectedTacGiaItem = (String) them_cbb_maTacGia.getSelectedItem();
                        int maTacGia = getMaTacGiaFromComboBox(selectedTacGiaItem);
                        String selectedNhaXuatBanItem = (String) them_cbb_maNhaXuatBan.getSelectedItem();
                        int maNhaXuatBan = getMaNhaXuatBanFromComboBox(selectedNhaXuatBanItem);
                        String theLoai = them_txt_theLoai.getText().trim();
                        String namXuatBanStr = them_txt_namXuatBan.getText().trim();
                        String soTrangStr = them_txt_soTrang.getText().trim();
                        String ngonNgu = them_txt_ngonNgu.getText().trim();
                        String soLuongStr = them_txt_soLuong.getText().trim();
                        String giaThueStr = them_txt_giaThue.getText().trim();
                        String giaBanStr = them_txt_giaBan.getText().trim();

                        if (tenSach.isEmpty() || theLoai.isEmpty() || namXuatBanStr.isEmpty() || soTrangStr.isEmpty() || ngonNgu.isEmpty() || soLuongStr.isEmpty() || giaThueStr.isEmpty() || giaBanStr.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int namXuatBan;
                        int soTrang;
                        int soLuong;
                        float giaThue;
                        float giaBan;

                        try {
                            namXuatBan = Integer.parseInt(namXuatBanStr);
                            soTrang = Integer.parseInt(soTrangStr);
                            soLuong = Integer.parseInt(soLuongStr);
                            giaThue = Float.parseFloat(giaThueStr);
                            giaBan = Float.parseFloat(giaBanStr);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Năm xuất bản, số trang, số lượng, giá thuê và giá bán phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Kiểm tra và xử lý các trường hợp cần thiết trước khi thêm sách
                        if (maTacGia != -1 && maNhaXuatBan != -1 && !tenSach.isEmpty() && !theLoai.isEmpty() && !ngonNgu.isEmpty()) {
                            ACT_Sach actSach = new ACT_Sach();
                            actSach.themSach(tenSach, maTacGia, maNhaXuatBan, theLoai, namXuatBan, soTrang, ngonNgu, soLuong, giaThue, giaBan, TableHienThiTacGia, them_txt_tenSach, them_cbb_maTacGia, them_cbb_maNhaXuatBan, them_txt_theLoai, them_txt_namXuatBan, them_txt_soTrang, them_txt_ngonNgu, them_txt_soLuong, them_txt_giaThue, them_txt_giaBan);
                            loadbangsach();
                            them_cbb_maTacGia.setSelectedIndex(-1); 
                            them_cbb_maNhaXuatBan.setSelectedIndex(-1);

                        } else {
                            // Xử lý trường hợp lỗi, ví dụ hiển thị thông báo cho người dùng
                            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin và chọn mã tác giả và mã nhà xuất bản hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
            
    }//GEN-LAST:event_btn_Them_SachActionPerformed

    private void them_cbb_maTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_cbb_maTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_them_cbb_maTacGiaActionPerformed

    private void them_txt_giaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_txt_giaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_them_txt_giaBanActionPerformed

    private void them_txt_ngonNguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_txt_ngonNguActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_them_txt_ngonNguActionPerformed

    private void them_txt_tenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_txt_tenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_them_txt_tenSachActionPerformed

    private void cbb_maTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_maTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_maTacGiaActionPerformed

    private void cbb_maNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_maNhaXuatBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_maNhaXuatBanActionPerformed

    private void txt_soLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_soLuongActionPerformed

    private void txt_soTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_soTrangActionPerformed

    private void txt_ngonNguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngonNguActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngonNguActionPerformed

    private void txt_giaThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giaThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giaThueActionPerformed

    private void btn_Sua_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua_SachActionPerformed
                        try {
                        // Lấy dữ liệu từ các JTextField và JComboBox
                        int maSach = Integer.parseInt(txt_maSach.getText());
                        String tenSach = txt_tenSach.getText();
                        String selectedTacGiaItem = (String) cbb_maTacGia.getSelectedItem();
                        int maTacGia = getMaTacGiaFromComboBox(selectedTacGiaItem);
                        String selectedNhaXuatBanItem = (String) cbb_maNhaXuatBan.getSelectedItem();
                        int maNhaXuatBan = getMaNhaXuatBanFromComboBox(selectedNhaXuatBanItem);
                        String theLoai = txt_theLoai.getText();
                        int namXuatBan = Integer.parseInt(txt_namXuatBan.getText());
                        int soTrang = Integer.parseInt(txt_soTrang.getText());
                        String ngonNgu = txt_ngonNgu.getText();
                        int soLuong = Integer.parseInt(txt_soLuong.getText());
                        float giaThue = Float.parseFloat(txt_giaThue.getText());
                        float giaBan = Float.parseFloat(txt_giaBan.getText());

                        // Gọi phương thức để sửa thông tin sách
                        ACT.ACT_Sach actSach = new ACT.ACT_Sach();
                        actSach.suaSach(maSach, tenSach, maTacGia, maNhaXuatBan, theLoai, namXuatBan, soTrang, ngonNgu, soLuong, giaThue, giaBan, TableHienThiSach, this);

                        // Cập nhật lại ComboBox cho Mã Tác giả và Mã Nhà Xuất bản
                        loadComboBox_them_cbb_maTacGia();
                        loadComboBox_them_cbb_maNhaXuatBan();
                        cbb_maTacGia.setSelectedIndex(-1); 
                         cbb_maNhaXuatBan.setSelectedIndex(-1);

                        // Cập nhật lại bảng sách
                        loadbangsach();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho các trường số liệu.", "Lỗi Nhập liệu", JOptionPane.ERROR_MESSAGE);
                        // Xử lý khi nhập liệu không hợp lệ, ví dụ như reset lại các JTextField hoặc thông báo cho người dùng.
                    }

    }//GEN-LAST:event_btn_Sua_SachActionPerformed

    

    
    private void txt_namXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namXuatBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namXuatBanActionPerformed

    private void btnQuanLyMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuanLyMuonActionPerformed

    private void btnQuanLyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyNhanVienActionPerformed
        int index = Home.indexOfComponent(QL_NhanVien);
            Home.setSelectedIndex(index);// 
    }//GEN-LAST:event_btnQuanLyNhanVienActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        int index = Home.indexOfComponent(DoiMatKhau);
            Home.setSelectedIndex(index);// 
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btn_doiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiMatKhauActionPerformed
        String matKhauCu = txt_matKhauCu.getText();
    String matKhauMoi = txt_matKhauMoi.getText();
    String matKhauXacNhan = txt_matKhauXacNhan.getText();

    if (!matKhauMoi.equals(matKhauXacNhan)) {
        JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận mật khẩu không khớp");
        return;
    }

    // Kiểm tra kết nối và thực thi đổi mật khẩu
    DAO_KetNoi dao = new DAO_KetNoi();
    try {
            java.sql.Connection conn = dao.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM NHANVIEN WHERE TenDangNhap = ? AND MatKhau = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, TenTaiKhoan); // loggedInUsername được lưu từ khi đăng nhập
            pst.setString(2, matKhauCu);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Đổi mật khẩu trong cơ sở dữ liệu
                sql = "UPDATE NHANVIEN SET MatKhau = ? WHERE TenDangNhap = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, matKhauMoi);
                pst.setString(2, TenTaiKhoan);
                int rowsAffected = pst.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thành công. Vui lòng thử lại sau");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
    } finally {
        dao.close(); // Đóng kết nối sau khi sử dụng
    }
    }//GEN-LAST:event_btn_doiMatKhauActionPerformed

    private void txt_matKhauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_matKhauMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_matKhauMoiActionPerformed

    private void lbl_dangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dangXuatMouseClicked
        GUI_Login lg = new GUI_Login();
        lg.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_lbl_dangXuatMouseClicked

    private void btnCongViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCongViecActionPerformed
        int index = Home.indexOfComponent(QL_CongViec);
            Home.setSelectedIndex(index);// 
    }//GEN-LAST:event_btnCongViecActionPerformed

    private void btn_Them_CongViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_CongViecActionPerformed
                    String tenCongViec = them_txt_tenCongViec.getText().trim();

                    if (tenCongViec.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    ACT_CongViec actCongViec = new ACT_CongViec();
                    actCongViec.themCongViec(tenCongViec, TableHienThiCongViec, them_txt_tenCongViec);
                     

                    // Xóa các trường sau khi thêm công việc
                    them_txt_tenCongViec.setText("");
                    
    }//GEN-LAST:event_btn_Them_CongViecActionPerformed

    private void btn_Sua_CongViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua_CongViecActionPerformed
        // Lấy dữ liệu từ các JTextField
        
                     int maQuyen = Integer.parseInt(txt_maQuyen.getText());
                     String tenCongViec = txt_tenCongViec.getText();

                     ACT.ACT_CongViec actCongViec = new ACT.ACT_CongViec();
                     actCongViec.suaCongViec(maQuyen,tenCongViec, TableHienThiCongViec, this);
                     
                     txt_tenCongViec.setText("");
                     txt_maQuyen.setText("");
    }//GEN-LAST:event_btn_Sua_CongViecActionPerformed

    private void btn_Xoa_CongViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_CongViecActionPerformed
            ACT.ACT_CongViec actCongViec = new ACT.ACT_CongViec();
           actCongViec.xoaCongViec(TableHienThiCongViec, this);
    }//GEN-LAST:event_btn_Xoa_CongViecActionPerformed

    private void them_cbb_MaQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_cbb_MaQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_them_cbb_MaQuyenActionPerformed

    private int getMaQuyenFromComboBox(String item) {
                if (item != null && !item.isEmpty()) {
                    String[] parts = item.split(" - ");
                    if (parts.length > 0) {
                        try {
                            return Integer.parseInt(parts[0]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return -1; // Giá trị mặc định nếu không thể tách hoặc chuyển đổi
                }
    private void btn_Them_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_NhanVienActionPerformed
                        String hoTenStr = them_txt_HoTen.getText().trim();
                        String diaChiStr = them_txt_DiaChi_NhanVien.getText().trim();
                        String soDienThoaiStr = them_txt_soDienThoai_NhanVien.getText().trim();
                        Date ngaySinhDate = them_date_ngaySinh.getDate(); // Lấy ngày sinh dưới dạng java.util.Date
                        String emailStr = them_txt_Email_NhanVien.getText().trim();
                        String selectedCongViecItem = (String) them_cbb_MaQuyen.getSelectedItem();
                        int maQuyenStr = getMaQuyenFromComboBox(selectedCongViecItem);
                        String tenDangNhapStr = them_txt_TenDangNhap.getText().trim();

                        if (hoTenStr.isEmpty() || diaChiStr.isEmpty() || soDienThoaiStr.isEmpty() || emailStr.isEmpty() || tenDangNhapStr.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Chuyển đổi ngày sinh sang định dạng "yyyy/MM/dd" để thêm vào CSDL
                        String ngaySinhStr = null;
                        if (ngaySinhDate != null) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                            ngaySinhStr = dateFormat.format(ngaySinhDate);
                        }

                        // Kiểm tra và xử lý các trường hợp cần thiết trước khi thêm nhân viên
                        if (maQuyenStr != -1 && !hoTenStr.isEmpty() && ngaySinhStr != null && !emailStr.isEmpty() && !tenDangNhapStr.isEmpty()) {
                            ACT_NhanVien actNhanVien = new ACT_NhanVien();
                            actNhanVien.themNhanVien(hoTenStr, ngaySinhStr, diaChiStr, soDienThoaiStr, emailStr, maQuyenStr, tenDangNhapStr, TableHienThiNhanVien, them_txt_HoTen, them_date_ngaySinh, them_txt_DiaChi_NhanVien, them_txt_soDienThoai_NhanVien, them_txt_Email_NhanVien, them_cbb_MaQuyen, them_txt_ngonNgu, them_txt_TenDangNhap);
                            loadbangnhanvien();

                            // Đặt lại các giá trị về rỗng hoặc giá trị mặc định sau khi thêm thành công
                            them_txt_HoTen.setText(""); 
                            them_date_ngaySinh.setDate(null); 
                            them_txt_DiaChi_NhanVien.setText(""); 
                            them_txt_soDienThoai_NhanVien.setText(""); 
                            them_txt_Email_NhanVien.setText(""); 
                            them_txt_TenDangNhap.setText("");
                            them_cbb_MaQuyen.setSelectedIndex(-1); 
                        } else {
                            // Xử lý trường hợp lỗi, ví dụ hiển thị thông báo cho người dùng
                            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin và chọn mã quyền hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
    }//GEN-LAST:event_btn_Them_NhanVienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(GUI_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        // Xử lý ngoại lệ ở đây: ghi log, thông báo lỗi, hoặc thực hiện một hành động phù hợp khác.
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            String TenTaiKhoan = "your_username"; // Thay bằng giá trị thực tế
            GUI_Home home = new GUI_Home(TenTaiKhoan);
            home.setVisible(true);

            // Cập nhật Label HienThiTaiKhoan với tên tài khoản
            home.getHienThiTaiKhoanLabel().setText("Đăng nhập với tài khoản: " + TenTaiKhoan);
        }
    });
}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChucNang;
    private javax.swing.JPanel DoiMatKhau;
    private javax.swing.JTextField HienThiTaiKhoan;
    private javax.swing.JTabbedPane Home;
    private javax.swing.JPanel QL_CongViec;
    private javax.swing.JPanel QL_NhaXuatBan;
    private javax.swing.JPanel QL_NhanVien;
    private javax.swing.JPanel QL_Sach;
    private javax.swing.JPanel QL_TacGia;
    private javax.swing.JTable TableHienThiCongViec;
    private javax.swing.JTable TableHienThiNhaXuatBan;
    private javax.swing.JTable TableHienThiNhanVien;
    private javax.swing.JTable TableHienThiSach;
    private javax.swing.JTable TableHienThiTacGia;
    private javax.swing.JButton btnCongViec;
    private javax.swing.JButton btnDanhSachPhat;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnQuanLyDatSach;
    private javax.swing.JButton btnQuanLyMuon;
    private javax.swing.JButton btnQuanLyNhaXuatBan;
    private javax.swing.JButton btnQuanLyNhanVien;
    private javax.swing.JButton btnQuanLySach;
    private javax.swing.JButton btn_QuanLyTacGia;
    private javax.swing.JButton btn_Reset_NhaXuatBan;
    private javax.swing.JButton btn_Reset_Sach;
    private javax.swing.JButton btn_Reset_TacGia;
    private javax.swing.JButton btn_Sua_CongViec;
    private javax.swing.JButton btn_Sua_NhaXuatBan;
    private javax.swing.JButton btn_Sua_Sach;
    private javax.swing.JButton btn_Sua_TacGia;
    private javax.swing.JButton btn_Them_CongViec;
    private javax.swing.JButton btn_Them_NhaXuatBan;
    private javax.swing.JButton btn_Them_NhanVien;
    private javax.swing.JButton btn_Them_Sach;
    private javax.swing.JButton btn_Them_TacGia;
    private javax.swing.JButton btn_Xoa_CongViec;
    private javax.swing.JButton btn_Xoa_NhaXuatBan;
    private javax.swing.JButton btn_Xoa_Sach;
    private javax.swing.JButton btn_Xoa_TacGia;
    private javax.swing.JButton btn_doiMatKhau;
    private javax.swing.JComboBox<String> cbb_maNhaXuatBan;
    private javax.swing.JComboBox<String> cbb_maQuyen;
    private javax.swing.JComboBox<String> cbb_maTacGia;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Sua;
    private javax.swing.JPanel jPanel_Sua1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_NhaXuatBan;
    private javax.swing.JScrollPane jScrollPane_NhaXuatBan1;
    private javax.swing.JScrollPane jScrollPane_TacGia;
    private javax.swing.JLabel lbl_dangXuat;
    private javax.swing.JComboBox<String> them_cbb_MaQuyen;
    private javax.swing.JComboBox<String> them_cbb_maNhaXuatBan;
    private javax.swing.JComboBox<String> them_cbb_maTacGia;
    private com.toedter.calendar.JDateChooser them_date_ngaySinh;
    private javax.swing.JTextField them_txt_DiaChi_NhanVien;
    private javax.swing.JTextField them_txt_Email_NhanVien;
    private javax.swing.JTextField them_txt_HoTen;
    private javax.swing.JTextField them_txt_TenDangNhap;
    private javax.swing.JTextField them_txt_diaChi;
    private javax.swing.JTextField them_txt_email;
    private javax.swing.JTextField them_txt_giaBan;
    private javax.swing.JTextField them_txt_giaThue;
    private javax.swing.JTextField them_txt_namMat;
    private javax.swing.JTextField them_txt_namSinh;
    private javax.swing.JTextField them_txt_namXuatBan;
    private javax.swing.JTextField them_txt_ngonNgu;
    private javax.swing.JTextField them_txt_quocTich;
    private javax.swing.JTextField them_txt_soDienThoai;
    private javax.swing.JTextField them_txt_soDienThoai_NhanVien;
    private javax.swing.JTextField them_txt_soLuong;
    private javax.swing.JTextField them_txt_soTrang;
    private javax.swing.JTextField them_txt_tenCongViec;
    private javax.swing.JTextField them_txt_tenNhaXuatBan;
    private javax.swing.JTextField them_txt_tenSach;
    private javax.swing.JTextField them_txt_tenTacGia;
    private javax.swing.JTextField them_txt_theLoai;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_giaThue;
    private javax.swing.JTextField txt_maNhaXuatBan;
    private javax.swing.JTextField txt_maQuyen;
    private javax.swing.JTextField txt_maSach;
    private javax.swing.JTextField txt_maTacGia;
    private javax.swing.JTextField txt_matKhauCu;
    private javax.swing.JTextField txt_matKhauMoi;
    private javax.swing.JTextField txt_matKhauXacNhan;
    private javax.swing.JTextField txt_namMat;
    private javax.swing.JTextField txt_namSinh;
    private javax.swing.JTextField txt_namXuatBan;
    private javax.swing.JTextField txt_ngonNgu;
    private javax.swing.JTextField txt_quocTich;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_soTrang;
    private javax.swing.JTextField txt_tenCongViec;
    private javax.swing.JTextField txt_tenNhaXuatBan;
    private javax.swing.JTextField txt_tenSach;
    private javax.swing.JTextField txt_tenTacGia;
    private javax.swing.JTextField txt_theLoai;
    // End of variables declaration//GEN-END:variables
}
