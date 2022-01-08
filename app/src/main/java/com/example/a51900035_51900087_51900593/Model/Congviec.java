package com.example.a51900035_51900087_51900593.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Congviec {
    private  String title;
    private String noidung;
    private Date dateFinish;
    private Date hourFinish;

    public Congviec(){

    }

    public Congviec(String title, String noidung, Date dateFinish, Date hourFinish) {
        this.title = title;
        this.noidung = noidung;
        this.dateFinish = dateFinish;
        this.hourFinish = hourFinish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getHourFinish() {
        return hourFinish;
    }

    public void setHourFinish(Date hourFinish) {
        this.hourFinish = hourFinish;
    }

    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }

    @Override
    public String toString() {
        return this.title+"-"+
                getDateFormat(this.dateFinish)+"-"+
                getHourFormat(this.hourFinish);
    }
}