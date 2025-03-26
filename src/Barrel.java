import java.io.Serializable;

class Barrel implements Comparable<Barrel>, Serializable {
    private int volume;
    private String storedMaterial;
    private String material;

    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

    public int getVolume() {
        return volume;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public int compareTo(Barrel other) {
        if (this.volume != other.volume)
            return Integer.compare(this.volume, other.volume);
        if (!this.storedMaterial.equalsIgnoreCase(other.storedMaterial))
            return this.storedMaterial.compareTo(other.storedMaterial);
        return this.material.compareTo(other.material);
    }

    @Override
    public String toString() {
        return " Barrel - " +
                " Material - " + material +
                " StoredMaterial - " + storedMaterial +
                " Volume - " + volume;
    }

    public static class Builder {
        private int volume;
        private String storedMaterial;
        private String material;

        public Builder setVolume(int volume) {
            this.volume = volume;
            return this;
        }

        public Builder setStoredMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }
}