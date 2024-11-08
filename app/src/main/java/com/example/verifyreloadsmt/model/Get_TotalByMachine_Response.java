package com.example.verifyreloadsmt.model;

public class Get_TotalByMachine_Response {
    private String PRODUCTION_ORDER_ID;
    private String LINE_ID;
    private String MACHINE_ID;
    private String MACHINE_SLOT;
    private String TotalByMachine;

    public Get_TotalByMachine_Response(String PRODUCTION_ORDER_ID, String LINE_ID, String MACHINE_ID, String MACHINE_SLOT) {
        this.PRODUCTION_ORDER_ID = PRODUCTION_ORDER_ID;
        this.LINE_ID = LINE_ID;
        this.MACHINE_ID = MACHINE_ID;
        this.MACHINE_SLOT = MACHINE_SLOT;
    }

    public String getPRODUCTION_ORDER_ID() {
        return PRODUCTION_ORDER_ID;
    }

    public void setPRODUCTION_ORDER_ID(String PRODUCTION_ORDER_ID) {
        this.PRODUCTION_ORDER_ID = PRODUCTION_ORDER_ID;
    }

    public String getLINE_ID() {
        return LINE_ID;
    }

    public void setLINE_ID(String LINE_ID) {
        this.LINE_ID = LINE_ID;
    }

    public String getMACHINE_ID() {
        return MACHINE_ID;
    }

    public void setMACHINE_ID(String MACHINE_ID) {
        this.MACHINE_ID = MACHINE_ID;
    }

    public String getMACHINE_SLOT() {
        return MACHINE_SLOT;
    }

    public void setMACHINE_SLOT(String MACHINE_SLOT) {
        this.MACHINE_SLOT = MACHINE_SLOT;
    }

    public String getTotalByMachine() {
        return TotalByMachine;
    }

    public void setTotalByMachine(String totalByMachine) {
        TotalByMachine = totalByMachine;
    }
}
