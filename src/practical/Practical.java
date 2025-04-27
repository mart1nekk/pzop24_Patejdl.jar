package practical;

import java.util.Scanner;

/**
 * @author lukas.patejdl
 */

public class Practical {
    public static void main(String[] args) {
        initialize();
    }

    public static void initialize() {
        Scanner sc = new Scanner(System.in);
        int option;
        while (true) {
            System.out.println("Vyber si jeden z následujících úkolů:");
            System.out.println("1: GUI               2: Comparable/comparator");
            System.out.println("3: Exceptions        4: Parse");
            System.out.println("5: FileMerge         6: END");
            option = sc.nextInt();
            switch (option) {
                case 6:
                    return;
                case 1:
                    WindowTask.init();
                    break;
                case 2:
                    Comparing.init();
                    break;
                case 3:
                    Exceptional.init();
                    break;
                case 4:
                    UglinessLoader.init();
                    break;
                case 5:
                    SongMerger.init();
                    break;
                default:
                    System.out.println("Špatné číslo");
                    break;

            }
        }

    }
}
