public class GeneratorBarrel implements GenerateUnit<Barrel>{
    @Override
    public Barrel getUnit() {
        return new Barrel.Builder().setVolume(10).setMaterial("wood").setStoredMaterial("wine").build();
    }
}
