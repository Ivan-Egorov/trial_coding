import java.util.Scanner;

public class BarrelStrategySorting implements TrialStrategySorting{

    private TrialList<Barrel> dataR = new TrialList<>();
    private TrialDataHandler<Barrel> dataHandler = new TrialDataHandler<>();

    @Override
    public boolean isEmptyList() {
        return dataR == null || dataR.isEmpty();
    }

    @Override
    public void inputRandom(int count) {
        GeneratorBarrel genUnit = new GeneratorBarrel();

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
        System.out.println(BARREL_CLUE + EXIT_CLUE);
        if (in.hasNext()) {
            String s = in.nextLine();
            Scanner input = new Scanner(s);

            if((s.contains("end") && s.indexOf("end") == 0) ||
                    (s.contains("trial")&& s.indexOf("trial") == 0))
                return;

            try {
                Barrel barrel = parseBarrel(input);
                TrialBinarySearch<Barrel> binarySearchBarrel = new TrialBinarySearch<Barrel>();
                index = binarySearchBarrel.find(dataR, barrel);
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

    @Override
    public void listToStringTest() {
        for (int i = 0; i < dataR.size(); i++) {
            System.out.println(i + " " + dataR.get(i).toString());
        }

    }

    private void fillListFromConsole(Scanner in)
    {
        System.out.println(BARREL_CLUE + EXIT_CLUE);

        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                if((s.contains("end") && s.indexOf("end") == 0) ||
                        (s.contains("trial")&& s.indexOf("trial") == 0))
                    return;

                try {
                    dataR.add(parseBarrel(input));
                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input.close();
                }
            }

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

            int volume = TrialValidator.IntValidate(input.nextInt(), 1000, 1, "[Объем - от 1 до 1000 л]");

            return new Barrel.Builder()
                    .setMaterial(material)
                    .setStoredMaterial(storedMaterial)
                    .setVolume(volume)
                    .build();
        } catch (CustomException e) {
            throw new CustomException("Некорректный ввод - " + e.getMessage() + "\n" + BARREL_CLUE);
        }
    }
}