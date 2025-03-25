import java.util.Random;

public class GeneratorAnimal implements GenerateUnit<Animal>{

    private static final String[] species = {"Cat", "Dog", "Rabbit", "Hamster", "Rat", "Parrot",
            "Turtle", "Cow", "Horse", "Pig", "Sheep", "Chicken", "Duck",
            "Goat", "Turkey", "Donkey", "Lion", "Tiger", "Elephant", "Giraffe",
            "Zebra", "Leopard", "Cheetah", "Wolf", "Bear", "Fox"};

    private static final String[] уyeColors = {"blue", "brown", "green", "grey", "hazel", "amber"};

    @Override
    public Animal getUnit() {
        Random random = new Random();
        return (new Animal.Builder()
                .setSpecies(species[random.nextInt(0, species.length)])
                .setEyeColor(уyeColors[random.nextInt(0, уyeColors.length)])
                .setWeigth(random.nextInt(1, 300))
                .setHasFur(random.nextBoolean())
                .build());
    }
}
