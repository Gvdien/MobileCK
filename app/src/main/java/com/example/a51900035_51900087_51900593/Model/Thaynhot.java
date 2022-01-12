package com.example.a51900035_51900087_51900593.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Thaynhot implements Serializable {
    private String id;
    private String Noithuchien;
    private String Thoigian;
    private String Chiphi;
    private String Loainhot;
    private int pic;

    public Thaynhot(){}

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

    public void setLoainhot(String loainhot) {
        Loainhot = loainhot;
    }

    public String getLoainhot() {
        return Loainhot;
    }

    @Override
    public String toString() {
        return "LichsuThaynhot{" +
                "id='" + id + '\'' +
                ", Noithuchien='" + Noithuchien + '\'' +
                ", Thoigian='" + Thoigian + '\'' +
                ", Chiphi=" + Chiphi +
                ", Loainhot='" + Loainhot + '\'' +
                ", pic=" + pic +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("Noithuchien", Noithuchien);
        result.put("Thoigian", Thoigian);
        result.put("Chiphi", Chiphi);
        result.put("pic", pic);
        result.put("Loainhot", Loainhot);
        return result;
    }
}
