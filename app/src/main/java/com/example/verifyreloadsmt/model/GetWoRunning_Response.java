package com.example.verifyreloadsmt.model;

import java.util.List;

public class GetWoRunning_Response {
    private boolean status;
    private List<String> result;

    public GetWoRunning_Response(boolean status, List<String> result) {
        this.status = status;
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
