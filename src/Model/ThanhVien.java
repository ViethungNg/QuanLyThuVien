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
public class ThanhVien {
    private int maThanhVien;
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private Date ngayDangKyThanhVien;

    @Override
    public String toString() {
        return "ThanhVien{" + "maThanhVien=" + maThanhVien + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", email=" + email + ", ngayDangKyThanhVien=" + ngayDangKyThanhVien + '}';
    }

    public ThanhVien() {
    }

    // Constructor
    public ThanhVien(int maThanhVien, String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String email, Date ngayDangKyThanhVien) {
        this.maThanhVien = maThanhVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayDangKyThanhVien = ngayDangKyThanhVien;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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
    
}
