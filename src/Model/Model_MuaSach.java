/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vhung
 */
public class Model_MuaSach {
    private int maMuaSach;
    private int maThanhVien;
    private int maSach;
    private int soLuong;
    private String tenKhachHang;
    private String soDienThoai;
    private float donGia;

    public Model_MuaSach() {
    }

    public Model_MuaSach(int maMuaSach, int maThanhVien, int maSach, int soLuong, String tenKhachHang, String soDienThoai, float donGia) {
        this.maMuaSach = maMuaSach;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "Model_MuaSach{" + "maMuaSach=" + maMuaSach + ", maThanhVien=" + maThanhVien + ", maSach=" + maSach + ", soLuong=" + soLuong + ", tenKhachHang=" + tenKhachHang + ", soDienThoai=" + soDienThoai + ", donGia=" + donGia + '}';
    }

    public int getMaMuaSach() {
        return maMuaSach;
    }

    public void setMaMuaSach(int maMuaSach) {
        this.maMuaSach = maMuaSach;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
    
    
    
    public String getString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
