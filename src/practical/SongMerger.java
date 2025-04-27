package practical;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Sada ukolu v metode init()
 * Pro splneni ukolu je mozne vytvaret vlastni tridy, vlastni kolekce ci jen doplnit metody
 */
public class SongMerger {

    static void init() {
        // TODO: nactete soubry slozky taskStuff, nacist pouze soubory < 500 kB
        String dirToRead = "taskStuff";
        System.out.println("Neserazene soubory:");
        ArrayList<File> files = loadFiles(dirToRead);
        System.out.println(files.toString()); //lze pojmenovat jinak nez files
        System.out.println();

        System.out.println("Serazene soubory");
        // TODO: Metoda pro serazeni dle velikosti souboru
        //pozn: lze pouzit kolekci jako List
        // lze ale vyuzit i obycejne pole a radit pomoci Arrays.sort(pole, comparator)
        files.sort(SongMerger.BY_SIZE);
        System.out.println(files.toString()); //kontrola, dle velikosti sol<om<on
        System.out.println();

        // TODO: Vytvoreni souboru s nazvem a obsahem spojenym z *textovych* souboru
        //!! soubor se vytvoří, ale až se napíše 6, idk proč (během hodiny, co jsem se ptal, tak jste to uznal)
        StringBuilder nameOfFinalFile = new StringBuilder();

        for (File f : files) {
            nameOfFinalFile.append(f.getName(), 0, f.getName().lastIndexOf("."));
        }

        File finalFile = new File(nameOfFinalFile + ".txt");
        System.out.println("finální soubor je " + finalFile.getName() + "\n");

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(finalFile)));) {
            for (File f : files) {
                List<String> lines = Files.readAllLines(Paths.get(f.getPath()));
                for (String line : lines) {
                    pw.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Task failed successfully.");
        }
    }

    static ArrayList<File> loadFiles(String path) {
        ArrayList<File> files = new ArrayList<>();
        File[] tmp = new File(path).listFiles();
        for (File file : tmp) {
            if (file.length() < (500 * 1024) && file.getName().endsWith(".txt")) {
                files.add(file);
            }
        }
        return files;
    }

    private static final Comparator<File> BY_SIZE = (o1, o2) -> {
        return Long.compare(o1.length(), o2.length());
    };
}
