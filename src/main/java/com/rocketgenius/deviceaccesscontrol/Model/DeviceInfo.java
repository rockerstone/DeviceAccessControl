package com.rocketgenius.deviceaccesscontrol.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Entity
public class DeviceInfo {

    @Id
    private Long sn; //序列号
    @Transient
    private String response; //由sha1生成的认证码
    @Transient
    private String nonce; //随机数，一次性有效
    @Transient
    private String time; //时间。如：2017-02-02T10:01:02.234
    private String softVersion; //软件版本
    private String hardwareVersion; //硬件版本

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public DeviceInfo() {}

    public DeviceInfo(Long sn) {
        this.sn = sn;
    }

    public DeviceInfo(Long sn, String response, String nonce, String time, String softVersion, String hardwareVersion) {
        this.sn = sn;
        this.response = response;
        this.nonce = nonce;
        this.time = time;
        this.softVersion = softVersion;
        this.hardwareVersion = hardwareVersion;
    }

    public boolean verify() {
        //TODO 设备验证
        System.out.println("认证码：" + this.response);
        System.out.println("随机数：" + this.nonce);
        System.out.println("时间：" + this.time);
        return true;
    }

    @Override
    public String toString() {
        Map<String, String> Info = new HashMap<>();
        Info.put("sn", this.sn.toString());
        Info.put("softVersion", this.softVersion);
        Info.put("hardwareVersion", this.hardwareVersion);
        return Info.toString();
    }

}
