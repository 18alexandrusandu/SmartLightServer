package com.smart.devices.services;

import com.smart.devices.entities.Device;
import com.smart.devices.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


@Component

public class Updater {
   @Autowired
    DeviceRepository repo;

    @Bean
    void ReadFileDatabase()
    {   System.out.println("init");

        repo.loadDatabase();
    }

    @Bean
    void updateConnectedDevicesAndSaveToDatabase()
    { Timer t=new Timer();

        t.schedule(
                new TimerTask() {
                    @Override
                    public void run() {

                        System.out.println("check conectivity");
                        boolean changed=false;
                        Date now=new Date();
                    List<Device>  obj=repo.findAll();

                    for(int i=0;i<obj.size();i++) {
                          Device d=obj.get(i);
                        if (d.lastRequested != null) {

                            long diff = now.getTime() - d.lastRequested.getTime();
                            long minutes = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
                            if (minutes > 5) {
                                repo.remove(d.index);

                                changed = true;
                            }
                        } else
                            d.lastRequested = new Date();
                    }

                        System.out.println("nr devices cnnected"+repo.count());
                        if (changed) {
                            repo.updateDatabase();
                        }




                    }
                },5000,5000

        );

    }



}
