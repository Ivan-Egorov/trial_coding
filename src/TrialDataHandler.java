import java.io.*;
import java.util.Scanner;

public class TrialDataHandler<A> {

    File file;
    String directoryPath;

    public void Save(TrialList<A> dataR) {

        SpecifySavePath();

        if (CheckDirectory(directoryPath)) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название файла: ");
        String fileName = scanner.nextLine();

        File saveFile = new File(this.file, fileName + ".txt");

        A[] aArray = dataR.toArray();

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            output.writeInt(aArray.length);

            for (A a : aArray) {
                output.writeObject(a);
            }
            System.out.println("Данные успешно сохранены в " + saveFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public TrialList<A> UploadFromFile(TrialList<A> dataR) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите полный путь в формате Диск://папка//файл.txt: ");
        directoryPath = scanner.nextLine();

        if (CheckFile(directoryPath)) {
            return dataR;
        }

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            int count = input.readInt();

            for (int i = 0; i < count; i++) {
                dataR.add((A)input.readObject());
            }

            System.out.println("Данные загружены из файла: " + directoryPath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
        }

        return dataR;
    }

    private void SpecifySavePath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к папке для сохранения файла: ");
        directoryPath = scanner.nextLine().trim();
    }

    private boolean CheckDirectory(String directoryPath) {
        file = new File(directoryPath);

        if (!file.exists() || !file.isDirectory()) {
            System.err.println("Ошибка: Указанная директория не существует.");
            return true;
        }

        return false;
    }

    private boolean CheckFile(String filePath) {
        file = new File(filePath);

        if (file.getParentFile() == null || !file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
            System.err.println("Ошибка: Указанного файла не существует.");
            return true;
        }

        return false;
    }
}