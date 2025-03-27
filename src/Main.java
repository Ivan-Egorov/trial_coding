import java.util.Scanner;

public class Main {

    public static final int ANIMAL = 0;
    public static final int BARREL = 1;
    public static final int HUMAN = 2;

    public static final String HELP =
            "Вас приветствуют Андрей, Иван, Дмитрий, Антон и Кирилл ( ‾́ ◡ ‾́ )\n" +
                    "Программа управляется консольным вводом и принимает следующие команды:\n" +
                    "\t[trial] [help] - информация о программе и ее управлении.\n" +
                    "\t[trial] [input] - переводит программу в режим ввода данных\n" +
                    "\t\t [animal] - задает соответствующий тип данных\n" +
                    "\t\t [barrel] - задает соответствующий тип данных\n" +
                    "\t\t [human] - задает соответствующий тип данных\n" +
                    "\t\t\t [console] - переводит программу в режим ввода с консоли\n" +
                    "\t\t\t [random] ?[можете указать количество] - программа генерирует данные самостоятельно\n" +
                    "\t\t\t [file] - импорт данных из файла\n" +
                    "\t[trial] [sort] - сортирует текущую коллекцию\n" +
                    "\t[trial] [search] - поиск по объекту\n" +
                    "\t[trial] [save] - сохраняет данные в файл\n" +
                    "\t[trial] [exit] - выход из программы\n";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean roundCondition = true;

        TrialCapsuleSorting trialCapsuleSorting = new TrialCapsuleSorting();

        System.out.println(HELP);

        while (roundCondition) {
            if (!in.hasNext())
                continue;

            String s = in.nextLine();
            Scanner commandLine = new Scanner(s);

            if (commandLine.next().equals("trial")) {
                if (!commandLine.hasNext())
                    continue;

                switch (commandLine.next()) {
                    case "help":
                        System.out.println(HELP);
                        break;

                    case "input":
                        if (input(in, commandLine, trialCapsuleSorting) &&
                                !trialCapsuleSorting.isEmptyList())
                            trialCapsuleSorting.listToStringTest();
                        break;

                    case "sort":
                        if (!trialCapsuleSorting.isEmptyList()) {
                            trialCapsuleSorting.startSort();
                            trialCapsuleSorting.listToStringTest();
                        }
                        break;

                    case "search":
                        if (!trialCapsuleSorting.isEmptyList())
                            trialCapsuleSorting.startBinarySearch(in);
                        break;

                    case "save":
                        if (!trialCapsuleSorting.isEmptyList())
                            trialCapsuleSorting.save();
                        break;

                    case "exit":
                        roundCondition = false;
                        break;
                }
            }

            commandLine.close();
        }

        in.close();
    }

    private static boolean input(Scanner in, Scanner commandLine, TrialCapsuleSorting trialCapsuleSorting) {
        if (!commandLine.hasNext()) {
            System.out.println("Укажите тип данных");
            return false;
        }

        switch (commandLine.next()) {
            case "animal":
                trialCapsuleSorting.setTypeData(ANIMAL);
                break;
            case "barrel":
                trialCapsuleSorting.setTypeData(BARREL);
                break;
            case "human":
                trialCapsuleSorting.setTypeData(HUMAN);
                break;
            default:
                System.out.println("Укажите тип данных");
                return false;
        }

        if (!commandLine.hasNext()) {
            System.out.println("Укажите источник данных");
            return false;
        }

        switch (commandLine.next()) {
            case "console":
                trialCapsuleSorting.inputFromConsole(in);
                break;
            case "random":
                trialCapsuleSorting.inputRandom(commandLine.hasNextInt() ? commandLine.nextInt() : 10);
                break;
            case "file":
                trialCapsuleSorting.UploadFromFile();
                break;
            default:
                System.out.println("Укажите источник данных");
                return false;
        }

        return true;
    }
}