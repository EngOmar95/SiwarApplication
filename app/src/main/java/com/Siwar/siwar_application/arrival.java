package com.Siwar.siwar_application;

public class arrival {

    String time;
    String chick_display;

    public arrival(String time, String chick_display) {
        this.time = time;
        this.chick_display = chick_display;
    }

    public arrival() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChick_display() {
        return chick_display;
    }

    public void setChick_display(String chick_display) {
        this.chick_display = chick_display;
    }
}
