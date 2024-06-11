/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vhung
 */
public class NhaXuatBan {
    private int maNhaXuatBan;
    private String tenNhaXuatBan;
    private String diaChi;
    private String soDienThoai;
    private String email;

    @Override
    public String toString() {
        return "NhaXuatBan{" + "maNhaXuatBan=" + maNhaXuatBan + ", tenNhaXuatBan=" + tenNhaXuatBan + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", email=" + email + '}';
    }

    public NhaXuatBan() {
    }
    
    // Constructor
    public NhaXuatBan(int maNhaXuatBan, String tenNhaXuatBan, String diaChi, String soDienThoai, String email) {
        this.maNhaXuatBan = maNhaXuatBan;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    // Getter và setter cho các thuộc tính

    public int getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(int maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
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
    
    
}

