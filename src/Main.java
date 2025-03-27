import java.util.Scanner;

public class Main {

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

        TrialStrategySorting trialStrategySorting = null;

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
                        trialStrategySorting = input(in, commandLine);
                        if(trialStrategySorting != null && !trialStrategySorting.isEmptyList())
                            trialStrategySorting.listToStringTest();
                        break;

                    case "sort":
                        if (trialStrategySorting != null && !trialStrategySorting.isEmptyList()) {
                            trialStrategySorting.startSort();
                            trialStrategySorting.listToStringTest();
                        } else
                            System.out.println("Нет данных для сортировки");
                        break;

                    case "search":
                        if (trialStrategySorting != null && !trialStrategySorting.isEmptyList())
                            trialStrategySorting.startBinarySearch(in);
                        else
                            System.out.println("Коллекция пуста, невозможно выполнить поиск");
                        break;

                    case "save":
                        if (trialStrategySorting != null && !trialStrategySorting.isEmptyList())
                            trialStrategySorting.save();
                        else
                            System.out.println("Коллекция пуста, нет данных для сохранения");
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

    private static TrialStrategySorting input(Scanner in, Scanner commandLine) {
        if (!commandLine.hasNext()) {
            System.out.println("Укажите тип данных");
            return null;
        }

        TrialStrategySorting trialStrategySorting  = null;

        switch (commandLine.next()) {
            case "animal":
                trialStrategySorting = new AnimalStrategySorting();
                break;
            case "barrel":
                trialStrategySorting = new BarrelStrategySorting();
                break;
            case "human":
                trialStrategySorting = new HumanStrategySorting();
                break;
            default:
                System.out.println("Укажите тип данных");
                return null;
        }

        if (!commandLine.hasNext()) {
            System.out.println("Укажите источник данных");
            return null;
        }

        switch (commandLine.next()) {
            case "console":
                trialStrategySorting.inputFromConsole(in);
                break;
            case "random":
                trialStrategySorting.inputRandom(commandLine.hasNextInt() ? commandLine.nextInt() : 10);
                break;
            case "file":
                trialStrategySorting.UploadFromFile();
                break;
            default:
                System.out.println("Укажите источник данных");
                return null;
        }

        return trialStrategySorting;
    }
}