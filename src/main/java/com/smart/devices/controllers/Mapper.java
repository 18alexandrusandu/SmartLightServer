package com.smart.devices.controllers;

import com.smart.devices.dtos.DeviceDto;
import com.smart.devices.entities.Device;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    DeviceDto mapDeviceToDto(Device device)
    {

        DeviceDto dto=new DeviceDto();
         dto.index=device.index;
         dto.free= device.free;
         dto.on= device.on;
         dto.ip= device.ip;
         dto.name= device.name;
        return dto;

    }
    List<DeviceDto> mapListDeviceToDtos(List<Device> devices)
    {
        List<DeviceDto> list=new ArrayList<>();
         for(Device dev : devices)
         {
             list.add( mapDeviceToDto(dev));
         }

       return list;


    }



}
