package com.rocketgenius.deviceaccesscontrol.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long sn;

    private String time;
    private String dataType;
    private int channel;
    private float dataVal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSn() {
        if (sn != null)
            return sn;
        return 0L;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public float getDataVal() {
        return dataVal;
    }

    public void setDataVal(float dataVal) {
        this.dataVal = dataVal;
    }

    public DeviceData() {}

    public DeviceData(Long sn, String time, String dataType, int channel, float dataVal) {
        this.sn = sn;
        this.time = time;
        this.dataType = dataType;
        this.channel = channel;
        this.dataVal = dataVal;
    }

    @Override
    public String toString() {
        Map<String, String> Data = new HashMap<>();
        Data.put("sn", this.sn.toString());
        Data.put("time", this.time);
        Data.put("dataType", this.dataType);
        Data.put("channel", String.valueOf(this.channel));
        Data.put("dataVal", String.valueOf(this.dataVal));
        return Data.toString();
    }
}
