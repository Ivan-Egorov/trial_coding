import java.util.Scanner;

public class HumanStrategySorting implements TrialStrategySorting{

    private TrialList<Human> dataR = new TrialList<>();
    private TrialDataHandler<Human> dataHandler = new TrialDataHandler<>();

    @Override
    public boolean isEmptyList() {
        return dataR == null || dataR.isEmpty();
    }

    @Override
    public void inputRandom(int count) {
        GeneratorHuman genUnit = new GeneratorHuman();

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
        System.out.println(HUMAN_CLUE + EXIT_CLUE);
        if (in.hasNext()) {
            String s = in.nextLine();
            Scanner input = new Scanner(s);

            if((s.contains("end") && s.indexOf("end") == 0) ||
                    (s.contains("trial")&& s.indexOf("trial") == 0))
                return;

            try {
                Human human = parseHuman(input);
                TrialBinarySearch<Human> binarySearchHuman = new TrialBinarySearch<Human>();
                index = binarySearchHuman.find(dataR, human);
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
        System.out.println(HUMAN_CLUE + EXIT_CLUE);

        while (true) {
            if (in.hasNext()) {
                String s = in.nextLine();
                Scanner input = new Scanner(s);

                if((s.contains("end") && s.indexOf("end") == 0) ||
                        (s.contains("trial")&& s.indexOf("trial") == 0))
                    return;

                try {
                    dataR.add(parseHuman(input));
                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input.close();
                }
            }

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