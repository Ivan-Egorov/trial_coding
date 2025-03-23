import java.util.Scanner;

public class Main {

    public static final int ANIMAL = 0;
    public static final int BARREL = 1;
    public static final int HUMAN = 2;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean roundCondition = true;

        TrialCapsuleSorting trialCapsuleSorting = new TrialCapsuleSorting();

        while (roundCondition)
        {
            if (!in.hasNext())
                return;

            if (in.next().equals("trial"))
            {
                if (!in.hasNext())
                    return;

                switch (in.next()) {
                    case "help":
                        help();
                        break;

                    case "input":
                        input(in, trialCapsuleSorting);
                        if(!trialCapsuleSorting.isEmptyList())
                            trialCapsuleSorting.listToStringTest();
                        break;

                    case "sort":
                        if(!trialCapsuleSorting.isEmptyList())
                            trialCapsuleSorting.startSort();
                        break;

                    case "search":
                        break;

                    case "save":
                        if(!trialCapsuleSorting.isEmptyList())
                            trialCapsuleSorting.save();
                        break;

                    case "exit":
                        roundCondition = false;
                        break;
                }

            }


        }

        in.close();
    }

    private static void input(Scanner in, TrialCapsuleSorting trialCapsuleSorting)
    {
        if (!in.hasNext())
            return;

        switch (in.next()) {
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
                return;
        }

        if (!in.hasNext())
            return;

        switch (in.next()) {
            case "console" -> trialCapsuleSorting.inputFromConsole(in);
            case "random" -> trialCapsuleSorting.inputRandom(in.nextInt());
            case "file" ->  trialCapsuleSorting.inputFromFile(in.next());
            default -> System.out.println("Укажите источник данных");
        }

        System.out.println("Данные добавлены");
    }

    private static void search(Scanner in, TrialCapsuleSorting trialCapsuleSorting)
    {
        if (!in.hasNext())
            return;

        switch (in.next()) {
            case "animal":
                trialCapsuleSorting.startBinarySearch(ANIMAL, in);
                break;
            case "barrel":
                trialCapsuleSorting.startBinarySearch(BARREL, in);
                break;
            case "human":
                trialCapsuleSorting.startBinarySearch(HUMAN, in);
                break;
        }

    }

    private static void help()
    {
        System.out.println("Commands: ");
    }
}