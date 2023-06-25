package shpp.level4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.level4.model.*;
import shpp.level4.proxy.FaultMessageList;
import shpp.level4.proxy.FaultMessageListProxyImpl;
import shpp.level4.util.ErrorMessageHandler;
import shpp.level4.util.MessageHandler;
import shpp.level4.util.WarnMassageHandler;

import java.util.Map;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger("console");
    public static void main(String[] args) {
        Sensor PT100 = new TemperatureSensor.Builder()
                .setTag("PT100_A1")
                .setName("PT100 8mm")
                .setMaxValue(100.0)
                .setMinValue(0.0)
                .setSignalType("Analog")
                .setTemperatureUnit("Celsius")
                .setSensorType(TemperatureSensor.TempSensorType.PT100)
                .build();

        Sensor PTC = new TemperatureSensor.Builder()
                .setTag("PTC3_80")
                .setName("PTC max +80C")
                .setMaxValue(80.0)
                .setMinValue(0.0)
                .setSignalType("Analogue")
                .setTemperatureUnit("Celsius")
                .setSensorType(TemperatureSensor.TempSensorType.PTC)
                .build();

        Sensor pressureSensor = new PressureSensor.Builder()
                .setTag("2045909")
                .setName("Pressure sensor")
                .setMaxValue(16.0)
                .setMinValue(2.0)
                .setPressureUnit("bar")
                .setSensorType(PressureSensor.PressureSensorType.GAUGE)
                .build();


        Equipment pumpBearing = new Bearing.Builder()
                .setTag("4201123")
                .setName("NZ3310")
                .setType(Bearing.BearingType.BALL)
                .setForceType(Bearing.ForceType.RADIAL)
                .build();

        Equipment motorBearing = new Bearing.Builder()
                .setTag("111253")
                .setName("NZ335")
                .setType(Bearing.BearingType.BALL)
                .setForceType(Bearing.ForceType.MIX)
                .build();

        pumpBearing.addComponent(PT100);
        motorBearing.addComponent(PTC);

        Equipment pump = new Pump.Builder()
                .setTag("4245560")
                .setName("Helix V402")
                .build();

        Equipment motor = new Motor.Builder()
                .setTag("58478")
                .setName("ABB")
                .setCos(0.98)
                .setNominalCurrent(11.5)
                .setNominalPower(11.0)
                .setProtectionClass("IP68")
                .build();

        motor.addComponent(motorBearing);

        pump.addComponent(motor);
        pump.addComponent(pumpBearing);
        pump.addComponent(pressureSensor);

        MessageHandler errorMessage = new ErrorMessageHandler();
        MessageHandler warnMessage = new WarnMassageHandler();
        errorMessage.setHandler(warnMessage);

        PTC.setMessageHandler(errorMessage);
        PT100.setMessageHandler(errorMessage);
        pressureSensor.setMessageHandler(errorMessage);

        Map<Sensor, Double> sensorValues = pump.getSensorValues();
        for (Map.Entry<Sensor, Double> entry : sensorValues.entrySet()) {
            Sensor sensor = entry.getKey();
            double value = entry.getValue();
            logger.info("Sensor tag = {}, sensor value = {}, sensor max value = {}"
                    , sensor.getTag()
                    , value
                    , sensor.getMaxValue());

            if (value > 0.7 * sensor.getMaxValue() && value <= sensor.getMaxValue()) {
                sensor.handleRequest("WARN");
            } else if (value > sensor.getMaxValue()) {
                sensor.handleRequest("ERROR");
            }
        }
        FaultMessageList faultMessageList = new FaultMessageListProxyImpl();
        pump.setFaultMessagesList(faultMessageList);
        logger.info("Last 10 fault message: {}", pump.getLast10FaultMessages());
    }
}