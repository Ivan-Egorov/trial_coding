class Animal {
    private String species;
    private String eyeColor;
    private boolean hasFur;
    private int weigth;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.hasFur = builder.hasFur;
        this.weigth = builder.weigth;
    }

    @Override
    public String toString()
    {
        return " Animal - " + species +
                " Eye color - " + eyeColor +
                " Has fur - " + hasFur +
                " Weigth - " + weigth;
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private boolean hasFur;
        private int weigth;

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

        public Builder setWeigth(int weigth) {
            this.weigth = weigth;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}