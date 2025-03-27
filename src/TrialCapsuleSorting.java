import java.util.Scanner;

public class TrialCapsuleSorting {

    public static final String ANIMAL_CLUE = "Тип ANIMAL вводите данные в формате:\n" +
            "[Тип животного - строка] [Цвет глаз - строка] [Наличие шерсти - true/false] [Вес - от 1 до 300 кг]";

    public static final String BARREL_CLUE = "Тип BARREL вводите данные в формате:\n" +
            "[Материал бочки - строка] [Хранимый материал - строка] [Объем - от 1 до 1000 л]";

    public static final String HUMAN_CLUE = "Тип HUMAN вводите данные в формате:\n" +
            "[Пол человека - строка] [Фамилия - строка] [Возраст - от 14 до 89]";

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
            dataR.add(genUnit.getUnit());
        }
    }

    public void inputFromConsole(Scanner in) {
        fillListFromConsole(in);
        System.out.println("Ввод окончен");
    }

    public void startBinarySearch(Scanner in) {
        TrialSort.sort(dataR);
        int index = -1;
        System.out.println(getClue() + EXIT_CLUE);
        if (in.hasNext()) {
            String s = in.nextLine();
            Scanner input = new Scanner(s);

            if((s.contains("end") && s.indexOf("end") == 0) ||
                    (s.contains("trial")&& s.indexOf("trial") == 0))
                return;

            try {
                switch (typeData) {
                    case Main.ANIMAL:
                        Animal animal = parseAnimal(input);
                        TrialBinarySearch<Animal> binarySearchAnimal = new TrialBinarySearch<Animal>();
                        index = binarySearchAnimal.find(dataR, animal);
                        break;
                    case Main.BARREL:
                        Barrel barrel = parseBarrel(input);
                        TrialBinarySearch<Barrel> binarySearchBarrel = new TrialBinarySearch<Barrel>();
                        index = binarySearchBarrel.find(dataR, barrel);
                        break;
                    case Main.HUMAN:
                        Human human = parseHuman(input);
                        TrialBinarySearch<Human> binarySearchHuman = new TrialBinarySearch<Human>();
                        index = binarySearchHuman.find(dataR, human);
                        break;
                }

            } catch (CustomException e) {
                System.out.println(e.getMessage());
            } finally {
                input.close();
            }

            if (index == -1)
                System.out.println("Элемент не найден");
            else
                System.out.println("Элемент находится в коллекции на позиции - " + index + "\n" +
                        dataR.get(index).toString());
        }
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

    private void fillListFromConsole(Scanner in)
    {
        System.out.println(getClue() + EXIT_CLUE);

        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                if((s.contains("end") && s.indexOf("end") == 0) ||
                        (s.contains("trial")&& s.indexOf("trial") == 0))
                    return;

                try {
                    switch (typeData) {
                        case Main.ANIMAL:
                            dataR.add(parseAnimal(input));
                            break;
                        case Main.BARREL:
                            dataR.add(parseBarrel(input));
                            break;
                        case Main.HUMAN:
                            dataR.add(parseHuman(input));
                            break;
                    }

                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input.close();
                }
            }

        }
    }

    private String getClue()
    {
        if (typeData == Main.BARREL)
            return BARREL_CLUE;

        if (typeData == Main.HUMAN)
            return HUMAN_CLUE;

        return ANIMAL_CLUE;
    }

    private Animal parseAnimal(Scanner input) throws CustomException {
        try {

            String species = TrialValidator.StringValidate(input.next(), "[Тип животного - строка]");

            if (!input.hasNext())
                throw new CustomException("[Цвет глаз - строка]");

            String eyeColor = TrialValidator.StringValidate(input.next(), "[Цвет глаз - строка]");

            if (!input.hasNextBoolean())
                throw new CustomException("[Наличие шерсти - true/false]");

            boolean hasFur = input.nextBoolean();

            if (!input.hasNextInt())
                throw new CustomException("[Вес - от 1 до 300 кг]");

            int weigth = TrialValidator.IntValidate(input.nextInt(), 1, 300, "Вес за пределами диапазона 1 - 300");

            return new Animal.Builder()
                    .setSpecies(species)
                    .setEyeColor(eyeColor)
                    .setWeigth(weigth)
                    .setHasFur(hasFur)
                    .build();

        } catch (CustomException e) {
            throw new CustomException("Некорректный ввод - " + e.getMessage() + "\n" + ANIMAL_CLUE);
        }
    }

    private Barrel parseBarrel(Scanner input) throws CustomException {
        try {
            String material = TrialValidator.StringValidate(input.next(), "[Материал бочки - строка]");

            if (!input.hasNext())
                throw new CustomException("[Хранимый материал - строка]");

            String storedMaterial = TrialValidator.StringValidate(input.next(), "[Хранимый материал - строка]");

            if (!input.hasNextInt())
                throw new CustomException("[Объем - от 1 до 1000 л]");

            int volume = TrialValidator.IntValidate(input.nextInt(), 1, 1000, "[Объем - от 1 до 1000 л]");

            return new Barrel.Builder()
                    .setMaterial(material)
                    .setStoredMaterial(storedMaterial)
                    .setVolume(volume)
                    .build();
        } catch (CustomException e) {
            throw new CustomException("Некорректный ввод - " + e.getMessage() + "\n" + BARREL_CLUE);
        }
    }

    private Human parseHuman(Scanner input) throws CustomException {
        try {
            String gender = TrialValidator.StringValidate(input.next(), "[Пол человека - строка]");

            if (!input.hasNext())
                throw new CustomException("[Фамилия - строка]");

            String lastName = TrialValidator.StringValidate(input.next(), "[Фамилия - строка]");

            if (!input.hasNextInt())
                throw new CustomException("[Возраст - от 14 до 89]");

            int age = TrialValidator.IntValidate(input.nextInt(), 89, 14, "[Возраст - от 14 до 89]");

            return new Human.Builder()
                    .setGender(gender)
                    .setLastName(lastName)
                    .setAge(age)
                    .build();
        } catch (CustomException e) {
            throw new CustomException("Некорректный ввод - " + e.getMessage() + "\n" + HUMAN_CLUE);
        }
    }
}