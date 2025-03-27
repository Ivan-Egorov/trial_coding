import java.io.Serializable;

public class Animal implements TrialComparable<Animal>, Serializable {

    private String species;
    private String eyeColor;
    private boolean hasFur;
    private int weigth;

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean isHasFur() {
        return hasFur;
    }

    public int getWeigth() {
        return weigth;
    }

    @Override
    public int compareTo(Animal other) {
        if (this.weigth != other.weigth)
            return Integer.compare(this.weigth, other.weigth);
        if (!this.species.equalsIgnoreCase(other.species))
            return this.species.compareTo(other.species);
        if (!this.eyeColor.equalsIgnoreCase(other.eyeColor))
            return this.eyeColor.compareTo(other.eyeColor);
        return Boolean.compare(this.hasFur, other.hasFur);
    }

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.hasFur = builder.hasFur;
        this.weigth = builder.weigth;
    }

    @Override
    public String toString() {
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