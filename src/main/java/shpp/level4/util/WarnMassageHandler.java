package shpp.level4.util;

import shpp.level4.model.Sensor;

public class WarnMassageHandler implements MessageHandler{
    private MessageHandler handler;
    @Override
    public void handleRequest(String message, Sensor sensor) {
        if(message.equals("WARN")){
            logger.warn("Sensor value close to max. Sensor tag={}, Sensor current value={}."
                    , sensor.getTag()
                    , sensor.getCurrentValue());
            logger.info("Send warning message via email.");
        }else if(handler != null){
            handler.handleRequest(message, sensor);
        }else{
           logger.info("No message handler for such message");
        }
    }

    @Override
    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }
}
