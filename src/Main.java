import java.util.Scanner;

public class Main {
    private static String a = "src/a/";
    private static String b = "src/b/";
    private static String text = "";

    public static void main(String[] args) {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду:\n" +
                    "[1] Создание файла\n" +
                    "[2] Запись в файл\n" +
                    "[3] Чтение файла\n" +
                    "[4] Перенос файлов из одной папки в другую\n" +
                    "[5] Удалить файл\n" +
                    "[6] Найти файл в папке\n" +
                    "[0] Нажмите '0' для завершения программы");
            int input = scanner.nextInt();
            switch (input){
                case 1 -> FileUtils.createNewFile(a);
                case 2 -> FileUtils.write(a,text);
                case 3 -> System.out.println(FileUtils.read(a));
                case 4 -> FileUtils.copyFolder(a,b);
                case 5 -> FileUtils.delete(a);
                case 6 -> finDirMenu();
            }
            if (input == 0){
                break;
            }
        }
    }
    public static void finDirMenu(){System.out.println("Введите имя файла, который хотите найти:");
        String nameFile = new Scanner(System.in).nextLine();
        FileUtils.findDir(a,nameFile);}
}
