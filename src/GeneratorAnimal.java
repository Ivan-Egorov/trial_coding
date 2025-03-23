public class GeneratorAnimal implements GenerateUnit<Animal>{

    @Override
    public Animal getUnit() {
        return (new Animal.Builder().setSpecies("Cat").setEyeColor("Blue").setWeigth(15).setHasFur(true).build());
    }
}
