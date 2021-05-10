package com.rocketgenius.deviceaccesscontrol.Repository;

import java.util.List;

import com.rocketgenius.deviceaccesscontrol.Model.DeviceData;
import org.springframework.data.repository.CrudRepository;

public interface DeviceDataRepository extends CrudRepository<DeviceData, Long> {

}