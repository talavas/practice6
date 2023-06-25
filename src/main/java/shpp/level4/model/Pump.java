package shpp.level4.model;

import java.util.Map;

public class Pump extends Equipment {
    private Pump(Builder builder) {
        super(builder);
    }

    public static class Builder extends Equipment.Builder<Builder>{

        @Override
        public Pump build() {
            return new Pump(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
