import java.util.ArrayList;
import java.util.Scanner;

public class TrialCapsuleSorting {

    private ArrayList dataR;

    private int typeData = Main.ANIMAL;

    public void setTypeData(int typeData)
    {
        this.typeData = typeData;
        dataR = new ArrayList<>();
    }

    public boolean isEmptyList()
    {
        return dataR == null || dataR.isEmpty();
    }

    public void inputRandom(int count)
    {
        GenerateUnit genUnit = switch (typeData) {
            case Main.ANIMAL -> new GeneratorAnimal();
            case Main.BARREL -> new GeneratorBarrel();
            case Main.HUMAN -> new GeneratorHuman();
            default -> new GeneratorAnimal();
        };

        for (int i = 0; i < count; i++)
        {
            dataR.add(genUnit.getUnit()); // ToDo - реализовать валидацию данных и обработку ошибки заполнения
        }

    }

    public void inputFromFile(String path)
    {

    }

    public void inputFromConsole(Scanner in)
    {
        switch (typeData) {
            case Main.ANIMAL:
                parseAnimal(in);
                break;
            case Main.BARREL:
                parseBarrel(in);
                break;
            case Main.HUMAN:
                parseHuman(in);
                break;
        }
        System.out.println("Ввод окончен");
    }

    public void startBinarySearch(int typeData, Scanner in)
    {
        if (typeData != this.typeData)
            return;


    }

    public void startSort()
    {

    }

    public void save()
    {

    }

    public void listToStringTest(){
        dataR.forEach(r -> System.out.println(r.toString()));
    }

    private void parseAnimal(Scanner in) {

        boolean roundCondition = true;
        while (roundCondition)
        {
            if (in.hasNext())
            {
                String species = in.next();

                if(species.equals("end") || species.equals("trial"))
                    roundCondition = false;

                if (!in.hasNext())
                    continue;
                String eyeColor = in.next();
                if (!in.hasNext())
                    continue;
                boolean hasFur = in.nextBoolean();
                if (!in.hasNext())
                    continue;
                int weigth = in.nextInt();

                dataR.add(new Animal.Builder()
                        .setSpecies(species)
                        .setEyeColor(eyeColor)
                        .setWeigth(weigth)
                        .setHasFur(hasFur)
                        .build());
            }

        }

    }

    private void parseBarrel(Scanner in) {
        boolean roundCondition = true;
        while (roundCondition)
        {
            if (in.hasNext())
            {
                String material = in.next();

                if(material.equals("end") || material.equals("trial"))
                    roundCondition = false;

                if (!in.hasNext())
                    continue;
                String storedMaterial = in.next();
                if (!in.hasNext())
                    continue;
                int volume = in.nextInt();

                dataR.add(new Barrel.Builder()
                        .setMaterial(material)
                        .setStoredMaterial(storedMaterial)
                        .setVolume(volume)
                        .build());
            }

        }
    }

    private void parseHuman(Scanner in) {
        while (true)
        {
            if (in.hasNext())
            {
                String gender = in.next();

                if(gender.equals("end") || gender.equals("trial"))
                    return;

                if (!in.hasNext())
                    continue;
                String lastName = in.next();
                if (!in.hasNext())
                    continue;
                int age = in.nextInt();

                dataR.add(new Human.Builder()
                        .setGender(gender)
                        .setLastName(lastName)
                        .setAge(age)
                        .build());
            }

        }
    }
}
