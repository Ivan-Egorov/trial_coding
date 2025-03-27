import java.util.Scanner;

public interface TrialStrategySorting {

    String ANIMAL_CLUE = "Тип ANIMAL вводите данные в формате:\n" +
            "[Тип животного - строка] [Цвет глаз - строка] [Наличие шерсти - true/false] [Вес - от 1 до 300 кг]";

    String BARREL_CLUE = "Тип BARREL вводите данные в формате:\n" +
            "[Материал бочки - строка] [Хранимый материал - строка] [Объем - от 1 до 1000 л]";

    String HUMAN_CLUE = "Тип HUMAN вводите данные в формате:\n" +
            "[Пол человека - строка] [Фамилия - строка] [Возраст - от 14 до 89]";

    String EXIT_CLUE = "\nДля завершения ввода используйте:\n" +
            "[end] или [trial]";

    boolean isEmptyList();

    void inputRandom(int count);

    void inputFromConsole(Scanner in);

    void startBinarySearch(Scanner in);

    void startSort();

    void listToStringTest();

    void UploadFromFile();

    void save();

}
