package com.example.a51900035_51900087_51900593.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Lichsu implements Serializable {

    private String id;
    private String Noithuchien;
    private String Thoigian;
    private int Chiphi;
    private int pic;

    public Lichsu(){}

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

    public int getChiphi() {
        return Chiphi;
    }

    public void setChiphi(int chiphi) {
        Chiphi = chiphi;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Lichsu{" +
                "id='" + id + '\'' +
                ", Noithuchien='" + Noithuchien + '\'' +
                ", Thoigian='" + Thoigian + '\'' +
                ", Chiphi='" + Chiphi + '\'' +
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
        return result;
    }
}
