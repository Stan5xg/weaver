package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

import java.io.IOException;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
