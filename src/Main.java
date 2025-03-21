import java.util.ArrayList;


public class Main {

    public static final int ANIMAL = 0;
    public static final int BARREL = 1;
    public static final int HUMAN = 2;



    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");

        ArrayList<String> data = getFromRandom(10, new GeneratorAnimal());
        ArrayList<Integer> dataI = getFromRandom(10, new GeneratorBarrel());

        int typeData = ANIMAL;

        ArrayList dataR = switch (typeData) {
            case ANIMAL -> getFromRandom(10, new GeneratorAnimal());
            case BARREL -> getFromRandom(15, new GeneratorBarrel());
            case HUMAN -> getFromRandom(20, new GeneratorHuman());
            default -> getFromRandom(10, new GeneratorAnimal());
        };

    }

    private void startSort()
    {

    }

    private void startBinarySearch()
    {

    }

    private ArrayList<String> getFromFile()
    {
        return null;
    }

    private static <T> ArrayList<T> getFromRandom(int count, GenerateUnit<T> genUnit)
    {
        ArrayList<T> data = new ArrayList<>();

        for (int i = 0; i < count; i++)
        {
            data.add(genUnit.getUnit()); // ToDo - реализовать валидацию данных
        }

        return data;

    }

    private ArrayList<String> getFromConsole()
    {
        return null;
    }

    private void save()
    {

    }
}