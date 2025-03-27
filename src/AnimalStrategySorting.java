import java.util.Scanner;

public class AnimalStrategySorting implements TrialStrategySorting {

    private TrialList<Animal> dataR = new TrialList<>();
    private TrialDataHandler<Animal> dataHandler = new TrialDataHandler<>();

    @Override
    public boolean isEmptyList() {
        return dataR == null || dataR.isEmpty();
    }

    @Override
    public void inputRandom(int count) {
        GeneratorAnimal genUnit = new GeneratorAnimal();

        for (int i = 0; i < count; i++) {
            dataR.add(genUnit.getUnit());
        }
    }

    @Override
    public void inputFromConsole(Scanner in) {
        fillListFromConsole(in);
        System.out.println("Ввод окончен");
    }

    @Override
    public void startBinarySearch(Scanner in) {
        TrialSort.sort(dataR);
        int index = -1;
        System.out.println(ANIMAL_CLUE + EXIT_CLUE);
        if (in.hasNext()) {
            String s = in.nextLine();
            Scanner input = new Scanner(s);

            if((s.contains("end") && s.indexOf("end") == 0) ||
                    (s.contains("trial")&& s.indexOf("trial") == 0))
                return;

            try {
                Animal animal = parseAnimal(input);
                TrialBinarySearch<Animal> binarySearchAnimal = new TrialBinarySearch<Animal>();
                index = binarySearchAnimal.find(dataR, animal);
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

    @Override
    public void startSort() {
        TrialSort.sort(dataR);
    }

    @Override
    public void UploadFromFile() {
        dataR = dataHandler.UploadFromFile(dataR);
    }

    @Override
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
        System.out.println(ANIMAL_CLUE + EXIT_CLUE);

        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                if((s.contains("end") && s.indexOf("end") == 0) ||
                        (s.contains("trial")&& s.indexOf("trial") == 0))
                    return;

                try {
                    dataR.add(parseAnimal(input));
                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input.close();
                }
            }

        }
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

            int weigth = TrialValidator.IntValidate(input.nextInt(), 300, 1, "Вес за пределами диапазона 1 - 300");

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
}