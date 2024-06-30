/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author vhung
 */
public class Model_ThanhVien {
    private int maThanhVien;
    private String tenThanhVien;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private Date ngayDangKyThanhVien;

    public Model_ThanhVien() {
    }

    public Model_ThanhVien(int maThanhVien, String tenThanhVien, Date ngaySinh, String diaChi, String soDienThoai, String email, Date ngayDangKyThanhVien) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayDangKyThanhVien = ngayDangKyThanhVien;
    }

    @Override
    public String toString() {
        return "Model_ThanhVien{" + "maThanhVien=" + maThanhVien + ", tenThanhVien=" + tenThanhVien + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", email=" + email + ", ngayDangKyThanhVien=" + ngayDangKyThanhVien + '}';
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgayDangKyThanhVien() {
        return ngayDangKyThanhVien;
    }

    public void setNgayDangKyThanhVien(Date ngayDangKyThanhVien) {
        this.ngayDangKyThanhVien = ngayDangKyThanhVien;
    }
    
    
        
    public String getString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
