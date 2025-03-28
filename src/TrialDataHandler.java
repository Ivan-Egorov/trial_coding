import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Path path = Paths.get(directoryPath + "\\" + fileName + ".txt");
        boolean isAppend = Files.exists(path);

        File saveFile = new File(this.file, fileName + ".txt");

        A[] aArray = dataR.toArray();

        try{
            FileOutputStream fos = new FileOutputStream(saveFile, true);
            ObjectOutputStream output = (isAppend)? new MyObjectOutputStream(fos):new ObjectOutputStream(fos);

            for (A a : aArray) {
                output.writeByte(1);
                output.writeObject(a);
            }
            output.close();
            fos.close();
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
            //int count = input.readInt();

            while (input.read() != -1) {
                //input.skip(3);
                dataR.add((A)input.readObject());
            }

            //for (int i = 0; i < count; i++) {
            //    dataR.add((A)input.readObject());
            //}
            input.close();
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

    class MyObjectOutputStream extends ObjectOutputStream {

        public MyObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        public void writeStreamHeader() throws IOException
        {
            reset();
        }
    }
}