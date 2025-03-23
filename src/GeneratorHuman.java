public class GeneratorHuman implements GenerateUnit<Human> {
    @Override
    public Human getUnit() {
        return new Human.Builder().setGender("male").setAge(32).setLastName("Novikov").build();
    }
}
