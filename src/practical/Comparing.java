package practical;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Comparing {

    /**
     * Muze se hodit :)
     *
     * @param music to, co chci vytisknout
     */
    static void printList(ArrayList<Song> music) {
        for (Song song : music)
            System.out.println(song);
    }

    static void init() {
        String filePath = "Songs.txt";
        ArrayList<Song> music = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] attributes = line.split(";");
                Song tmp = new Song(attributes[0], Integer.parseInt(attributes[1]), Double.parseDouble(attributes[2]));
                music.add(tmp);
            }
        } catch (IOException e) {
            System.out.println("Task failed successfully");
        }
        System.out.println();

        System.out.println("By year of release");
        music.sort(Song.BY_YEAR);
        printList(music);
        System.out.println();

        /*music.stream()
                .sorted((s1, s2) -> Integer.compare(s1.yearOfRelease, s2.yearOfRelease))
                .forEach(System.out::println);
        */

        System.out.println("By name");
        music.sort(Song.BY_NAME);
        printList(music);
        System.out.println();

        /*music.stream()
                .sorted((s1, s2) -> s2.name.compareTo(s1.name))
                .forEach(System.out::println);
        */

        System.out.println("TOP 3");
        music.sort(Song.BY_RATING);
        for (int i = 0; i < 3; i++) {
            System.out.println(music.get(i));
        }
        System.out.println();

        /*music.stream()
                .sorted((s1, s2) -> Double.compare(s2.rating, s1.rating))
                .limit(3)
                .forEach(System.out::println);
        */

        System.out.println("Spotify (Wish edition) at work, please stand by... \n");
    }
}

class Song {
    String name;
    int yearOfRelease;
    double rating;

    public Song(String name, int yearOfRelease, double rating) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    public static Comparator<Song> BY_YEAR = (s1, s2) -> {
        return Integer.compare(s1.yearOfRelease, s2.yearOfRelease);
    };

    public static Comparator<Song> BY_NAME = (s1, s2) -> {
        return s2.name.compareTo(s1.name);
    };

    public static Comparator<Song> BY_RATING = (s1, s2) -> {
        return Double.compare(s2.rating, s1.rating);
    };

    @Override
    public String toString() {
        return name + " " + yearOfRelease + ")" + ": " + rating;
    }
}
