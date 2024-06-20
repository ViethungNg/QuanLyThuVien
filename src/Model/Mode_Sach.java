/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vhung
 */
public class Mode_Sach {
    private int maSach;
    private String tieuDe;
    private int maTacGia;
    private int maNhaXuatBan;
    private int maTheLoai;
    private String maSoSachChuanQuocTe;
    private int namXuatBan;
    private int soTrang;
    private String ngonNgu;
    private int soLuongBanSaoCoSan;

    @Override
    public String toString() {
        return "Sach{" + "maSach=" + maSach + ", tieuDe=" + tieuDe + ", maTacGia=" + maTacGia + ", maNhaXuatBan=" + maNhaXuatBan + ", maTheLoai=" + maTheLoai + ", maSoSachChuanQuocTe=" + maSoSachChuanQuocTe + ", namXuatBan=" + namXuatBan + ", soTrang=" + soTrang + ", ngonNgu=" + ngonNgu + ", soLuongBanSaoCoSan=" + soLuongBanSaoCoSan + '}';
    }

    public Mode_Sach() {
    }

    // Constructor
    public Mode_Sach(int maSach, String tieuDe, int maTacGia, int maNhaXuatBan, int maTheLoai, String maSoSachChuanQuocTe, int namXuatBan, int soTrang, String ngonNgu, int soLuongBanSaoCoSan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.maTacGia = maTacGia;
        this.maNhaXuatBan = maNhaXuatBan;
        this.maTheLoai = maTheLoai;
        this.maSoSachChuanQuocTe = maSoSachChuanQuocTe;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.ngonNgu = ngonNgu;
        this.soLuongBanSaoCoSan = soLuongBanSaoCoSan;
    }

    // Getter và setter cho các thuộc tính

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

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getMaSoSachChuanQuocTe() {
        return maSoSachChuanQuocTe;
    }

    public void setMaSoSachChuanQuocTe(String maSoSachChuanQuocTe) {
        this.maSoSachChuanQuocTe = maSoSachChuanQuocTe;
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

    public int getSoLuongBanSaoCoSan() {
        return soLuongBanSaoCoSan;
    }

    public void setSoLuongBanSaoCoSan(int soLuongBanSaoCoSan) {
        this.soLuongBanSaoCoSan = soLuongBanSaoCoSan;
    }
    
    
}

