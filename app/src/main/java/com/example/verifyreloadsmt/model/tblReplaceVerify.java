package com.example.verifyreloadsmt.model;

public class tblReplaceVerify {
    private String PRODUCTION_ORDER_ID;
    private String LINE_ID;
    private String MACHINE_ID;
    private String MACHINE_SLOT;

    public tblReplaceVerify(String WO, String LINE, String MACHINE, String SLOT) {
        this.PRODUCTION_ORDER_ID = WO;
        this.LINE_ID = LINE;
        this.MACHINE_ID = MACHINE;
        this.MACHINE_SLOT = SLOT;
    }

    public String getWO() {
        return PRODUCTION_ORDER_ID;
    }

    public void setWO(String WO) {
        this.PRODUCTION_ORDER_ID = WO;
    }

    public String getLINE() {
        return LINE_ID;
    }

    public void setLINE(String LINE) {
        this.LINE_ID = LINE;
    }

    public String getMACHINE() {
        return MACHINE_ID;
    }

    public void setMACHINE(String MACHINE) {
        this.MACHINE_ID = MACHINE;
    }

    public String getSLOT() {
        return MACHINE_SLOT;
    }

    public void setSLOT(String SLOT) {
        this.MACHINE_SLOT = SLOT;
    }
}
