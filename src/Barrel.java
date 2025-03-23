class Barrel {
    private int volume;
    private String storedMaterial;
    private String material;

    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

    @Override
    public String toString()
    {
        return " Barrel - "  +
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