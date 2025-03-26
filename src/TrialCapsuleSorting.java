import java.util.Scanner;

public class TrialCapsuleSorting {

    public static final String ANIMAL_CLUE = "Тип ANIMAL вводите данные в формате:\n" +
            "[Тип животного - строка] [Цвет глаз - строка] [Наличие шерсти - true/false] [Вес - Целое натуральное число]";

    public static final String BARREL_CLUE = "Тип BARREL вводите данные в формате:\n" +
            "[Материал бочки - строка] [Хранимый материал - строка] [Объем - Целое натуральное число]";

    public static final String HUMAN_CLUE = "Тип HUMAN вводите данные в формате:\n" +
            "[Пол человека - строка] [Фамилия - строка] [Возраст - Целое натуральное число]";

    public static final String EXIT_CLUE = "\nДля завершения ввода используйте:\n" +
            "[end] или [trial]";

    private TrialList dataR;
    private TrialDataHandler dataHandler = new TrialDataHandler();

    private int typeData = Main.ANIMAL;

    public void setTypeData(int typeData) {
        this.typeData = typeData;
        dataR = new TrialList<>();
    }

    public boolean isEmptyList() {
        return dataR == null || dataR.isEmpty();
    }

    public void inputRandom(int count) {
        System.out.println("count - " + count);

        GenerateUnit genUnit = switch (typeData) {
            case Main.ANIMAL -> new GeneratorAnimal();
            case Main.BARREL -> new GeneratorBarrel();
            case Main.HUMAN -> new GeneratorHuman();
            default -> new GeneratorAnimal();
        };

        for (int i = 0; i < count; i++) {
            dataR.add(genUnit.getUnit()); // ToDo - реализовать валидацию данных и обработку ошибки заполнения
        }
    }

    public void inputFromConsole(Scanner in) {
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

    public void startBinarySearch(int typeData, Scanner in) {
        if (typeData != this.typeData)
            return;


    }

    public void startSort() {
        TrialSort.sort(dataR);
    }

    public void UploadFromFile() {
        dataR = dataHandler.UploadFromFile(dataR);
    }

    public void save() {
        dataHandler.Save(dataR);
    }

    public void listToStringTest() {
        for (int i = 0; i < dataR.size(); i++) {
            System.out.println(i + " " + dataR.get(i).toString());
        }

    }

    private void parseAnimal(Scanner in) {

        System.out.println(ANIMAL_CLUE + EXIT_CLUE);
        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                String species = input.next();

                if (species.equals("end") || species.equals("trial"))
                    return;

                if (!input.hasNext()) {
                    System.out.println("Не хватает данных\n" + ANIMAL_CLUE);
                    continue;
                }
                String eyeColor = input.next();

                if (!input.hasNextBoolean()) {
                    System.out.println("Не хватает данных\n" + ANIMAL_CLUE);
                    continue;
                }
                boolean hasFur = input.nextBoolean();

                if (!input.hasNextInt()) {
                    System.out.println("Не хватает данных\n" + ANIMAL_CLUE);
                    continue;
                }
                int weigth = input.nextInt();

                input.close();

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

        System.out.println(BARREL_CLUE + EXIT_CLUE);
        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                String material = input.next();

                if (material.equals("end") || material.equals("trial"))
                    return;

                if (!input.hasNext()) {
                    System.out.println("Не хватает данных\n" + BARREL_CLUE);
                    continue;
                }
                String storedMaterial = input.next();

                if (!input.hasNextInt()) {
                    System.out.println("Не хватает данных\n" + BARREL_CLUE);
                    continue;
                }
                int volume = input.nextInt();

                dataR.add(new Barrel.Builder()
                        .setMaterial(material)
                        .setStoredMaterial(storedMaterial)
                        .setVolume(volume)
                        .build());

                input.close();
            }
        }
    }

    private void parseHuman(Scanner in) {
        System.out.println(HUMAN_CLUE + EXIT_CLUE);
        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                String gender = input.next();

                if (gender.equals("end") || gender.equals("trial"))
                    return;

                if (!input.hasNext()) {
                    System.out.println("Не хватает данных\n" + HUMAN_CLUE);
                    continue;
                }
                String lastName = input.next();

                if (!input.hasNextInt()) {
                    System.out.println("Не хватает данных\n" + HUMAN_CLUE);
                    continue;
                }

                int age = input.nextInt();

                dataR.add(new Human.Builder()
                        .setGender(gender)
                        .setLastName(lastName)
                        .setAge(age)
                        .build());

                input.close();
            }
        }
    }
}