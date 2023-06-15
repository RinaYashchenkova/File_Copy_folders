import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class FileUtils {
    private static Scanner scanner = new Scanner(System.in);
    private static String name;
    public static void createNewFile(String path){
        System.out.println("Введите имя файла:");
        String nameFile = scanner.nextLine();
        File mkd = new File(path);
        File file = new File(path + File.separator + nameFile + ".txt");
        mkd.mkdirs();
        try {
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл с таким именем уже существует");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void write(String path, String text){
        System.out.println("Введите имя файла, в который вы хотите сделать запись:");
        String nameFile = scanner.nextLine();
        File file = new File(path + File.separator + nameFile + ".txt");
        if (file.exists()){
            System.out.println("Введите текст:");
            text = scanner.nextLine();
            try {
                PrintWriter writer = new PrintWriter(file.getAbsoluteFile());
                try {
                    writer.print(text);
                } finally {
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String read(String path){
        System.out.println("Введите имя файла, который вы хотите считать:");
        String nameFile = scanner.nextLine();
        File file = new File(path + File.separator + nameFile + ".txt");
        StringBuilder builder = new StringBuilder();
        if (!file.exists()){
            try {
                throw new FileNotFoundException(file.getName());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String str;
                while ((str = reader.readLine())!=null){
                    builder.append(str + "\n");
                }
            } finally {
                reader.close();
            }
        }catch (IOException ex){
            throw new RuntimeException();
        }
        return builder.toString();
    }

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        File file = new File(sourceDirectory);
        exists(sourceDirectory);
        if (notEmpty(file)){
            for(File a : file.listFiles()){
                try {
                    Files.copy(a.toPath(),new File(destinationDirectory + File.separator + a.getName()).toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void exists(String path){
        File file = new File(path);
        if (!file.exists()){
            try {
                throw new FileNotFoundException(file.getName());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean notEmpty (File file){
        if (file.listFiles() != null){
            return true;
        } else {
            return false;
        }
    }
    public static void delete(String path){
        System.out.println("Введите имя файла, который вы хотите удалить:");
        String nameFile = scanner.nextLine();
        File file = new File(path + File.separator + nameFile + ".txt");
        if (file.exists()){
            file.delete();
        }
    }
    public static void findDir(String path, String nameFile){
        File folder = new File(path);
        if (folder.exists()){
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isDirectory()){
                    findDir(file.getAbsolutePath(),nameFile);
                }
                if (file.getAbsolutePath().contains(nameFile)){
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }
}
