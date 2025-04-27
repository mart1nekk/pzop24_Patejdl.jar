package practical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Scenar je nasledujici:
 * Jste general, ktery se rozhoduje pred bojem s cizi armadou
 * Bojovnik ma silu a inteligenci
 * Mate 3 vzorky 20 nepratelskych bojovniku. Nepratelska kontrarozvedka vam
 * do souboru hodila nekolik spionu, ti jsou schovani v zavorkach, jejich atributy ignorujte.
 * Vy musite nacist potrebne udaje, abyste meli dostatek informaci,
 * zda nepratelskeho bojovnika umlatit palici nebo umlatit knihou
 */
public class UglinessLoader {

    static ArrayList<Enemy> loadInfo(String filePath) {
        //A zde je do seznamu ulozte
        return read(filePath);
    }

    static ArrayList<Enemy> read(String filePath) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int comment = 0;
            StringBuilder numberToAdd = new StringBuilder();
            line = br.readLine();
            while (line != null) {

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '(') {
                        comment++;
                    }
                    if (line.charAt(i) == ')') {
                        if (comment != 0) {
                            comment--;
                        }
                    }
                    if (comment == 0) {
                        if (Character.isDigit(line.charAt(i))) {
                            numberToAdd.append(line.charAt(i));
                            if (numberToAdd.length() == 4) {
                                enemies.add(new Enemy(Integer.parseInt(numberToAdd.substring(0, 2)), Integer.parseInt(numberToAdd.substring(2, 4))));
                                numberToAdd.delete(0, numberToAdd.length());
                            }
                        }
                    }
                }
                line = br.readLine();
            }

            if (!numberToAdd.isEmpty()){
                enemies.add(new Enemy(Integer.parseInt(numberToAdd.substring(0, 2)), Integer.parseInt(numberToAdd.substring(2, numberToAdd.length()))));
            }
        } catch (IOException e) {
            System.out.println("Task failed successfully");
        }
        return enemies;
    }


    static void init() {
        System.out.println("Prvni: " + loadInfo("simpleParse.txt").size());
        System.out.println("Druhy: " + loadInfo("ParseTest.txt").size());
        System.out.println("Treti: " + loadInfo("ParseMeIfYouCan.txt").size());
        System.out.println();
    }
}

class Enemy {
    int strength, intelligence;

    public Enemy(int strength, int intelligence) {
        this.strength = strength;
        this.intelligence = intelligence;
    }
}

