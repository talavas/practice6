package shpp.level4.model;
public class Bearing extends Equipment {
    public enum BearingType {BALL, ROLL, BALL_ROLL, BUSH};
    public enum ForceType {AXIAL, RADIAL, MIX};
    private final BearingType type;
    private final ForceType forceType;
    protected Bearing(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.forceType = builder.forceType;
    }
    public static class Builder extends Equipment.Builder<Builder> {
        private BearingType type;
        private ForceType forceType;
        public Builder setType(BearingType type){
            this.type = type;
            return this;
        }
        public Builder setForceType(ForceType forceType){
            this.forceType = forceType;
            return this;
        }
        @Override
        public Bearing build() {
            return new Bearing(this);
        }
        @Override
        protected Builder self() {
            return this;
        }
    }
}
