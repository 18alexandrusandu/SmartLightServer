package com.smart.devices.controllers;

import com.smart.devices.dtos.DeviceDto;
import com.smart.devices.entities.Device;
import com.smart.devices.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    DeviceService service;

    Mapper mapper;
   public DeviceController()
    {
        super();
        mapper=new Mapper();
    }
    @GetMapping("connect/{name}/{ip}")
     String connectDevice( @PathVariable String name, @PathVariable String ip)
    {
          System.out.println("create device control");
        long res=service.connectDevice(name,ip);
        System.out.println("passed");
        return Long.toString(res);
    }
    @GetMapping("device/state/{id}")
    DeviceDto askForState(@PathVariable long id)
    {
        return mapper.mapDeviceToDto(service.askForState(id));

    }
    @GetMapping("getAll")
    List<DeviceDto> getAll()
    {
        List<Device> devices=service.getAll();
        return mapper.mapListDeviceToDtos(devices);

    }
    @GetMapping("change/state/free/{id}/{value}")
    void updateFree(@PathVariable long id,boolean value)
    {
      service.updateFree(id, value);
    }

    @GetMapping("change/state/{id}/{value}")
    void updateOn(@PathVariable long id,boolean value)
    {
     service.updateOn(id,value);
    }

    @GetMapping("change/value/{id}")
    void updateValue(@PathVariable long id,double value)
    {
      service.updateValue(id,value);
    }

}
