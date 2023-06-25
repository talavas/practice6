package shpp.level4.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.level4.service.FaultMessageListImpl;

import java.util.List;

public class FaultMessageListProxyImpl implements FaultMessageList{
    static Logger logger = LoggerFactory.getLogger("console");
    private FaultMessageList faultMessageList;
    @Override
    public List<String> getLast10Faults() {
        if(faultMessageList == null){
            logger.info("Loading last 10 fault message");
            faultMessageList = new FaultMessageListImpl();
        }
        return faultMessageList.getLast10Faults();
    }
}
