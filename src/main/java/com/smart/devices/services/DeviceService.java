package com.smart.devices.services;


import com.smart.devices.dtos.DeviceDto;
import com.smart.devices.entities.Device;
import com.smart.devices.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
  @Autowired
    DeviceRepository repository;

    public long connectDevice(@PathVariable String name, @PathVariable String ip)
    {

        long id=repository.createDevice(name,ip);
         Device d= repository.findDevice(id);
         d.lastRequested=new Date();
         repository.updateDatabase();
         return id;

    }

    public Device askForState(@PathVariable long id)
    {
         Device d= repository.findDevice(id);
         d.lastRequested= new Date();
         repository.update(d);
         d=repository.findDevice(id);
         return d;
    }

   public List<Device> getAll()
    {

        return repository.findAll();

    }

   public void updateFree(@PathVariable long id,boolean value)
    {
      Device dev=repository.findDevice(id);
      if(dev!=null)
      {
        dev.free=value;
        repository.update(dev);
      }
    }


   public void updateOn(@PathVariable long id,boolean value)
    {
      Device dev=repository.findDevice(id);
      if(dev!=null)
      {
        dev.on=value;
        repository.update(dev);
      }

    }


   public  void updateValue(@PathVariable long id,double value)
    {
      Device dev=repository.findDevice(id);
      if(dev!=null)
      {
        dev.value=value;
        repository.update(dev);

      }
    }




}
