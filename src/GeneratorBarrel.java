import java.util.Random;

public class GeneratorBarrel {

    private static final String[] materials = {"Cedar", "Birch", "Oak", "Pine", "Beech", "Alder",
            "Fir", "Aspen", "Spruce", "Apple tree", "Plane", "Maple", "Linden",
            "Larch", "Plastic"};

    private static final String[] storedMaterials = {"Wine", "Milk", "Cream", "Cognac", "Whiskey", "Tequila",
            "Water"};

    public Barrel getUnit() {
        Random random = new Random();
        return new Barrel.Builder()
                .setVolume(random.nextInt(1, 20) * 50)
                .setMaterial(materials[random.nextInt(0, materials.length)])
                .setStoredMaterial(storedMaterials[random.nextInt(0, storedMaterials.length)])
                .build();
    }
}
