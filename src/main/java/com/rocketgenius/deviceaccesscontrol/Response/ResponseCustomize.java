package com.rocketgenius.deviceaccesscontrol.Response;

public class ResponseCustomize<Object> {
    private int errorcode;
    private String errormsg;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public ResponseCustomize() {
        this.errorcode = 0;
        this.errormsg = "";
        this.data = null;
    }

    public ResponseCustomize(int errorcode, String errormsg, Object data) {
        this.errorcode = errorcode;
        this.errormsg = errormsg;
        this.data = data;
    }

}
