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
public class Model_Phat {
    private int maPhat;
    private int maThanhVien;
    private float soTienPhat;
    private String lyDoPhat;
    private Date ngayPhat;

    public Model_Phat() {
    }

    public Model_Phat(int maPhat, int maThanhVien, float soTienPhat, String lyDoPhat, Date ngayPhat) {
        this.maPhat = maPhat;
        this.maThanhVien = maThanhVien;
        this.soTienPhat = soTienPhat;
        this.lyDoPhat = lyDoPhat;
        this.ngayPhat = ngayPhat;
    }

    @Override
    public String toString() {
        return "Model_Phat{" + "maPhat=" + maPhat + ", maThanhVien=" + maThanhVien + ", soTienPhat=" + soTienPhat + ", lyDoPhat=" + lyDoPhat + ", ngayPhat=" + ngayPhat + '}';
    }

    public int getMaPhat() {
        return maPhat;
    }

    public void setMaPhat(int maPhat) {
        this.maPhat = maPhat;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public float getSoTienPhat() {
        return soTienPhat;
    }

    public void setSoTienPhat(float soTienPhat) {
        this.soTienPhat = soTienPhat;
    }

    public String getLyDoPhat() {
        return lyDoPhat;
    }

    public void setLyDoPhat(String lyDoPhat) {
        this.lyDoPhat = lyDoPhat;
    }

    public Date getNgayPhat() {
        return ngayPhat;
    }

    public void setNgayPhat(Date ngayPhat) {
        this.ngayPhat = ngayPhat;
    }
    
    
        
    public String getString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
