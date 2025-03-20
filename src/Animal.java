class Animal {
    private String species;
    private String eyeColor;
    private boolean hasFur;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.hasFur = builder.hasFur;
    }

    public class Builder {
        private String species;
        private String eyeColor;
        private boolean hasFur;

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder setHasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}