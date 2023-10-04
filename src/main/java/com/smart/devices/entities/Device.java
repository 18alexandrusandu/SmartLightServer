package com.smart.devices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity

public class Device  implements Serializable {
    public boolean on = false;
    public boolean free = true;
    public double value = 0;
    public  String ip = null;
    public String name = "";

    public Date lastRequested;

    @Id
    public long index = 0L;
}
