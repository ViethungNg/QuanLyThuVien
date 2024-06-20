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
public class Model_DatSach {
    private int maDatSach;
    private int maThanhVien;
    private int maSach;
    private Date ngayDatTruoc;
    private String trangThai;

    @Override
    public String toString() {
        return "DatSach{" + "maDatSach=" + maDatSach + ", maThanhVien=" + maThanhVien + ", maSach=" + maSach + ", ngayDatTruoc=" + ngayDatTruoc + ", trangThai=" + trangThai + '}';
    }
    
    public Model_DatSach() {
    }

    // Constructor
    public Model_DatSach(int maDatSach, int maThanhVien, int maSach, Date ngayDatTruoc, String trangThai) {
        this.maDatSach = maDatSach;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngayDatTruoc = ngayDatTruoc;
        this.trangThai = trangThai;
    }

    // Getter và setter cho các thuộc tính

    public int getMaDatSach() {
        return maDatSach;
    }

    public void setMaDatSach(int maDatSach) {
        this.maDatSach = maDatSach;
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

    public Date getNgayDatTruoc() {
        return ngayDatTruoc;
    }

    public void setNgayDatTruoc(Date ngayDatTruoc) {
        this.ngayDatTruoc = ngayDatTruoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
