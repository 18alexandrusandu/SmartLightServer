package com.smart.devices.dtos;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

public class DeviceDto  implements Serializable {
    public boolean on = false;
    public boolean free = true;
    public double value = 0;
    public  String ip = null;
    public String name = "";
    @Id
    public long index = 0L;
}
