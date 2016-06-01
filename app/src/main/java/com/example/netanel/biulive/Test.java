package com.example.netanel.biulive;

public class Test {
    private String day;
    private String hour;
    private String moed;

    public Test(String day, String hour, String moed) {
        this.day = day;
        this.hour = hour;
        this.moed = moed;

    }
    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMoed() {
        return this.moed;
    }

    public void setMoed(String moed) {
        this.moed = moed;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
