package shpp.level4.model;

import shpp.level4.proxy.FaultMessageList;

import java.util.*;

public abstract class Equipment {
    protected String tag;

    protected String name;

    protected List<Equipment> components;
    protected Map<Sensor, Double> sensorValues = new HashMap<>();
    protected FaultMessageList faultMessages;

    public void setFaultMessagesList(FaultMessageList faultMessages) {
        this.faultMessages = faultMessages;
    }
    public List<String> getLast10FaultMessages(){
        return  faultMessages.getLast10Faults();
    }
    public Map<Sensor, Double> getSensorValues(){
        for(Equipment component : components){
            sensorValues.putAll(component.getSensorValues());
        }
        return sensorValues;
    }
   protected Equipment(Builder<?> builder) {
        this.tag = builder.tag;
        this.name = builder.name;
        this.components = new ArrayList<>();
    }
    public String getTag() {
        return tag;
    }
    public void addComponent(Equipment component) {
        components.add(component);
    }
    public void removeComponent(Equipment component) {
        components.remove(component);
    }
    public abstract static class Builder<T extends Builder<T>> {
        protected String tag;

        protected String name;

        public T setTag(String tag) {
            this.tag = tag;
            return self();
        }

        public T setName(String name){
            this.name = name;
            return self();
        }

        public abstract Equipment build();

        protected abstract T self();
    }
}