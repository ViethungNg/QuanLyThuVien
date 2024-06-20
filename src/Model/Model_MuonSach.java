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
public class Model_MuonSach {
    private int maMuonSach;
    private int maThanhVien;
    private int maSach;
    private Date ngayMuon;
    private Date ngayPhaiTra;
    private Date ngayTra;

    @Override
    public String toString() {
        return "MuonSach{" + "maMuonSach=" + maMuonSach + ", maThanhVien=" + maThanhVien + ", maSach=" + maSach + ", ngayMuon=" + ngayMuon + ", ngayPhaiTra=" + ngayPhaiTra + ", ngayTra=" + ngayTra + '}';
    }

    public Model_MuonSach() {
    }

    // Constructor
    public Model_MuonSach(int maMuonSach, int maThanhVien, int maSach, Date ngayMuon, Date ngayPhaiTra, Date ngayTra) {
        this.maMuonSach = maMuonSach;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngayMuon = ngayMuon;
        this.ngayPhaiTra = ngayPhaiTra;
        this.ngayTra = ngayTra;
    }

    // Getter và setter cho các thuộc tính

    public int getMaMuonSach() {
        return maMuonSach;
    }

    public void setMaMuonSach(int maMuonSach) {
        this.maMuonSach = maMuonSach;
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

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayPhaiTra() {
        return ngayPhaiTra;
    }

    public void setNgayPhaiTra(Date ngayPhaiTra) {
        this.ngayPhaiTra = ngayPhaiTra;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }
    
}
