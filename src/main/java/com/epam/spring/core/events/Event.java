package com.epam.spring.core.events;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private int id;
    private String msq;
    private Date date;
    private DateFormat df;

    {
        Random random = new Random();
        id = random.nextInt();
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public String getMsq() {
        return msq;
    }

    public void setMsq(String msq) {
        this.msq = msq;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msq='" + msq + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
