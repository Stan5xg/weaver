package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

import java.io.IOException;

public interface EventLogger {
    public void logEvent(Event event) throws IOException;
}
