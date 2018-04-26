package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

public interface EventLogger {
    public void logEvent(Event event);
}
