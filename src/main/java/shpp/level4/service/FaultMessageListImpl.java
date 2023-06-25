package shpp.level4.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.level4.proxy.FaultMessageList;

import java.util.ArrayList;
import java.util.List;

public class FaultMessageListImpl implements FaultMessageList {
    static Logger logger = LoggerFactory.getLogger("console");
    @Override
    public List<String> getLast10Faults() {
        List<String> response = new ArrayList<>();
        response.add("Fault code 101: message error 1");
        response.add("Fault code 202: message error 2");
        response.add("Fault code 101: message error 3");
        response.add("Fault code 404: message error 4");
        response.add("Fault code 105: message error 5");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return response;
    }
}
