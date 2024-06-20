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
    private int maSach;
    private String tieuDe;
    private int maTacGia;
    private int maNhaXuatBan;
    private String theLoai;
    private int namXuatBan;
    private int soTrang;
    private String ngonNgu;
    private int soLuong;

    public Model_Sach() {
    }

    public Model_Sach(int maSach, String tieuDe, int maTacGia, int maNhaXuatBan, String theLoai, int namXuatBan, int soTrang, String ngonNgu, int soLuong) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.maTacGia = maTacGia;
        this.maNhaXuatBan = maNhaXuatBan;
        this.theLoai = theLoai;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.ngonNgu = ngonNgu;
        this.soLuong = soLuong;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
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

    public void setMaNhaXuatBan(int maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
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

    @Override
    public String toString() {
        return "Model_Sach{" + "maSach=" + maSach + ", tieuDe=" + tieuDe + ", maTacGia=" + maTacGia + ", maNhaXuatBan=" + maNhaXuatBan + ", theLoai=" + theLoai + ", namXuatBan=" + namXuatBan + ", soTrang=" + soTrang + ", ngonNgu=" + ngonNgu + ", soLuong=" + soLuong + '}';
    }

    public String getString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}

