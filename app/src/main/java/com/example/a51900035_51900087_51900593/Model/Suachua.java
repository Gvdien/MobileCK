package com.example.a51900035_51900087_51900593.Model;

import java.io.Serializable;

public class Suachua implements Serializable {
    private String id;
    private String Noithuchien;
    private String Thoigian;
    private String Chiphi;
    private int pic;
    private String Linhkien;

    public Suachua(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoithuchien() {
        return Noithuchien;
    }

    public void setNoithuchien(String noithuchien) {
        Noithuchien = noithuchien;
    }

    public String getThoigian() {
        return Thoigian;
    }

    public void setThoigian(String thoigian) {
        Thoigian = thoigian;
    }

    public String getChiphi() {
        return Chiphi;
    }

    public void setChiphi(String chiphi) {
        Chiphi = chiphi;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setLinhkien(String linhkien) {
        Linhkien = linhkien;
    }

    public String getLinhkien() {
        return Linhkien;
    }

}
