package com.example.verifyreloadsmt.model;

public class tblReplaceVerify {
    private String WO;
    private String LINE;
    private String MACHINE;
    private String SLOT;

    public tblReplaceVerify(String WO, String LINE, String MACHINE, String SLOT) {
        this.WO = WO;
        this.LINE = LINE;
        this.MACHINE = MACHINE;
        this.SLOT = SLOT;
    }

    public String getWO() {
        return WO;
    }

    public void setWO(String WO) {
        this.WO = WO;
    }

    public String getLINE() {
        return LINE;
    }

    public void setLINE(String LINE) {
        this.LINE = LINE;
    }

    public String getMACHINE() {
        return MACHINE;
    }

    public void setMACHINE(String MACHINE) {
        this.MACHINE = MACHINE;
    }

    public String getSLOT() {
        return SLOT;
    }

    public void setSLOT(String SLOT) {
        this.SLOT = SLOT;
    }
}
