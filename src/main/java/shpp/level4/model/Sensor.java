package shpp.level4.model;

import shpp.level4.util.MessageHandler;

import java.util.Random;

public abstract class Sensor extends Equipment{
    protected double maxValue;
    protected double minValue;
    protected String signalType;
    protected double currentValue;
    protected boolean state;

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void handleRequest(String message) {
        if (messageHandler != null) {
            messageHandler.handleRequest(message, this);
        }
    }

    protected MessageHandler messageHandler;

    Sensor(Builder<?> builder) {
        super(builder);
        this.maxValue = builder.maxValue;
        this.minValue = builder.minValue;
        this.signalType = builder.signalType;
        this.currentValue = 2 * new Random().nextDouble() * maxValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public String getSignalType() {
        return signalType;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public boolean isFault() {
        return state;
    }

    @Override
    public void addComponent(Equipment component) {
        throw new UnsupportedOperationException("Sensors not support components");
    }

    @Override
    public void removeComponent(Equipment component) {
        throw new UnsupportedOperationException("Sensors not support components");
    }

    public abstract static class Builder<T> extends Equipment.Builder<Builder<T>> {
        protected double maxValue;
        protected double minValue;
        protected String signalType;

        public T setMaxValue(double maxValue) {
            this.maxValue = maxValue;
            return (T) self();
        }

        public T setMinValue(double minValue) {
            this.minValue = minValue;
            return (T) self();
        }

        public T setSignalType(String signalType) {
            this.signalType = signalType;
            return (T) self();
        }
    }
}

