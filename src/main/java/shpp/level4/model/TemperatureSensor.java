package shpp.level4.model;

import java.util.Map;

public class TemperatureSensor extends Sensor {
    private final String temperatureUnit;

    public TemperatureSensor(Builder builder) {
        super(builder);
        this.temperatureUnit = builder.temperatureUnit;
        this.sensorType = builder.sensorType;
    }

    @Override
    public Map<Sens or, Double> getSensorValues() {
        sensorValues.put(this, getCurrentValue());
        return sensorValues;
    }

    public enum TempSensorType {PT100, PTC, BI_METAL};
    private final TempSensorType sensorType;


    public static class Builder extends Sensor.Builder<Builder> {
        private String temperatureUnit;
        private TempSensorType sensorType;

        public Builder setTemperatureUnit(String temperatureUnit) {
            this.temperatureUnit = temperatureUnit;
            return this;
        }

        public Builder setSensorType(TempSensorType sensorType){
            this.sensorType = sensorType;
            return this;
        }

        @Override
        public TemperatureSensor build() {
            return new TemperatureSensor(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
