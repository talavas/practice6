package shpp.level4.model;

import java.util.Map;

public class PressureSensor extends Sensor {
    private final String pressureUnit;

    public PressureSensor(Builder builder) {
        super(builder);
        this.pressureUnit = builder.pressureUnit;
        this.sensorType = builder.sensorType;
    }

    @Override
    public Map<Sensor, Double> getSensorValues() {
        sensorValues.put(this, getCurrentValue());
        return sensorValues;
    }

    public enum PressureSensorType {ABSOLUTE, GAUGE, DIFFERENTIAL};
    private final PressureSensorType sensorType;

    public static class Builder extends Sensor.Builder<Builder> {
        private String pressureUnit;
        private PressureSensorType sensorType;

        public Builder setPressureUnit(String pressureUnit) {
            this.pressureUnit = pressureUnit;
            return this;
        }

        public Builder setSensorType(PressureSensorType sensorType) {
            this.sensorType = sensorType;
            return this;
        }

        @Override
        public PressureSensor build() {
            return new PressureSensor(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}

