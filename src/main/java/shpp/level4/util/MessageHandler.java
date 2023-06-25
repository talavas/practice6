package shpp.level4.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.level4.model.Sensor;

public interface MessageHandler {
    static Logger logger = LoggerFactory.getLogger("console");
    void handleRequest(String message, Sensor sensor);

    void setHandler(MessageHandler handler);
}
