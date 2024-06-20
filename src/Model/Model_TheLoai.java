/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vhung
 */
public class Model_TheLoai {
    private int maTheLoai;
    private String tenTheLoai;
    private String moTaTheLoai;

    @Override
    public String toString() {
        return "TheLoai{" + "maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + ", moTaTheLoai=" + moTaTheLoai + '}';
    }

    public Model_TheLoai() {
    }

    // Constructor
    public Model_TheLoai(int maTheLoai, String tenTheLoai, String moTaTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTaTheLoai = moTaTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTaTheLoai() {
        return moTaTheLoai;
    }

    public void setMoTaTheLoai(String moTaTheLoai) {
        this.moTaTheLoai = moTaTheLoai;
    }
    
}
