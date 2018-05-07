package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private Map<EventType, EventLogger> loggers;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");


    public App(Client client,  Map<EventType, EventLogger> loggers, EventLogger eventLogger) {
        this.client = client;
        this.loggers = loggers;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) throws IOException {

        App app = (App) ctx.getBean("app");

        app.logEvent(null, "Event with no type");
        app.logEvent(EventType.INFO, "Generic event");
        app.logEvent(EventType.ERROR, "ERROR event");

        ctx.close();
    }

    private void logEvent(EventType type, String msg) throws IOException {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        String message = msg.replaceAll(
                client.getId(), client.getFullName()
        );
        Event event = (Event) ctx.getBean("event");
        event.setMsq(message);
        logger.logEvent(event);
    }

}
