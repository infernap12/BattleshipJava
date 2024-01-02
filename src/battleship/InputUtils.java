package battleship;

import java.util.Scanner;

public class InputUtils {
    static Coord[] requestParts(Ship.ShipType shipClass, Player player) {
        System.out.printf("Enter the coordinates of the %s (%d cells):%n%n", shipClass.name(), shipClass.size);

        Coord start = null;
        Coord end = null;

        while (true) {
            start = requestCoord();
            end = requestCoord();
            System.out.println();
            boolean validLine = Coord.isValidLine(start,end, shipClass);
            if (validLine) {
                break;
            }
        }

        return new Coord[] {start, end};

    }

    private static Coord requestCoord() {
        try (Scanner scanner = new Scanner(System.in)) {
            if (!scanner.hasNext("[A-Ja-j](10|[1-9])")) {
                Main.shitPants();
            }
            return new Coord(scanner.next());
        }
    }
}
