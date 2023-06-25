package shpp.level4.util;

import shpp.level4.model.Sensor;

public class ErrorMessageHandler implements MessageHandler{
    private MessageHandler handler;

    @Override
    public void handleRequest(String message, Sensor sensor) {
        if(message.equals("ERROR")){
            logger.error("Fault sensor value. Sensor tag={}, Sensor current value={}."
                    , sensor.getTag()
                    , sensor.getCurrentValue());
            logger.info("Send error message via sms.");
        }else if(handler != null){
            handler.handleRequest(message, sensor);
        }
    }

    @Override
    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }
}
