/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vhung
 */
public class Model_Sach {
    private int MaSach;
    private String tenSach;
    private int maTacGia;
    private int maNhaXuatBan;
    private String TheLoai;
    private int namXuatBan;
    private int soTrang;
    private String ngonNgu;
    private int soLuong;
    private float giaThue;
    private float giaBan;

    public Model_Sach() {
    }

    public Model_Sach(int MaSach, String tenSach, int maTacGia, int maNhaSanXuat, String TheLoai, int namXuatBan, int soTrang, String ngonNgu, int soLuong, float giaThue, float giaBan) {
        this.MaSach = MaSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.maNhaXuatBan = maNhaSanXuat;
        this.TheLoai = TheLoai;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.ngonNgu = ngonNgu;
        this.soLuong = soLuong;
        this.giaThue = giaThue;
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "Model_Sach{" + "MaSach=" + MaSach + ", tenSach=" + tenSach + ", maTacGia=" + maTacGia + ", maNhaSanXuat=" + maNhaXuatBan + ", TheLoai=" + TheLoai + ", namXuatBan=" + namXuatBan + ", soTrang=" + soTrang + ", ngonNgu=" + ngonNgu + ", soLuong=" + soLuong + ", giaThue=" + giaThue + ", giaBan=" + giaBan + '}';
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public int getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(int maNhaSanXuat) {
        this.maNhaXuatBan = maNhaSanXuat;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(float giaThue) {
        this.giaThue = giaThue;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }
    
    
        
    public String getString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
