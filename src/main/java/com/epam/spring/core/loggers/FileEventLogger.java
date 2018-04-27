package com.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        if (!this.file.canWrite()) {
            throw new IOException("Can't write into " + fileName + ".");
        }
    }

    public void logEvent(com.epam.spring.core.events.Event event) throws IOException {
            boolean append = true;
            FileUtils.writeStringToFile(file, event.toString(), append);
    }
}
