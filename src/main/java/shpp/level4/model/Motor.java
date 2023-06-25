package shpp.level4.model;

public class Motor extends Equipment {
    private double cos;
    private String protectionClass;
    private double nominalPower;
    private double nominalCurrent;

    private Motor(Builder builder) {
        super(builder);
        this.cos = builder.cos;
        this.protectionClass = builder.protectionClass;
        this.nominalPower = builder.nominalPower;
        this.nominalCurrent = builder.nominalCurrent;
    }

    public double getCos() {
        return cos;
    }

    public String getProtectionClass() {
        return protectionClass;
    }

    public double getNominalPower() {
        return nominalPower;
    }

    public double getNominalCurrent() {
        return nominalCurrent;
    }

    public static class Builder extends Equipment.Builder<Builder> {
        private double cos;
        private String protectionClass;
        private double nominalPower;
        private double nominalCurrent;

        public Builder setCos(double cos) {
            this.cos = cos;
            return this;
        }

        public Builder setProtectionClass(String protectionClass) {
            this.protectionClass = protectionClass;
            return this;
        }

        public Builder setNominalPower(double nominalPower) {
            this.nominalPower = nominalPower;
            return this;
        }

        public Builder setNominalCurrent(double nominalCurrent) {
            this.nominalCurrent = nominalCurrent;
            return this;
        }

        @Override
        public Motor build() {
            return new Motor(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}

