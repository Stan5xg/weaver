package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");


    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) throws IOException {

        App app = (App) ctx.getBean("app");

        app.logEvent("Some events for 1");
        app.logEvent("Some events for 2");

        ctx.close();
    }

    private void logEvent(String msg) throws IOException {
        String message = msg.replaceAll(
                client.getId(), client.getFullName()
        );
        Event event = (Event) ctx.getBean("event");
        event.setMsq(message);
        eventLogger.logEvent(event);
    }

}
