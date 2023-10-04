package com.smart.devices.repositories;

import com.smart.devices.entities.Device;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceRepository {
     int count();
    void loadDatabase();

    void updateDatabase();

    Device findDevice(long index);
    List<Device> findAll();

    void remove(long id);


    long createDevice(String name,String ip);

    void update(Device device);


}
