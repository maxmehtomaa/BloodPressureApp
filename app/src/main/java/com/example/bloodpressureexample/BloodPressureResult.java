package com.example.bloodpressureexample;

import java.util.Date;

public class BloodPressureResult {

    private int sys;
    private int dys;
    private int pulse;
    private Date timestamp;

    public int getSys() {
        return sys;
    }

    public void setSys(int sys) {
        this.sys = sys;
    }

    public int getDys() {
        return dys;
    }

    public void setDys(int dys) {
        this.dys = dys;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}



