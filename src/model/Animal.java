package model;

public class Animal implements Comparable<Animal> {

    private String species;
    private String eyeColor;
    private boolean hasFur;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.hasFur = builder.hasFur;
    }

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean isHasFur() {
        return hasFur;
    }

    @Override
    public int compareTo(Animal other) {
        if (!this.species.equalsIgnoreCase(other.species))
            return this.species.compareTo(other.species);
        if (!this.eyeColor.equalsIgnoreCase(other.eyeColor))
            return this.eyeColor.compareTo(other.eyeColor);
        return Boolean.compare(this.hasFur, other.hasFur);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hasFur=" + hasFur +
                '}';
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
