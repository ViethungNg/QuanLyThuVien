
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vhung
 */
public class Model_Phat {
    private int maPhat;
    private int maThanhVien;
    private double soTienPhat;
    private String lyDoPhat;
    private Date ngayPhat;

    public Model_Phat() {
    }

    // Constructor
    public Model_Phat(int maPhat, int maThanhVien, double soTienPhat, String lyDoPhat, Date ngayPhat) {
        this.maPhat = maPhat;
        this.maThanhVien = maThanhVien;
        this.soTienPhat = soTienPhat;
        this.lyDoPhat = lyDoPhat;
        this.ngayPhat = ngayPhat;
    }

    // Getter và setter cho các thuộc tính

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

    public double getSoTienPhat() {
        return soTienPhat;
    }

    public void setSoTienPhat(double soTienPhat) {
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
    
}

