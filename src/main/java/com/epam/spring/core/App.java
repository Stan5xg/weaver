package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private ConsoleEventLogger eventLogger;
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");


    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {

        App app = (App) ctx.getBean("app");

        app.logEvent("Some events for 1");
        app.logEvent("Some events for 2");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(
                client.getId(), client.getFullName()
        );
        Event event = (Event) ctx.getBean("event");
        event.setMsq(message);
        eventLogger.logEvent(event);
    }

}
