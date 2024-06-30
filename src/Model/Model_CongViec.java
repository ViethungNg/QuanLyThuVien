/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vhung
 */
public class Model_CongViec {
    private int maQuyen;
    private String tenCongViec;

    public Model_CongViec() {
    }

    public Model_CongViec(int maQuyen, String tenCongViec) {
        this.maQuyen = maQuyen;
        this.tenCongViec = tenCongViec;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    @Override
    public String toString() {
        return "Model_CongViec{" + "maQuyen=" + maQuyen + ", tenCongViec=" + tenCongViec + '}';
    }

    
    
    
        
    public String getString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
