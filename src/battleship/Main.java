package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Player me = new Player();
        me.printDisplayboard();

        System.out.println("Enter the coordinates of the ship:");
        Coord start = null;
        Coord end = null;
        try (Scanner scanner = new Scanner(System.in)) {
            if (scanner.hasNext("[A-Ja-j](10|[1-9])")) {
                start = new Coord(scanner.next());
            } else {
                System.out.println("Error");
                System.exit(0);
            }
            if (scanner.hasNext("[A-Ja-j](10|[1-9])")) {
                end = new Coord(scanner.next());
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        }
        Coord[] ship = Player.getParts(start, end);
        System.out.printf("Length: %d%n", ship.length);
        System.out.print("Parts: ");
        for (Coord loc : ship) {
            System.out.print(loc.name + " ");
        }
        System.out.println();

    }

    public static void shitPants() {
        System.out.println("Error");
        System.exit(0);
    }
}