package com.rocketgenius.deviceaccesscontrol.Repository;

import com.rocketgenius.deviceaccesscontrol.Model.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Long> {
    DeviceInfo findBySn(long sn);
}