package com.rocketgenius.deviceaccesscontrol.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rocketgenius.deviceaccesscontrol.Model.DeviceData;
import com.rocketgenius.deviceaccesscontrol.Model.DeviceInfo;
import com.rocketgenius.deviceaccesscontrol.Repository.DeviceDataRepository;
import com.rocketgenius.deviceaccesscontrol.Repository.DeviceInfoRepository;
import com.rocketgenius.deviceaccesscontrol.Response.ResponseCustomize;
import com.rocketgenius.deviceaccesscontrol.Response.ResponseTime;
import com.rocketgenius.deviceaccesscontrol.Response.ResponseToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class DeviceController {
    private final DeviceInfoRepository deviceInfoRepository;
    private final DeviceDataRepository deviceDataRepository;
    private final String jwtSecret = "tHwlnTI5jcYhZVET9sFI4CMc0I3yNU";

    DeviceController(DeviceInfoRepository deviceInfoRepository, DeviceDataRepository deviceDataRepository) {
        this.deviceInfoRepository = deviceInfoRepository;
        this.deviceDataRepository = deviceDataRepository;
    }

    @GetMapping("/kl/device/getsystime")
    public ResponseCustomize<ResponseTime> getSysTime(HttpServletResponse response) {
        Date date = new Date();
        ResponseTime responseTime = new ResponseTime(date.toString());
        return new ResponseCustomize<>(0, "", responseTime);
    }

    @PostMapping("/kl/device/reg")
    public ResponseCustomize<ResponseToken> deviceReg(@RequestBody DeviceInfo deviceInfo, HttpServletResponse response) {
        DeviceInfo deviceInfoExist = deviceInfoRepository.findBySn(deviceInfo.getSn());
        if (deviceInfoExist == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ResponseCustomize<>(404,"设备不存在",new ResponseToken(""));
        }
        if (!deviceInfo.verify()) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return new ResponseCustomize<>(406,"设备验证错误",new ResponseToken(""));
        }

        deviceInfoExist.setSoftVersion(deviceInfo.getSoftVersion());
        deviceInfoExist.setHardwareVersion(deviceInfo.getHardwareVersion());
        deviceInfoRepository.save(deviceInfoExist);

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        String token = JWT.create().withClaim("SN", deviceInfo.getSn().toString()).withIssuer("RocketGenius").sign(algorithm);
        ResponseToken responseToken = new ResponseToken(token);
        return new ResponseCustomize<>(0,"",responseToken);
    }

    @PostMapping("/kl/device/report")
    public ResponseCustomize<String> deviceReport(@RequestParam(value = "token") String token, @RequestBody DeviceData deviceData, HttpServletResponse response) {
        //验证token 获取SN
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("RocketGenius").build();
            verifier.verify(token);
            DecodedJWT jwt = JWT.decode(token);
            Claim sn = jwt.getClaim("SN");
            deviceData.setSn(Long.valueOf(sn.asString()));
        } catch (JWTVerificationException exception){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new ResponseCustomize<>(401, exception.getMessage(),"");
        }

        //TODO 数据验证

        //存储数据
        deviceDataRepository.save(deviceData);

        return new ResponseCustomize<>(0, "", "");

    }

}
