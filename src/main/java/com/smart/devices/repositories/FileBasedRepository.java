package com.smart.devices.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.devices.entities.Device;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FileBasedRepository implements DeviceRepository {
    public List<Device> devices=Collections.synchronizedList(new ArrayList<Device>());
    public long lastIndex=0;
    public String fileName="save_devices.json";

    @Override
    public int count() {
        return devices.size();
    }

    public  void loadDatabase()
    {
        try{
            File f= new File(fileName);
          f.createNewFile();


        }catch(Exception e)
        {   System.out.println(e.getMessage());

            System.out.println("nu se poate crea sau porni fisier");
        }

        ObjectMapper mapper=new ObjectMapper();
        try {
            devices.clear();
            devices.addAll(mapper.readValue(new File(fileName),
                    new TypeReference<List<Device>>(){}));

             long max=-1L;
             for(Device dev : devices)
             {
                 if(dev.index>max)
                     max=dev.index;
             }
             lastIndex=max;

        } catch (IOException e) {

            System.out.println("nu se poate adauga din fisierul json");


        }


    }
   public  void updateDatabase()
    {
        try{
            File f=new File(fileName);
            f.createNewFile();

        }catch(Exception e)
        {
            System.out.println("nu se poate crea sau porni fisierul json");
        }

        ObjectMapper mapper=new ObjectMapper();
        try {
            mapper.writeValue(new File(fileName),devices);
        } catch (IOException e) {
            System.out.println("nu se poate modifica fisierul json");
        }

    }
   public  long createDevice(String name,String ip)
   {
       Device device=new Device();
       device.index=lastIndex+1;
       device.ip=ip;
       device.name=name;
       devices.add(device);
       lastIndex+=1;
       return device.index;

   }

    @Override
    public void update(Device device) {
        if(devices.stream().anyMatch(dev->dev.index==device.index))
        {
          Device  olddev=devices.stream().filter(dev->dev.index==device.index).findFirst().get();
            Device before=olddev;
            olddev.free=device.free;
            olddev.on=device.on;
            olddev.value=device.value;
            olddev.lastRequested=device.lastRequested;
            devices.set(devices.indexOf(before),olddev);
        }

    }

    @Override
    public Device findDevice(long index) {
        if(devices.stream().anyMatch(device->device.index==index))
        return devices.stream().filter(device->device.index==index).findFirst().get();

        return new Device();
    }

    @Override
    public List<Device> findAll() {
        return devices;
    }

    @Override
    public void remove(long id) {
        devices.removeIf(device->device.index==id);


    }
}
