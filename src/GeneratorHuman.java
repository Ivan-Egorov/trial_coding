import java.util.Random;

public class GeneratorHuman implements GenerateUnit<Human> {

    private static final String[] genders = {"male", "female", "man", "woman", "m", "w"};

    private static final String[] lastName = {"Novikov", "Anderson", "Brown", "Mironov", "Bunin", "Knight",
            "Gorenko", "Perez", "Hoffman", "Roosevelt", "Bunin", "Bonham",
            "Sergeev", "Darwin", "Dumas", "Trauberg", "Zubkov", "Fedorov",
            "Zhadan", "Zhukovsky", "Zaitsev", "Zubkov", "Suvorov", "Sukhorukov"};

    @Override
    public Human getUnit() {
        Random random = new Random();
        return new Human.Builder()
                .setGender(genders[random.nextInt(0, genders.length)])
                .setAge(random.nextInt(14, 89))
                .setLastName(lastName[random.nextInt(0, lastName.length)])
                .build();
    }
}
